package org.example.dailydriver.mapper;

import org.example.dailydriver.model.dto.authUserDto.AuthUserCreateDto;
import org.example.dailydriver.model.dto.authUserDto.AuthUserDto;
import org.example.dailydriver.model.dto.authUserDto.AuthUserUpdateDto;
import org.example.dailydriver.model.entity.AuthUser;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = {AddressMapper.class})
public interface AuthUserMapper {

    AuthUser toEntity(AuthUserDto authUserDto);
    AuthUser toEntity(AuthUserCreateDto authUserCreateDto);
    AuthUser toEntity(AuthUserUpdateDto authUserUpdateDto);

    AuthUserDto toDto(AuthUser authUser);

    List<AuthUserDto> toDto(List<AuthUser> authUser);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "username", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateDto(AuthUserUpdateDto authUserUpdateDto, @MappingTarget AuthUser authUser);

}
