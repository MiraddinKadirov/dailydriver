package org.example.dailydriver.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.example.dailydriver.config.security.JwtService;
import org.example.dailydriver.mapper.AuthUserMapper;
import org.example.dailydriver.model.AuthRequest;
import org.example.dailydriver.model.AuthResponse;
import org.example.dailydriver.model.TokenDto;
import org.example.dailydriver.model.dto.authUserDto.AuthUserCreateDto;
import org.example.dailydriver.model.dto.authUserDto.AuthUserDto;
import org.example.dailydriver.model.dto.authUserDto.AuthUserUpdateDto;
import org.example.dailydriver.model.entity.AuthUser;
import org.example.dailydriver.model.enums.Role;
import org.example.dailydriver.model.enums.Status;
import org.example.dailydriver.repository.AuthUserRepository;
import org.example.dailydriver.validation.AuthUserValidation;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AuthUserService implements CrudService<AuthUserCreateDto
        , AuthUserUpdateDto
        , AuthUserDto
        , String> {

    private final AuthUserRepository repository;
    private final AuthUserValidation validation;
    private final AuthUserMapper mapper;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthUserService(AuthUserRepository repository, AuthUserValidation validation, AuthUserMapper mapper, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.validation = validation;
        this.mapper = mapper;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }


    @Transactional
    @Override
    public AuthUserDto save(AuthUserCreateDto entity) {
        validation.validate(entity);
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        AuthUser entity1 = mapper.toEntity(entity);
        AuthUser save = repository.save(entity1);
        return mapper.toDto(save);
    }

    @Override
    public AuthUserDto update(AuthUserUpdateDto entity, String id) {
        AuthUser authUser = repository.findByIdAndNotDeleted(id)
                .orElseThrow(() -> new EntityNotFoundException("AuthUser not found: " + id));
        if ((entity.getPassword() != null && !entity.getPassword().isBlank()) ||
                (entity.getPhoneNumber() != null && !entity.getPhoneNumber().isBlank())) {
            validation.validate(entity);
        }

        mapper.updateDto(entity, authUser);
        repository.save(authUser);
        return mapper.toDto(authUser);
    }



    @Override
    public Boolean delete(String id) {
        AuthUser authUser = repository.findByIdAndNotDeleted(id).orElse(null);
        if (authUser == null) {
            return false;
        }
        authUser.setDeleted(true);
        repository.save(authUser);
        return true;
    }

    @Override
    public AuthUserDto findById(String id) {
        AuthUser authUser = repository.findByIdAndNotDeleted(id).orElseThrow(() -> new EntityNotFoundException(id));
        return mapper.toDto(authUser);
    }

    @Override
    public List<AuthUserDto> findAll() {
        List<AuthUser> authUsers = repository.findAllByDeletedFalse().orElseThrow(() -> new RuntimeException("Not found"));
        return mapper.toDto(authUsers);
    }


    public List<AuthUserDto> getAllUsers() {
        List<AuthUser> authUsers = repository.findAllByDeletedFalse().orElse(null);
        return mapper.toDto(authUsers);
    }


    public void changeUserStatus(String userId, Status status) {
        AuthUser user = repository.findByIdAndNotDeleted(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        user.setStatus(status);
        repository.save(user);
    }


    public void changeUserRole(String userId, Role role) {
        AuthUser user = repository.findByIdAndNotDeleted(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        user.setRole(role);
        repository.save(user);
    }

    public AuthResponse login(AuthRequest authRequest) {
        AuthUser authUser = repository.findByUsername(authRequest.getUsername())
                .orElse(null);

        if (authUser == null) {
            throw new RuntimeException("User not found with username: " + authRequest.getUsername());
        }
        UserDetails userDetails = new User(authUser.getUsername(), authUser.getPassword(), jwtService.getPermisions(authUser.getRole()));
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Role role = authUser.getRole();

        //token yasash boshlandi
        Map<String, Object> claims = new HashMap<>(Map.of(
                "userId", authUser.getId(),
                "role", role,
                "password", authUser.getPassword()
        ));

        TokenDto access = jwtService.generateAccessToken(authRequest.getUsername(), claims);
        TokenDto refresh = jwtService.generateRefreshToken(authRequest.getUsername(), Map.of());

        return AuthResponse.builder()
                .accessToken(access)
                .refreshToken(refresh)
                .build();
    }
}
