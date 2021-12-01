package com.example.cinemasystem.DALInterfaces;

import com.example.cinemasystem.ServiceInterfaces.IMovie;
import com.example.cinemasystem.model.Trailer;
import com.example.cinemasystem.model.request.MovieCreateRequest;
import com.example.cinemasystem.model.request.MovieEditRequest;

import java.util.ArrayList;
import java.util.List;

public interface IMovieDAL {
    List<IMovie> getAllMovies();
    IMovie getMovieById(int id);
    String getPhotoByMovieId(int id);
    Trailer getTrailerByMovieId(int id);
    boolean AddMovie(MovieCreateRequest movieCreateRequest);
    int getMovieIdByTitle (String title);
    boolean AddTrailerToMovie(int movieId,String trailer);
    boolean AddPosterToMovie(String path,int movieId);
    boolean EditPosterOfMovie(MovieEditRequest movieEditRequest);
    void DeleteMovie(int movieId);
    void DeletePosterOfMovie(int movieId);
    void DeleteTrailerOfMovie(int movieId);
}
