package com.example.cinemasystem.controller;

import com.example.cinemasystem.Service.MovieService;
import com.example.cinemasystem.ServiceInterfaces.IMovieService;
import com.example.cinemasystem.model.Movie;
import com.example.cinemasystem.Enums.Genre;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RequestMapping("/movies")
public class MovieController {

    private static final IMovieService movieService = new MovieService();

    @GetMapping
    public ResponseEntity<List<Movie>> GetAllMovies()
    {
        return movieService.ReturnAllMovies();
    }

    @GetMapping("{id}")
    public ResponseEntity<Movie> GetMovieById(@PathVariable(value = "id") int id)
    {
        return movieService.ReturnMovieById(id);
    }

    @DeleteMapping("{id}")
    public ResponseEntity DeleteMovie(@PathVariable int id) {

        return movieService.ReturnDeleteMovie(id);
    }

    @PostMapping()
    public ResponseEntity<Movie> AddMovie(@RequestBody Movie movie) {

        return this.movieService.CreateMovie(movie);

    }

    @PutMapping("{id}")
    public ResponseEntity<Movie> UpdateMovie(@PathVariable("id") int id, @RequestParam String title, @RequestParam Double length, @RequestParam Genre genre,@RequestParam Double rating, @RequestParam String director)
    {
        return movieService.ReturnUpdateMovie(id,title,length,genre,rating,director);
    }
}
