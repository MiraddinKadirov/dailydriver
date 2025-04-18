package org.example.dailydriver.mapper;

import org.example.dailydriver.model.dto.locationDto.CarLocationDto;
import org.example.dailydriver.model.dto.locationDto.LocationCreateDto;
import org.example.dailydriver.model.entity.CarLocation;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LocationMapper {

    CarLocation toEntity(LocationCreateDto dto);

    CarLocationDto toDto(CarLocation location);

    List<CarLocationDto> toDtoList(List<CarLocation> locations);

}
