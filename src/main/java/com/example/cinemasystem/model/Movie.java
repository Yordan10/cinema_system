package com.example.cinemasystem.model;

import com.example.cinemasystem.Enums.Genre;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;


public class Movie {

    @Getter @Setter
    private  int id;
    @Getter @Setter
    private String title;
    @Getter @Setter
    private Double length;
    @Getter @Setter
    private Genre genre;
    @Getter @Setter
    private Double rating;
    @Getter @Setter
    private String director;

    public Movie(int id,String title,Double length,Genre genre, Double rating,String director)
    {
        this.id=id;
        this.title=title;
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
