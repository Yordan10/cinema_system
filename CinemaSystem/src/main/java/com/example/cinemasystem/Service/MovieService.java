package com.example.cinemasystem.Service;

import com.example.cinemasystem.ServiceInterfaces.IMovieService;
import com.example.cinemasystem.model.Movie;

import com.example.cinemasystem.Enums.Genre;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;



import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class MovieService implements IMovieService {

    public List<Movie> movies;

    public MovieService()
    {
        this.movies= new ArrayList<>();
        Movie fastAndFurious = new Movie(1,"Fast and furious",2.45,Genre.Action,7.5,"John Hill");
        Movie inception = new Movie(2,"Inception",2.59,Genre.Mystery,9.0,"Christopher Nolan");
        Movie lionKing = new Movie(3,"Lion King", 2.18,Genre.Drama,8.5,"Roger Allers");


        movies.add(fastAndFurious);
        movies.add(inception);
        movies.add(lionKing);
    }
    public  List<Movie> GetMovies(){return  movies;}

    public List<Movie> GetMovieByGenre(Genre genre)
    {
        List<Movie> filtered = new ArrayList<>();
        for (Movie movie: movies)
        {
            if (movie.getGenre().equals(genre))
            {
                filtered.add(movie);
            }
        }
        return filtered;
    }

    public Movie GetMovieById (int id)
    {
        for (Movie movie: movies) {
            if (movie.getId() == id) {
                return movie;
            }
        }
        return null;
    }
    public Movie GetMovieByTitle (String title)
    {
        for (Movie movie: movies) {
            if (movie.getTitle().equals(title)) {
                return movie;
            }
        }
        return null;
    }
    public boolean DeleteMovie(int id)
    {
        Movie movie = GetMovieById(id);
        if (movie == null){
            return false;
        }
        movies.remove(movie);
        return true;
    }
    public boolean AddMovie(Movie movie)
    {
        if(this.GetMovieById(movie.getId())==null)
        {
            return false;
        }
        movies.add((movie));
        return true;
    }
    public boolean UpdateMovie(Movie movie)
    {
        Movie oldMovie = this.GetMovieById(movie.getId());
        if (oldMovie == null)
        {
            return false;
        }
        oldMovie.setTitle(movie.getTitle());
        oldMovie.setDirector(movie.getDirector());
        oldMovie.setGenre(movie.getGenre());
        oldMovie.setLength(movie.getLength());
        oldMovie.setRating(movie.getRating());
        return true;
    }
    public ResponseEntity CreateMovie(Movie movie)
    {
        if (!AddMovie(movie)){
            String entity =  "Movie " + movie.getTitle() + " already exists.";
            return new ResponseEntity(entity, HttpStatus.CONFLICT);
        } else {
            String url = "movie" + movie.getTitle(); // url of the created student
            URI uri = URI.create(url);
            return new ResponseEntity(uri,HttpStatus.CREATED);
        }
    }

    public ResponseEntity<List<Movie>> ReturnAllMovies()
    {
        return ResponseEntity.ok().body(GetMovies());
    }

    public ResponseEntity<Movie> ReturnMovieById(int id)
    {
        Movie movie = GetMovieById(id);
        if (movie == null)
        {
            return ResponseEntity.notFound().build();
        }
        else
        {
            return ResponseEntity.ok().body(movie);
        }
    }

    public ResponseEntity ReturnDeleteMovie(int id) {

        DeleteMovie(id);
        return ResponseEntity.ok().build();

    }
    public ResponseEntity<Movie> ReturnUpdateMovie( int id,String title, Double length, Genre genre, Double rating,String director)
    {
        Movie movie = new Movie(id,title,length,genre,rating,director);
        if (UpdateMovie(movie))
        {
            return  ResponseEntity.noContent().build();
        }
        else
        {
            return new ResponseEntity("Please provide a id", HttpStatus.NOT_FOUND);
        }
    }
}
