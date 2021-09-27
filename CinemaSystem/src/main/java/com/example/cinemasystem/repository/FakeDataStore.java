package com.example.cinemasystem.repository;

import com.example.cinemasystem.model.Movie;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FakeDataStore {

    private final List<Movie> movieList = new ArrayList<>();

    public FakeDataStore()
    {
        Movie fastAndFurious = new Movie(1,"Fast and furious",2.45,Genre.Action,7.5,"John Hill");
        Movie inception = new Movie(2,"Inception",2.59,Genre.Mystery,9.0,"Christopher Nolan");
        Movie lionKing = new Movie(3,"Lion King", 2.18,Genre.Drama,8.5,"Roger Allers");

        movieList.add(fastAndFurious);
        movieList.add(inception);
        movieList.add(lionKing);
    }

    public  List<Movie> GetMovies(){return  movieList;}

    public List<Movie> GetMovieByGenre(Genre genre)
    {
     List<Movie> filtered = new ArrayList<>();
     for (Movie movie: movieList)
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
        for (Movie movie: movieList) {
            if (movie.getId() == id) {
                return movie;
            }
        }
        return null;
    }
    public Movie GetMovieByTitle (String title)
    {
        for (Movie movie: movieList) {
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
        return movieList.remove(movie);
    }
    public boolean AddMovie(Movie movie)
    {
        if(this.GetMovieById(movie.getId())==null)
        {
            return false;
        }
        movieList.add((movie));
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
}
