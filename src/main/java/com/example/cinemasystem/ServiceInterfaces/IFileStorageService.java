package com.example.cinemasystem.ServiceInterfaces;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Path;


public interface IFileStorageService {

    public void save(MultipartFile file, String title);
    void delete(String path);


}
