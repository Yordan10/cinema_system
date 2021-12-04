package com.example.cinemasystem.MovieTests;

import com.example.cinemasystem.Enums.Genre;
import com.example.cinemasystem.Service.MovieService;
import com.example.cinemasystem.ServiceInterfaces.IMovie;
import com.example.cinemasystem.model.Movie;
import com.example.cinemasystem.model.Trailer;
import com.example.cinemasystem.model.request.MovieCreateRequest;
import com.example.cinemasystem.repository.MovieDalJDBC;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;


@SpringBootTest
public class MovieServiceUnitTests {

    @Autowired
    MovieService movieService;

    @MockBean
    MovieDalJDBC movieDalJDBC;

    @Test
    void getMovieByIdTest(){
        int id=1;
        Movie movie = new Movie();

        when(movieDalJDBC.getMovieById(id))
                .thenReturn(movie);

        //assert
        Assertions.assertEquals(new ResponseEntity(movie, HttpStatus.OK),movieService.returnMovieById(id));
    }

    @Test
     void getMovieByIdFailTest(){
        int id=1;

        when(movieDalJDBC.getMovieById(id))
                .thenReturn(null);

        Assertions.assertEquals(new ResponseEntity(HttpStatus.NOT_FOUND),movieService.returnMovieById(id));
    }
    @Test
    void getAllMoviesTest(){
        List<IMovie> movies = new ArrayList<>();
        Movie movie = new Movie(1,"title","e",1.0, Genre.Fantasy,10.0,"ds");
        movies.add(movie);

        when(movieDalJDBC.getAllMovies()).thenReturn(Stream.of(movie)
                .collect(Collectors.toList()));

        Assertions.assertEquals(new ResponseEntity(movies,HttpStatus.OK),movieService.returnAllMovies());
    }

    @Test
    void getAllMoviesFailTest(){


        when(movieDalJDBC.getAllMovies()).thenReturn(null);

        Assertions.assertEquals(new ResponseEntity(HttpStatus.NOT_FOUND),movieService.returnAllMovies());
    }
    @Test
    void getPosterOfMovieByIdTest(){
        int id=1;
        String path = "success";
        when(movieDalJDBC.getPhotoByMovieId(id))
                .thenReturn(path);

        Assertions.assertEquals(path,movieService.returnPhotoOfMovieByID(id));
    }

    @Test
    void getPosterOfMovieByIdFailTest(){
        int id=1;
        String path = "";
        when(movieDalJDBC.getPhotoByMovieId(id))
                .thenReturn(path);

        Assertions.assertEquals(path,movieService.returnPhotoOfMovieByID(id));
    }

    @Test
    void getTrailerOfMovieByIdTest(){
        int id = 1;
        Trailer trailer = new Trailer(1,"link");
        when(movieDalJDBC.getTrailerByMovieId(id))
                .thenReturn(trailer);

        Assertions.assertEquals(new ResponseEntity(trailer,HttpStatus.OK),movieService.returnTrailerOfMovieById(id));
    }

    @Test
    void getTrailerOfMovieByIdFailTest(){
        int id = 1;

        when(movieDalJDBC.getTrailerByMovieId(id))
                .thenReturn(null);

        Assertions.assertEquals(new ResponseEntity(HttpStatus.NOT_FOUND),movieService.returnTrailerOfMovieById(id));
    }

    @Test
    void addMovieTest(){
        MovieCreateRequest movieCreateRequest = new MovieCreateRequest();
        int id =10;

        when(movieDalJDBC.addMovie(movieCreateRequest)).thenReturn(true);
        when(movieDalJDBC.getMovieIdByTitle(movieCreateRequest.getTitle())).thenReturn(id);
        when(movieDalJDBC.addTrailerToMovie(id,movieCreateRequest.getTrailer())).thenReturn(true);

        Assertions.assertEquals(true,movieService.addMovie(movieCreateRequest));


    }


}
