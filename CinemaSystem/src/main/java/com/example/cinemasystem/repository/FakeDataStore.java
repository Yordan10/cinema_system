package com.example.cinemasystem.repository;

import com.example.cinemasystem.Interfaces.IAccount;
import com.example.cinemasystem.Logic.UserManager;
import com.example.cinemasystem.model.Movie;
import com.example.cinemasystem.model.UserAccount;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FakeDataStore {

    private final List<Movie> movieList = new ArrayList<>();
    public UserManager userManager;

    public FakeDataStore()
    {
        Movie fastAndFurious = new Movie(1,"Fast and furious",2.45,Genre.Action,7.5,"John Hill");
        Movie inception = new Movie(2,"Inception",2.59,Genre.Mystery,9.0,"Christopher Nolan");
        Movie lionKing = new Movie(3,"Lion King", 2.18,Genre.Drama,8.5,"Roger Allers");
        IAccount Yordan = new UserAccount(2,"yordan","pass","yor@","Yordan","Ivanov");

        userManager = new UserManager();
        userManager.userAccounts.add(Yordan);
        movieList.add(fastAndFurious);
        movieList.add(inception);
        movieList.add(lionKing);
    }



    public boolean checkUser(String username, String password) {
        for (IAccount user : this.userManager.userAccounts) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public IAccount GetAccountByUsername(String username) {
        for (IAccount account : this.userManager.userAccounts) {
            if (account.getUsername().equals(username))
                return account;
        }
        return null;
    }

    public IAccount GetAccountByEmail(String email) {
        for (IAccount account : this.userManager.userAccounts) {
            if (account.getEmail().equals(email))
                return account;
        }
        return null;
    }

    public int AddAccount(IAccount account) {
        if (this.GetAccountByUsername(account.getUsername()) != null ) {
            return -1;
        }else if(this.GetAccountByEmail(account.getEmail()) != null){
            return -2;
        }
        this.userManager.userAccounts.add(account);
        return 0;
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
