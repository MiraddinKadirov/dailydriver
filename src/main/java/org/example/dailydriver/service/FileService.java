package org.example.dailydriver.service;

import org.example.dailydriver.mapper.FileMapper;
import org.example.dailydriver.model.dto.fileDto.FileDto;
import org.example.dailydriver.model.entity.Car;
import org.example.dailydriver.model.entity.File;
import org.example.dailydriver.repository.CarRepository;
import org.example.dailydriver.repository.FileRepository;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FileService {

    private final FileRepository fileRepository;
    private final FileMapper fileMapper;
    private final CarRepository carRepository;

    public FileService(FileRepository fileRepository, FileMapper fileMapper, CarRepository carRepository) {
        this.fileRepository = fileRepository;
        this.fileMapper = fileMapper;
        this.carRepository = carRepository;
    }

    private final Path root = Paths.get("D:\\java ultimate\\10 Spring Boot Advanced\\file");

    public List<File> save(List<MultipartFile> file, String carId) {

        Car car1 = carRepository.findById(carId).orElse(null);
        List<File> savedFiles = new ArrayList<>();
        for (MultipartFile multipartFile : file) {
            String imagePath1 = saveMultiPart(multipartFile);
            File build = File.builder()
                    .originalName(multipartFile.getOriginalFilename())
                    .storedName(UUID.randomUUID() + multipartFile.getOriginalFilename())
                    .path(imagePath1)
                    .size(multipartFile.getSize())
                    .contentType(multipartFile.getContentType())
                    .car(carRepository.findById(carId).orElseThrow(() -> new RuntimeException("Car not found")))
                    .build();
            savedFiles.add(build);
        }
        assert car1 != null;
        car1.setFiles(savedFiles);
        carRepository.save(car1);
        fileRepository.saveAll(savedFiles);
        return savedFiles;
    }

    public List<File> update(List<MultipartFile> newFiles, String carId) {
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new RuntimeException("Car not found"));

        List<File> oldFiles = fileRepository.findAllByCar(carId);

        for (File oldFile : oldFiles) {
            java.io.File systemFile = new java.io.File(oldFile.getPath());
            if (systemFile.exists()) {
                systemFile.delete();
            }
        }

        fileRepository.deleteAll(oldFiles);

        List<File> savedFiles = new ArrayList<>();
        for (MultipartFile multipartFile : newFiles) {
            String storedPath = saveMultiPart(multipartFile);
            File file = File.builder()
                    .originalName(multipartFile.getOriginalFilename())
                    .storedName(UUID.randomUUID() + multipartFile.getOriginalFilename())
                    .path(storedPath)
                    .size(multipartFile.getSize())
                    .contentType(multipartFile.getContentType())
                    .car(car)
                    .build();
            savedFiles.add(file);
        }

        car.setFiles(savedFiles);
        carRepository.save(car);
        fileRepository.saveAll(savedFiles);

        return savedFiles;
    }


    public File getById(String id) {
        return fileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("File not found"));
    }

    public List<FileDto> getAll(String id) {
        List<File> allByCar = fileRepository.findAllByCar(id);
        return fileMapper.toDtoList(allByCar);
    }

    public void delete(String id) {
        File file = fileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("File not found"));
        try {
            Path path = Paths.get(file.getPath());
            Files.deleteIfExists(path);
        } catch (IOException e) {
            throw new RuntimeException("Could not delete file from disk: " + e.getMessage());
        }
        fileRepository.deleteById(id);
    }

    private String saveMultiPart(MultipartFile file) {

        try {
            long maxSize = 10 * 1024 * 1024;
            if (file.getSize() > maxSize) {
                throw new RuntimeException("file size is too big");
            }
            String originalFilename = file.getOriginalFilename();
            String storedName = UUID.randomUUID() + originalFilename;
            Path path = root.resolve(storedName);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            return path.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public ResponseEntity<Resource> downloadFile(String fileId) {
        File file = fileRepository.findById(fileId)
                .orElseThrow(() -> new RuntimeException("File not found"));

        Path filePath = Paths.get(file.getPath());

        Resource resource = loadAsResource(filePath);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getOriginalName() + "\"")
                .body(resource);
    }

    private Resource loadAsResource(Path filePath) {
        try {
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Faylni o‘qish mumkin emas: " + filePath);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Faylni yuklab olishda xatolik yuz berdi: " + filePath, e);
        }
    }

}
