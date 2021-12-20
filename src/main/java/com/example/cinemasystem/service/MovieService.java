package com.example.cinemasystem.service;

import com.example.cinemasystem.dalInterfaces.IMovieDAL;
import com.example.cinemasystem.serviceInterfaces.IFileStorageService;
import com.example.cinemasystem.serviceInterfaces.IMovie;
import com.example.cinemasystem.serviceInterfaces.IMovieService;

import com.example.cinemasystem.model.Trailer;
import com.example.cinemasystem.model.request.MovieCreateRequest;
import com.example.cinemasystem.model.request.MovieEditRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

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
    public CompletableFuture<ResponseEntity> returnAllMovies()
    {
        CompletableFuture<List<IMovie>> movies = CompletableFuture.completedFuture(dal.getAllMovies());

        if(movies != null) {

            return movies.thenApply(ResponseEntity::ok);
        } else {

            return (CompletableFuture) ResponseEntity.notFound();
        }


    }
    @Override
    public ResponseEntity<IMovie> returnMovieById(int id)
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
    public String returnPhotoOfMovieByID(int id)
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
    public ResponseEntity<Trailer> returnTrailerOfMovieById(int id)
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
    public boolean addMovie(MovieCreateRequest movieCreateRequest)
    {

        boolean bool = false;
        if(dal.addMovie(movieCreateRequest)){
            bool= true;
        }
        int id = dal.getMovieIdByTitle(movieCreateRequest.getTitle());

        if ( dal.addTrailerToMovie(id, movieCreateRequest.getTrailer()))
        {
            bool = true;
        }
        return bool;
    }

    @Override
    public boolean editMovie(MovieEditRequest movieEditRequest)
    {
        boolean bool = false;
       if(dal.editMovie(movieEditRequest)){
        bool = true;
       }

        return bool;
    }
    @Override
    public boolean deleteMovie(int id){
        String photoPath =  dal.getPhotoByMovieId(id);
        dal.deleteMovie(id);
        dal.deletePosterOfMovie(id);
        dal.deleteTrailerOfMovie(id);
        fileStorageService.delete(photoPath);

        return true;
    }
}
