package com.example.cinemasystem.Service;

import com.example.cinemasystem.DALInterfaces.IMovieDAL;
import com.example.cinemasystem.ServiceInterfaces.IFileStorageService;
import com.example.cinemasystem.ServiceInterfaces.IMovie;
import com.example.cinemasystem.ServiceInterfaces.IMovieService;
import com.example.cinemasystem.model.Movie;

import com.example.cinemasystem.Enums.Genre;
import com.example.cinemasystem.model.Trailer;
import com.example.cinemasystem.model.request.MovieCreateRequest;
import com.example.cinemasystem.model.request.MovieEditRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService implements IMovieService {

   private IFileStorageService fileStorageService;
   private IMovieDAL dal;

    @Autowired
    public MovieService(IMovieDAL dal,IFileStorageService fileStorageService)
    {

        this.dal = dal;
        this.fileStorageService = fileStorageService;
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
        if (path.equals(""))
        {
            return "";
        }
        else
        {
            return path;
        }
    }

    @Override
    public ResponseEntity<Trailer> ReturnTrailerOfMovieById(int id)
    {
        Trailer trailer = dal.getTrailerByMovieId(id);
        if (trailer==null)
        {
            return ResponseEntity.notFound().build();
        }
        else
        {
            return  ResponseEntity.ok().body(trailer);
        }
    }

    @Override
    public boolean AddMovie(MovieCreateRequest movieCreateRequest)
    {

        boolean bool = false;
        if(dal.AddMovie(movieCreateRequest)){
            bool= true;
        }
        int id = dal.getMovieIdByTitle(movieCreateRequest.getTitle());

        if ( dal.AddTrailerToMovie(id, movieCreateRequest.getTrailer()))
        {
            bool = true;
        }
        return bool;
    }

    @Override
    public boolean EditMovie(MovieEditRequest movieEditRequest)
    {
        boolean bool = false;
       if(dal.EditPosterOfMovie(movieEditRequest)){
        bool = true;
       }

        return bool;
    }
    @Override
    public void DeleteMovie(int id){
        String photoPath =  dal.getPhotoByMovieId(id);
        dal.DeleteMovie(id);
        dal.DeletePosterOfMovie(id);
        dal.DeleteTrailerOfMovie(id);
        fileStorageService.delete(photoPath);


    }
}
