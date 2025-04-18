package org.example.dailydriver.mapper;

import org.example.dailydriver.model.dto.fileDto.FileCreateDto;
import org.example.dailydriver.model.dto.fileDto.FileDto;
import org.example.dailydriver.model.entity.Car;
import org.example.dailydriver.model.entity.File;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FileMapper {

    @Mapping(source = "car.id", target = "carId")
    FileDto toDto(File file);

    List<FileDto> toDtoList(List<File> files);

    @Mapping(target = "car", expression = "java(mapCarId(dto.getCarId()))")
    File toEntity(FileDto dto);

    List<File> toEntityList(List<FileDto> fileDtos);

   // @Mapping(target = "id", ignore = true)
    @Mapping(target = "car", expression = "java(mapCarId(dto.getCarId()))")
    File fromCreateDto(FileCreateDto dto);

    List<File> fromCreateDtoList(List<FileCreateDto> dtoList);

    default Car mapCarId(String carId) {
        if (carId == null) return null;
        Car car = new Car();
        car.setId(carId);
        return car;
    }
}
