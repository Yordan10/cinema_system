package com.example.cinemasystem.MovieTests;

import com.example.cinemasystem.enums.Genre;
import com.example.cinemasystem.service.MovieService;
import com.example.cinemasystem.controller.MovieController;
import com.example.cinemasystem.model.Movie;
import com.example.cinemasystem.model.Trailer;
import com.example.cinemasystem.model.request.MovieCreateRequest;
import com.example.cinemasystem.model.request.MovieEditRequest;
import com.example.cinemasystem.repository.MovieDalJDBC;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import static org.mockito.Mockito.*;


@SpringBootTest
public class MovieServiceUnitTests {

    @Autowired
    MovieService movieService;

    @Autowired
    MovieController movieController;

    @MockBean
    MovieDalJDBC movieDalJDBC;

    @Test
    void getMovieByIdTest(){
        int id=1;
        Movie movie = new Movie(1,"jkt","js",10,Genre.COMEDY,10.2,"sjkf");

        movie.getDescription();
        movie.getTitle();
        movie.getGenre();
        movie.getId();
        movie.getLength();
        movie.getRating();
        movie.getDirector();


        when(movieDalJDBC.getMovieById(id))
                .thenReturn(movie);

        //assert
        Assertions.assertEquals(new ResponseEntity(movie, HttpStatus.OK),movieService.returnMovieById(id));
    }

    @Test
     void getMovieByIdFailTest(){
        int id=1;
Movie movie = new Movie();
        when(movieDalJDBC.getMovieById(id))
                .thenReturn(null);

        Assertions.assertEquals(new ResponseEntity(HttpStatus.NOT_FOUND),movieService.returnMovieById(id));
    }
//    @Test
//    void getAllMoviesTest(){
//        List<IMovie> movies = new ArrayList<>();
//        Movie movie = new Movie(1,"title","e",1.0, Genre.Fantasy,10.0,"ds");
//        movies.add(movie);
//
//        when(movieDalJDBC.getAllMovies()).thenReturn(Stream.of(movie)
//                .collect(Collectors.toList()));
//
//        Assertions.assertEquals(new ResponseEntity(movies,HttpStatus.OK),movieService.returnAllMovies());
//    }

//    @Test
//    void getAllMoviesFailTest(){
//
//
//        when(movieDalJDBC.getAllMovies()).thenReturn(null);
//
//        Assertions.assertEquals(new ResponseEntity(HttpStatus.NOT_FOUND),movieService.returnAllMovies());
//    }
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
        Trailer trailer1 = new Trailer();
        Trailer trailer = new Trailer(1,"link");
        trailer.getId();
        trailer.getVideoLink();
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

        movieCreateRequest.getDescription();
        movieCreateRequest.getDirector();
        movieCreateRequest.getGenre();
        movieCreateRequest.getLength();
        movieCreateRequest.getRating();
        movieCreateRequest.getId();
        movieCreateRequest.getTrailer();
        movieCreateRequest.getTitle();

        when(movieDalJDBC.addMovie(movieCreateRequest)).thenReturn(true);
        when(movieDalJDBC.getMovieIdByTitle(movieCreateRequest.getTitle())).thenReturn(id);
        when(movieDalJDBC.addTrailerToMovie(id,movieCreateRequest.getTrailer())).thenReturn(true);

        Assertions.assertEquals(true,movieService.addMovie(movieCreateRequest));


    }

    @Test
    void editMovieTest(){

        Genre genre = Genre.ACTION;
        MovieEditRequest movieEditRequest = new MovieEditRequest();
        movieEditRequest.setDescription("jf");
        movieEditRequest.setDirector("shj");
        movieEditRequest.setId(1);
        movieEditRequest.setGenre(genre);
        movieEditRequest.setLength(10.2);
        movieEditRequest.setTitle("dfhjf");

        movieEditRequest.getTitle();
       movieEditRequest.getDescription();
        movieEditRequest.getDirector();
         movieEditRequest.getId();
       movieEditRequest.getLength();
         movieEditRequest.getGenre();
         movieEditRequest.getRating();

        when(movieDalJDBC.editMovie(movieEditRequest)).thenReturn(true);

        Assertions.assertEquals(true,movieService.editMovie(movieEditRequest));

    }
@Test
    void deleteMovie(){
        int id = 1;
        String photo = "sfjk";

        when(movieDalJDBC.getPhotoByMovieId(id)).thenReturn(photo);

        Assertions.assertEquals(true,movieService.deleteMovie(id));
}

@Test
    void savePosterTestFail(){

    MultipartFile file = null;
    String title = ":)";

//    int randomNum = 1;
//    when(movieDalJDBC.getMovieIdByTitle(title)).thenReturn(randomNum);
//    when(movieDalJDBC.addPosterToMovie("asd",randomNum)).thenReturn(true);
    Assertions.assertEquals(new ResponseEntity(HttpStatus.BAD_REQUEST),movieController.uploadPhoto(file,title));
}
}
