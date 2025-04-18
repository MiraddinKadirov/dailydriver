package org.example.dailydriver.mapper;

import org.example.dailydriver.model.dto.carratingDto.CarRatingDto;
import org.example.dailydriver.model.entity.CarRating;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarRatingMapper {

    CarRating toEntity(CarRatingDto dto);


}
