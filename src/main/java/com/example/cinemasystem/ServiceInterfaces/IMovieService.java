package com.example.cinemasystem.ServiceInterfaces;

import com.example.cinemasystem.model.Movie;
import com.example.cinemasystem.Enums.Genre;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IMovieService {

    ResponseEntity<List<IMovie>> ReturnAllMovies();
    ResponseEntity<IMovie> ReturnMovieById(int id);

}
