package org.example.dailydriver.mapper;

import org.example.dailydriver.model.dto.carDto.CarCreateDto;
import org.example.dailydriver.model.dto.carDto.CarDto;
import org.example.dailydriver.model.dto.carDto.CarUpdateDto;
import org.example.dailydriver.model.entity.Car;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {FileMapper.class, LocationMapper.class, CommentMapper.class})
public interface CarMapper {


    CarDto toDto(Car car);

    Car toEntity(CarDto dto);

    Car toEntity(CarCreateDto dto);

    Car toEntity(CarUpdateDto dto);

    List<CarDto> toDtoList(List<Car> cars);

    List<Car> toEntityList(List<CarDto> carDtos);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "files", ignore = true)
    void updateCarFromDto(CarUpdateDto dto, @MappingTarget Car car);


}
