package org.example.dailydriver.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.example.dailydriver.mapper.AuthUserMapper;
import org.example.dailydriver.model.dto.authUserDto.AuthUserCreateDto;
import org.example.dailydriver.model.dto.authUserDto.AuthUserDto;
import org.example.dailydriver.model.dto.authUserDto.AuthUserUpdateDto;
import org.example.dailydriver.model.entity.AuthUser;
import org.example.dailydriver.repository.AuthUserRepository;
import org.example.dailydriver.validation.AuthUserValidation;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthUserService implements CrudService<AuthUserCreateDto
        , AuthUserUpdateDto
        , AuthUserDto
        , String> {

    private final AuthUserRepository repository;
    private final AuthUserValidation validation;
    private final AuthUserMapper mapper;

    public AuthUserService(AuthUserRepository repository, AuthUserValidation validation, AuthUserMapper mapper) {
        this.repository = repository;
        this.validation = validation;
        this.mapper = mapper;
    }

    @Transactional
    @Override
    public AuthUserDto save(AuthUserCreateDto entity) {
        validation.validate(entity);
        AuthUser entity1 = mapper.toEntity(entity);
        repository.save(entity1);
        return mapper.toDto(entity1);
    }

    @Override
    public AuthUserDto update(AuthUserUpdateDto entity, String id) {
        validation.validate(entity);
        AuthUser authUser = repository.findById(id).orElseThrow(() -> new EntityNotFoundException(id));
        authUser = mapper.toEntity(entity);
        authUser.setUpdatedAt(LocalDateTime.now());
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
        return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }
}
