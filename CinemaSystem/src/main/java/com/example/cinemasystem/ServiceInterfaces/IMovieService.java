package com.example.cinemasystem.ServiceInterfaces;

import com.example.cinemasystem.model.Movie;
import com.example.cinemasystem.Enums.Genre;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IMovieService {

    ResponseEntity CreateMovie(Movie movie);
    ResponseEntity<List<Movie>> ReturnAllMovies();
    ResponseEntity<Movie> ReturnMovieById(int id);
    ResponseEntity ReturnDeleteMovie(int id);
    ResponseEntity<Movie> ReturnUpdateMovie(int id, String title, Double length, Genre genre, Double rating, String director);

}
