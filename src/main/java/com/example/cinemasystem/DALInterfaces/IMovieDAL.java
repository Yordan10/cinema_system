package com.example.cinemasystem.DALInterfaces;

import com.example.cinemasystem.ServiceInterfaces.IMovie;

import java.util.ArrayList;

public interface IMovieDAL {
    ArrayList<IMovie> getAllMovies();
    IMovie getMovieById(int id);
}
