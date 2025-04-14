package org.example.dailydriver.mapper;

import org.example.dailydriver.model.dto.authUserDto.AuthUserCreateDto;
import org.example.dailydriver.model.dto.authUserDto.AuthUserDto;
import org.example.dailydriver.model.dto.authUserDto.AuthUserUpdateDto;
import org.example.dailydriver.model.entity.AuthUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthUserMapper {

    AuthUser toEntity(AuthUserDto authUserDto);
    AuthUser toEntity(AuthUserCreateDto authUserCreateDto);
    AuthUser toEntity(AuthUserUpdateDto authUserUpdateDto);

    AuthUserDto toDto(AuthUser authUser);

}
