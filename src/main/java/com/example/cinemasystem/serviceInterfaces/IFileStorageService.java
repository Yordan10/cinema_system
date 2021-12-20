package com.example.cinemasystem.serviceInterfaces;

import org.springframework.web.multipart.MultipartFile;


public interface IFileStorageService {

    public void save(MultipartFile file, String title);
    void delete(String path);


}
