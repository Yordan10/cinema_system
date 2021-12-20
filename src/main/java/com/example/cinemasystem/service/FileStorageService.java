package com.example.cinemasystem.service;

import com.example.cinemasystem.dalInterfaces.IMovieDAL;
import com.example.cinemasystem.serviceInterfaces.IFileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileStorageService implements IFileStorageService {
    private final Path root = Paths.get("photos");


    IMovieDAL dal;

    @Autowired
    public FileStorageService(IMovieDAL dal)
    {
        this.dal = dal;
    }
    @Override
    public void save(MultipartFile file,String title) {
        try {

            Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
           int id= dal.getMovieIdByTitle(title);
           dal.addPosterToMovie(file.getOriginalFilename(), id);

        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }


    }
    @Override
    public void delete(String path)
    {
        try{
            Path test = Paths.get("/photos/"+ path);
            String directory = new File("./" ).getCanonicalPath() + test;
            Path finalPath = Paths.get(directory);

            Files.delete(finalPath);
        }
        catch (Exception ex){ex.getMessage();}


    }



}
