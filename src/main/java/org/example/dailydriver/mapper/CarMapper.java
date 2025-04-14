package org.example.dailydriver.mapper;

import org.example.dailydriver.model.dto.carDto.CarCreateDto;
import org.example.dailydriver.model.dto.carDto.CarDto;
import org.example.dailydriver.model.dto.carDto.CarUpdateDto;
import org.example.dailydriver.model.entity.Car;
import org.example.dailydriver.model.entity.CarLocation;
import org.example.dailydriver.model.entity.Comment;
import org.mapstruct.*;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface CarMapper {

    @Mapping(target = "location", expression = "java(toCarLocation(carCreateDto))")
    Car toEntity(CarCreateDto carCreateDto);
    Car toEntity(CarDto carDto);

    @Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now())")
    Car toEntity(CarUpdateDto carUpdateDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCarFromDto(CarUpdateDto dto, @MappingTarget Car car);

    List<CarDto> toDtoList(List<Car> cars);

    @Mapping(target = "comments", source = "comments")
    CarDto toDto(Car car);

    List<Comment> commentsToList(Set<Comment> comments);

    default CarLocation toCarLocation(CarCreateDto carCreateDto) {
        CarLocation carLocation = new CarLocation();
        carLocation.setLatitude(carCreateDto.getLocation().getLatitude());
        carLocation.setLongitude(carCreateDto.getLocation().getLongitude());
        return carLocation;
    }

}
