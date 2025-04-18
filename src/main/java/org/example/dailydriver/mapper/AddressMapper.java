package org.example.dailydriver.mapper;

import org.example.dailydriver.model.dto.addressDto.AddressCreateDto;
import org.example.dailydriver.model.dto.addressDto.AddressDto;
import org.example.dailydriver.model.entity.Address;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    Address toEntity(AddressCreateDto address);
    Address toEntity(AddressDto address);

    AddressDto toDto(Address address);

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateAddress(AddressDto dto, @MappingTarget Address entity);
}
