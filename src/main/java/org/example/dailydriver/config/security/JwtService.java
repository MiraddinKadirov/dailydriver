package org.example.dailydriver.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.example.dailydriver.model.TokenDto;
import org.example.dailydriver.model.entity.AuthUser;
import org.example.dailydriver.model.enums.Role;
import org.example.dailydriver.repository.AuthUserRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.PublicKey;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class JwtService {

    private final JwtProperties jwtProperties;
    private final AuthUserRepository authUserRepository;

    public Claims validateToken(String token) {
        if (token == null || !token.startsWith("Bearer ")) {
            throw new IllegalArgumentException("token is null");
        }
        token = token.replace("Bearer ", "");
        try {
            Claims claims = extractClaims(token);
            if (claims.getExpiration().before(new Date())) {
                throw new IllegalArgumentException("token is expired");
            }
            return claims;
        } catch (RuntimeException e) {
            throw new BadCredentialsException("Invalid Token");
        }
    }

    private Claims extractClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes());
    }

    public UserDetails makeUserdetails(Claims claims, boolean syncDb) {
        String username;
        String password;
        List<SimpleGrantedAuthority> authorities;

        if (syncDb) {
            //  db
            Optional<AuthUser> authUser = authUserRepository.findByUsername(claims.getSubject());
            AuthUser byUsername = authUser.orElseThrow(() -> new UsernameNotFoundException("User not found"));
            password = byUsername.getPassword();
            username = byUsername.getUsername();
            authorities = getPermisions(byUsername.getRole());

        }else {

            /// claim
            String roleName = claims.get("role", String.class);
            ArrayList<String> permissions = claims.get("permissions", ArrayList.class);
            authorities = permissions.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
            authorities.add(new SimpleGrantedAuthority("ROLE_" + roleName));
            username = claims.getSubject();
            password = claims.get("password", String.class);

        }
        return new User(username, password, authorities);
    }

    public List<SimpleGrantedAuthority> getPermisions(Role role) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_"+role.name()));
        return authorities;
    }

    public TokenDto generateAccessToken(String username, Map<String, Object> claims) {
        Date exp = new Date(System.currentTimeMillis() + jwtProperties.getAccessTokenExpire());
        return TokenDto.builder()
                .expiry(exp)
                .token(buildToken(username, claims, exp))
                .build();
    }

    private String buildToken(String username, Map<String, Object> claims, Date exp) {
        return Jwts.builder()
                .signWith(getSecretKey())
                .issuedAt(new Date())
                .expiration(exp)
                .subject(username)
                .claims(claims)
                .compact();
    }

    public TokenDto generateRefreshToken(String username, Map<String, Object> claims) {
        Date exp = new Date(System.currentTimeMillis() + jwtProperties.getRefreshTokenExpire());
        return TokenDto.builder()
                .expiry(exp)
                .token(buildToken(username, claims, exp))
                .build();
    }
}
