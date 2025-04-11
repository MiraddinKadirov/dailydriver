package org.example.dailydriver.service;

import org.example.dailydriver.model.dto.authUserDto.AuthUserCreateDto;
import org.example.dailydriver.model.dto.authUserDto.AuthUserDto;
import org.example.dailydriver.model.dto.authUserDto.AuthUserUpdateDto;
import org.example.dailydriver.repository.AuthUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthUserService implements CrudService<AuthUserCreateDto
        , AuthUserUpdateDto
        , AuthUserDto
        , String> {

    private final AuthUserRepository repository;

    public AuthUserService(AuthUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Boolean save(AuthUserCreateDto entity) {
        return null;
    }

    @Override
    public AuthUserDto update(AuthUserCreateDto entity, String id) {
        return null;
    }

    @Override
    public Boolean delete(String id) {
        return null;
    }

    @Override
    public AuthUserDto findById(String id) {
        return null;
    }

    @Override
    public List<AuthUserDto> findAll() {
        return List.of();
    }
}
