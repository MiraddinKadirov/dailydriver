package org.example.dailydriver.mapper;

import org.example.dailydriver.model.dto.carDto.CarCreateDto;
import org.example.dailydriver.model.dto.carDto.CarDto;
import org.example.dailydriver.model.dto.carDto.CarUpdateDto;
import org.example.dailydriver.model.entity.Car;
import org.example.dailydriver.model.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Set;

@Mapper
public interface CarMapper {

    Car toEntity(CarDto carDto);
    Car toEntity(CarCreateDto carCreateDto);
    Car toEntity(CarUpdateDto carUpdateDto);

    @Mapping(target = "comments", source = "comments")
    CarDto toDto(Car car);

    List<Comment> commentsToList(Set<Comment> comments);

}
