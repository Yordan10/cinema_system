package com.example.cinemasystem.controller;

import com.example.cinemasystem.Service.MovieService;
import com.example.cinemasystem.ServiceInterfaces.IMovie;
import com.example.cinemasystem.ServiceInterfaces.IMovieService;
import com.example.cinemasystem.model.Movie;
import com.example.cinemasystem.Enums.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private  IMovieService movieService ;

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

    @GetMapping("/photo/{id}")
    public ResponseEntity<String> GetPhotoMovieById(@PathVariable(value = "id") int id)
    {
        return movieService.ReturnPhotoOfMovieByID(id);
    }
}
