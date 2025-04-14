package org.example.dailydriver.service;

import org.example.dailydriver.model.entity.File;
import org.example.dailydriver.repository.FileRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
public class FileService {

    private final FileRepository fileRepository;

    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    private final Path root = Paths.get("D:\\java ultimate\\10 Spring Boot Advanced\\file");

    public String save(MultipartFile file) {

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

    public File getById(String id) {
        return fileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("File not found"));
    }

    public List<File> getAll() {
        return fileRepository.findAll();
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

    public File update(String id, MultipartFile newFile) {
        File oldFile = fileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("File not found"));
        try {
            Files.deleteIfExists(Paths.get(oldFile.getPath()));
        } catch (IOException e) {
            throw new RuntimeException("Could not delete old file: " + e.getMessage());
        }
        String newPath = save(newFile);
        oldFile.setOriginalName(newFile.getOriginalFilename());
        oldFile.setStoredName(newPath.substring(newPath.lastIndexOf("/") + 1));
        oldFile.setPath(newPath);
        oldFile.setSize(newFile.getSize());
        oldFile.setContentType(newFile.getContentType());
        return fileRepository.save(oldFile);
    }

}
