package com.example.cinemasystem.serviceInterfaces;

import com.example.cinemasystem.model.Trailer;
import com.example.cinemasystem.model.request.MovieCreateRequest;
import com.example.cinemasystem.model.request.MovieEditRequest;
import org.springframework.http.ResponseEntity;

import java.util.concurrent.CompletableFuture;

public interface IMovieService {

    CompletableFuture<ResponseEntity> returnAllMovies();
    ResponseEntity<IMovie> returnMovieById(int id);
    String returnPhotoOfMovieByID(int id);
    ResponseEntity<Trailer> returnTrailerOfMovieById(int id);
    boolean addMovie(MovieCreateRequest movieCreateRequest);
    boolean editMovie(MovieEditRequest movieEditRequest);
    boolean deleteMovie(int id);

}
