package com.example.cinemasystem.ServiceInterfaces;

import com.example.cinemasystem.model.Movie;
import com.example.cinemasystem.Enums.Genre;
import com.example.cinemasystem.model.Trailer;
import com.example.cinemasystem.model.request.MovieCreateRequest;
import com.example.cinemasystem.model.request.MovieEditRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface IMovieService {

    ResponseEntity<List<IMovie>> ReturnAllMovies();
    ResponseEntity<IMovie> ReturnMovieById(int id);
    String ReturnPhotoOfMovieByID(int id);
    ResponseEntity<Trailer> ReturnTrailerOfMovieById(int id);
    boolean AddMovie(MovieCreateRequest movieCreateRequest);
    boolean EditMovie(MovieEditRequest movieEditRequest);
    void DeleteMovie(int id);

}
