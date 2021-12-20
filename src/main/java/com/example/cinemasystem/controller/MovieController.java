package com.example.cinemasystem.controller;

import com.example.cinemasystem.service.FileStorageService;
import com.example.cinemasystem.serviceInterfaces.IMovie;
import com.example.cinemasystem.serviceInterfaces.IMovieService;
import com.example.cinemasystem.model.Trailer;
import com.example.cinemasystem.model.request.MovieCreateRequest;
import com.example.cinemasystem.model.request.MovieEditRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.CompletableFuture;


@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private  IMovieService movieService ;

    @Autowired
    FileStorageService storageService;

    @GetMapping
    public CompletableFuture<ResponseEntity> getAllMovies()
    {
       return movieService.returnAllMovies();
    }



    @GetMapping("{id}")
    public ResponseEntity<IMovie> getMovieById(@PathVariable(value = "id") int id)
    {
        return movieService.returnMovieById(id);
    }


    @PostMapping("/upload/photo/{title}")
    public ResponseEntity uploadPhoto(@RequestParam(value = "file") MultipartFile file,@PathVariable(value = "title") String title)
    {
        try{
            storageService.save(file,title);

            return ResponseEntity.ok().body("File uploaded");
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().build();
        }

    }



    @GetMapping("/photo/{id}")
    public ResponseEntity<Resource> getMoviePhotoById(@PathVariable(value = "id") int id)
    {
        String filename =  movieService.returnPhotoOfMovieByID(id);
        ByteArrayResource inputStream = null;
        try{
            String directory = new File("./" ).getCanonicalPath() + "/photos/" + filename;

            inputStream = new ByteArrayResource(Files.readAllBytes(Paths.get(
                    directory)));
            return ResponseEntity.ok()
                    .contentLength(inputStream.contentLength())
                    .body(inputStream);

        }
        catch (Exception e){e.getMessage();}

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/trailer/{id}")
    public ResponseEntity<Trailer> getTrailerCharityById(@PathVariable(value = "id") int id)
    {
        return movieService.returnTrailerOfMovieById(id);
    }

    @PostMapping("addMovie")
    public ResponseEntity addMovie (@RequestBody MovieCreateRequest movieCreateRequest)
    {
        movieService.addMovie(movieCreateRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping("editMovie")
    public ResponseEntity editMovie(@RequestBody MovieEditRequest movieEditRequest)
    {
        movieService.editMovie(movieEditRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity deleteMovie (@PathVariable(value = "id") int id)
    {
        movieService.deleteMovie(id);
        return ResponseEntity.ok().build();
    }
}
