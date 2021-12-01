package com.example.cinemasystem.controller;

import com.example.cinemasystem.Service.FileStorageService;
import com.example.cinemasystem.ServiceInterfaces.IMovie;
import com.example.cinemasystem.ServiceInterfaces.IMovieService;
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
import java.util.List;


@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private  IMovieService movieService ;

    @Autowired
    FileStorageService storageService;

    @GetMapping
    public ResponseEntity<List<IMovie>> GetAllMovies()
    {
        return movieService.ReturnAllMovies();
    }



    @GetMapping("{id}")
    public ResponseEntity<IMovie> GetMovieById(@PathVariable(value = "id") int id)
    {
        return movieService.ReturnMovieById(id);
    }


    @PostMapping("/upload/photo/{title}")
    public ResponseEntity UploadPhoto(@RequestParam(value = "file") MultipartFile file,@PathVariable(value = "title") String title)
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
    public ResponseEntity<Resource> GetMoviePhotoById(@PathVariable(value = "id") int id)
    {
        String filename =  movieService.ReturnPhotoOfMovieByID(id);
        ByteArrayResource inputStream = null;
        try{
            String directory = new File("./" ).getCanonicalPath() + "/photos/" + filename;

            inputStream = new ByteArrayResource(Files.readAllBytes(Paths.get(
                    directory)));
            return ResponseEntity.ok()
                    .contentLength(inputStream.contentLength())
                    .body(inputStream);

        }
        catch (Exception e){}

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/trailer/{id}")
    public ResponseEntity<Trailer> GetTrailerCharityById(@PathVariable(value = "id") int id)
    {
        return movieService.ReturnTrailerOfMovieById(id);
    }

    @PostMapping("addMovie")
    public ResponseEntity AddMovie (@RequestBody MovieCreateRequest movieCreateRequest)
    {
        movieService.AddMovie(movieCreateRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping("editMovie")
    public ResponseEntity EditMovie(@RequestBody MovieEditRequest movieEditRequest)
    {
        movieService.EditMovie(movieEditRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity DeleteMovie (@PathVariable(value = "id") int id)
    {
        movieService.DeleteMovie(id);
        return ResponseEntity.ok().build();
    }
}
