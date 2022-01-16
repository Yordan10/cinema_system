package com.example.cinemasystem.MovieTests;

import com.example.cinemasystem.config.AuthenticationConfigConstants;
import com.example.cinemasystem.controller.MovieController;
import com.example.cinemasystem.enums.Genre;
import com.example.cinemasystem.model.Movie;
import com.example.cinemasystem.model.Trailer;
import com.example.cinemasystem.model.UserAccount;
import com.example.cinemasystem.model.request.MovieCreateRequest;
import com.example.cinemasystem.model.request.MovieEditRequest;
import com.example.cinemasystem.service.MovieService;
import com.example.cinemasystem.serviceInterfaces.IAccount;
import com.example.cinemasystem.serviceInterfaces.IMovie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;

import static org.mockito.Mockito.*;
@SpringBootTest
public class MovieControllerUnitTests {

    @Autowired
    MovieController movieController;

    @MockBean
    MovieService movieService;

    @Test
    void getMovieByIdTest(){


        int id = 10;
        IMovie movie = new Movie(10,"sadds","asdas",10, Genre.COMEDY,10.0,"lele");

        when(movieService.returnMovieById(id)).thenReturn(new ResponseEntity(movie, HttpStatus.OK));

        Assertions.assertEquals(new ResponseEntity(movie,HttpStatus.OK),movieController.getMovieById(id));
    }


    @Test
    void getTrailerByIdTest(){
        int id = 10;
        Trailer trailer = new Trailer();


        when(movieService.returnTrailerOfMovieById(id)).thenReturn(new ResponseEntity(trailer, HttpStatus.OK));

        Assertions.assertEquals(new ResponseEntity(trailer,HttpStatus.OK),movieController.getTrailerById(id));
    }

    @Test
    void getPhotoOfMovieFailTest(){
        int id =1;


        when(movieService.returnPhotoOfMovieByID(1)).thenReturn("");

        Assertions.assertEquals(new ResponseEntity(HttpStatus.NOT_FOUND),movieController.getMoviePhotoById(id));
    }
    @Test
    void getAddMovieTest(){
        int id =1;
        MovieCreateRequest movieCreateRequest = new MovieCreateRequest();

        when(movieService.addMovie(movieCreateRequest)).thenReturn(true);

        Assertions.assertEquals(new ResponseEntity(HttpStatus.OK),movieController.addMovie(movieCreateRequest));
    }
    @Test
    void getEditMovieTest(){
        int id =1;
        MovieEditRequest movieEditRequest = new MovieEditRequest();

        when(movieService.editMovie(movieEditRequest)).thenReturn(true);

        Assertions.assertEquals(new ResponseEntity(HttpStatus.OK),movieController.editMovie(movieEditRequest));
    }

    @Test
    void deleteMovieTest(){
        int id =1;


        when(movieService.deleteMovie(id)).thenReturn(true);

        Assertions.assertEquals(new ResponseEntity(HttpStatus.OK),movieController.deleteMovie(id));
    }

    @Test
    void savePosterTestFail(){

        MultipartFile file = null;
        String title = ":)";


        Assertions.assertEquals(new ResponseEntity(HttpStatus.BAD_REQUEST),movieController.uploadPhoto(file,title));
    }




}
