package com.example.cinemasystem.Service;

import com.example.cinemasystem.ServiceInterfaces.IFileStorageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service
public class FileStorageService implements IFileStorageService {
    private final Path root = Paths.get("photos");


    @Override
    public void save(MultipartFile file) {
        try {
            System.out.println(file.getOriginalFilename());
            Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }

    /*@Override
    public File load(String filename) {

      //  Path file = root.resolve(filename);

        File file1 =  new File(filename);
        return file1;

    }
    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
        } catch (IOException e) {
            throw new RuntimeException("Could not load the files!");
        }
    }*/

}
