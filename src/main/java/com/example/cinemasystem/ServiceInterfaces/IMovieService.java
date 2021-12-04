package com.example.cinemasystem.ServiceInterfaces;

import com.example.cinemasystem.model.Movie;
import com.example.cinemasystem.Enums.Genre;
import com.example.cinemasystem.model.Trailer;
import com.example.cinemasystem.model.request.MovieCreateRequest;
import com.example.cinemasystem.model.request.MovieEditRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface IMovieService {

    CompletableFuture<List<IMovie>> returnAllMovies();
    ResponseEntity<IMovie> returnMovieById(int id);
    String returnPhotoOfMovieByID(int id);
    ResponseEntity<Trailer> returnTrailerOfMovieById(int id);
    boolean addMovie(MovieCreateRequest movieCreateRequest);
    boolean editMovie(MovieEditRequest movieEditRequest);
    void deleteMovie(int id);

}
