package com.kanbanflow.kanban_api.service.impl;

import com.kanbanflow.kanban_api.service.FileStorageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    private final Path rootLocation = Paths.get("uploads");

    public FileStorageServiceImpl() {
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize storage location",e);
        }
    }

    @Override
    public String storeFile(MultipartFile file) {
        try {
            if (file.isEmpty()){
                throw new RuntimeException("Failed to store file");
            }
            String fileName = UUID.randomUUID().toString()+"_"+file.getOriginalFilename();
            Path destinationFile = this.rootLocation.resolve(Paths.get(fileName)).normalize().toAbsolutePath();
            Files.copy(file.getInputStream(),destinationFile);
            return fileName;
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file.", e);
        }
    }
}
