package org.example.dailydriver.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.example.dailydriver.model.dto.fileDto.FileDto;
import org.example.dailydriver.model.entity.Car;
import org.example.dailydriver.model.entity.File;
import org.example.dailydriver.repository.CarRepository;
import org.example.dailydriver.service.FileService;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/file")

public class FileController {

    private final FileService fileService;
    private final CarRepository carRepository;

    public FileController(FileService fileService, CarRepository carRepository) {
        this.fileService = fileService;
        this.carRepository = carRepository;
    }


    @GetMapping("/{id}")
    public ResponseEntity<List<FileDto>> getAllFiles(@PathVariable String id) {
        return ResponseEntity.ok(fileService.getAll(id));
    }

    @Operation(summary = "Faylni yuklab olish", description = "Berilgan fayl ID bo‘yicha faylni yuklab olish")
    @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) {
        return fileService.downloadFile(fileId);
    }

    @PostMapping(path = "/{id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Car> uploadFile(@PathVariable String id,
                                          @RequestParam List<MultipartFile> file) {
        List<File> save = fileService.save(file, id);
        Car car = carRepository.findByIdAndNotDeleted(id).orElse(null);
        assert car != null;
        car.setFiles(save);
        carRepository.save(car);
        return ResponseEntity.ok(car);
    }

    @PutMapping("/{id}")
    public void updateFile(@RequestParam List<MultipartFile> files,
                           @PathVariable String id) {
        fileService.update(files, id);
    }

    @DeleteMapping("/{id}")
    public void deleteFile(@PathVariable String id) {
        fileService.delete(id);
    }

}
