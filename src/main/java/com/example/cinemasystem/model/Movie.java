package com.example.cinemasystem.model;

import com.example.cinemasystem.Enums.Genre;
import com.example.cinemasystem.ServiceInterfaces.IMovie;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter @Setter
public class Movie implements IMovie {


    private  int id;

    private String title;

    private String description;

    private int length;

    private Genre genre;

    private Double rating;

    private String director;

    public Movie(int id,String title,String description,int length,Genre genre, Double rating,String director)
    {
        this.id=id;
        this.title=title;
        this.description=description;
        this.length = length;
        this.genre = genre;
        this.rating = rating;
        this.director = director;
    }
    public Movie()
    {

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(id, movie.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }



    @Override
    public String toString() {
        return "Movie: " + title +
                ", length: " + length +
                " has rating: " + rating +
                ", directed by " +
                director;

    }
}
