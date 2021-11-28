package com.example.cinemasystem.DALInterfaces;

import com.example.cinemasystem.ServiceInterfaces.IMovie;
import com.example.cinemasystem.model.Trailer;
import com.example.cinemasystem.model.request.MovieCreateRequest;

import java.util.ArrayList;

public interface IMovieDAL {
    ArrayList<IMovie> getAllMovies();
    IMovie getMovieById(int id);
    String getPhotoByMovieId(int id);
    Trailer getTrailerByMovieId(int id);
    void AddMovie(MovieCreateRequest movieCreateRequest);
}
