package org.example.dailydriver.service;

import jakarta.transaction.Transactional;
import org.example.dailydriver.mapper.CarMapper;
import org.example.dailydriver.model.dto.carDto.CarCreateDto;
import org.example.dailydriver.model.dto.carDto.CarDto;
import org.example.dailydriver.model.dto.carDto.CarUpdateDto;
import org.example.dailydriver.model.entity.Car;
import org.example.dailydriver.model.entity.CarLocation;
import org.example.dailydriver.model.entity.File;
import org.example.dailydriver.repository.CarLocationRepository;
import org.example.dailydriver.repository.CarRepository;
import org.example.dailydriver.repository.FileRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CarService implements CrudService<CarCreateDto, CarUpdateDto, CarDto, String> {

    private final CarRepository carRepository;
    private final CarMapper carMapper;
    private final FileService fileService;
    private final FileRepository fileRepository;
    private final CarLocationRepository carLocationRepository;

    public CarService(CarRepository carRepository, CarMapper carMapper, FileService fileService, FileRepository fileRepository, CarLocationRepository carLocationRepository) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
        this.fileService = fileService;
        this.fileRepository = fileRepository;
        this.carLocationRepository = carLocationRepository;
    }

   /* @Override
    public CarDto save(CarCreateDto entity) {
        Car entity1 = carMapper.toEntity(entity);
        carRepository.save(entity1);
        return carMapper.toDto(entity1);
    }*/

    @Transactional
    @Override
    public CarDto update(CarUpdateDto entity, String id) {

        Car updateCar = carRepository.findByIdAndNotDeleted(id)
                .orElseThrow(() -> new RuntimeException("Car not found"));

        carMapper.updateCarFromDto(entity, updateCar);
        List<File> existingFiles = fileRepository.findAllById(entity.getExistingFileIds());

        List<File> newFiles = new ArrayList<>();
        for (MultipartFile multipartFile : entity.getFile()) {
            String path = fileService.save(multipartFile);
            File file = File.builder()
                    .originalName(multipartFile.getOriginalFilename())
                    .storedName(UUID.randomUUID() + multipartFile.getOriginalFilename())
                    .path(path)
                    .size(multipartFile.getSize())
                    .contentType(multipartFile.getContentType())
                    .car(updateCar)
                    .build();
            newFiles.add(file);
        }

        List<File> allFiles = new ArrayList<>();
        allFiles.addAll(existingFiles);
        allFiles.addAll(newFiles);
        fileRepository.saveAll(allFiles);

        if (entity.getLocation() != null) {
            if (updateCar.getLocation() == null) {
                updateCar.setLocation(new CarLocation());
            }
            updateCar.getLocation().setLatitude(entity.getLocation().getLatitude());
            updateCar.getLocation().setLongitude(entity.getLocation().getLongitude());
        }

        updateCar.setUpdatedAt(LocalDateTime.now());
        updateCar.setUpdatedBy("ADMIN");

        return carMapper.toDto(carRepository.save(updateCar));
    }

    @Override
    public Boolean delete(String id) {
        Car car = carRepository.findByIdAndNotDeleted(id).orElse(null);
        if (car == null) {
            return false;
        }
        car.setDeleted(true);
        carRepository.save(car);
        return true;
    }

    @Override
    public CarDto findById(String id) {
        Car car = carRepository.findByIdAndDeletedFalseAndAvailableFalseAndActiveTrue(id);
        return carMapper.toDto(car);
    }

    @Override
    public List<CarDto> findAll() {
        List<Car> cars = carRepository.findAllByDeletedFalseAndAvailableFalseAndActiveTrue();
        return carMapper.toDtoList(cars);
    }

    @Override
    public CarDto save(CarCreateDto entity) {

        List<MultipartFile> files = entity.getFiles();
        carLocationRepository.save(entity.getLocation());

        Car car = carMapper.toEntity(entity);

        List<File> savedFiles = new ArrayList<>();
        for (MultipartFile multipartFile : files) {
            String imagePath1 = fileService.save(multipartFile);
            File build = File.builder()
                    .originalName(multipartFile.getOriginalFilename())
                    .storedName(UUID.randomUUID() + multipartFile.getOriginalFilename())
                    .path(imagePath1)
                    .size(multipartFile.getSize())
                    .contentType(multipartFile.getContentType())
                    .car(car)
                    .build();
            savedFiles.add(build);
        }

        car.setFiles(savedFiles);

        carRepository.save(car);

        fileRepository.saveAll(savedFiles);

        return carMapper.toDto(car);
    }

}
