package com.example.cinemasystem.Service;

import com.example.cinemasystem.DALInterfaces.IMovieDAL;
import com.example.cinemasystem.ServiceInterfaces.IMovie;
import com.example.cinemasystem.ServiceInterfaces.IMovieService;
import com.example.cinemasystem.model.Movie;

import com.example.cinemasystem.Enums.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService implements IMovieService {


    IMovieDAL dal;

    @Autowired
    public MovieService(IMovieDAL dal)
    {
      this.dal = dal;
    }


    @Override
    public ResponseEntity<List<IMovie>> ReturnAllMovies()
    {
        if (dal.getAllMovies() == null)
        {
            return ResponseEntity.notFound().build();
        }
        else
        {
            return ResponseEntity.ok().body(dal.getAllMovies());
        }

    }
    @Override
    public ResponseEntity<IMovie> ReturnMovieById(int id)
    {
        IMovie movie = dal.getMovieById(id);
        if (movie == null)
        {
            return ResponseEntity.notFound().build();
        }
        else
        {
            return ResponseEntity.ok().body(movie);
        }
    }
    @Override
    public String ReturnPhotoOfMovieByID(int id)
    {
        String path = dal.getPhotoByMovieId(id);
        if (path == "")
        {
            return "";
        }
        else
        {
            return path;
        }
    }

}
