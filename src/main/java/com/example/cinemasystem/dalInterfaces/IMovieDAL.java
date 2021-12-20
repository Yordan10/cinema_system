package com.example.cinemasystem.dalInterfaces;

import com.example.cinemasystem.serviceInterfaces.IMovie;
import com.example.cinemasystem.model.Trailer;
import com.example.cinemasystem.model.request.MovieCreateRequest;
import com.example.cinemasystem.model.request.MovieEditRequest;

import java.util.List;

public interface IMovieDAL {
    List<IMovie> getAllMovies();
    IMovie getMovieById(int id);
    String getPhotoByMovieId(int id);
    Trailer getTrailerByMovieId(int id);
    boolean addMovie(MovieCreateRequest movieCreateRequest);
    int getMovieIdByTitle (String title);
    boolean addTrailerToMovie(int movieId,String trailer);
    boolean addPosterToMovie(String path,int movieId);
    boolean editMovie(MovieEditRequest movieEditRequest);
    void deleteMovie(int movieId);
    void deletePosterOfMovie(int movieId);
    void deleteTrailerOfMovie(int movieId);
}
