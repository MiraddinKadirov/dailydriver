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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
        List<GrantedAuthority> authorities;

        if (syncDb) {
            Optional<AuthUser> authUser = authUserRepository.findByUsername(claims.getSubject());
            AuthUser byUsername = authUser.orElseThrow(() -> new UsernameNotFoundException("User not found"));

            username = byUsername.getUsername();
            password = byUsername.getPassword();

            authorities = getPermissions(byUsername.getRole());

        } else {
            username = claims.getSubject();
            password = claims.get("password", String.class);

            String roleName = claims.get("role", String.class);

            authorities = List.of(new SimpleGrantedAuthority("ROLE_" + roleName));
        }

        return new User(username, password, authorities);
    }

    public List<GrantedAuthority> getPermissions(Role role) {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
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
