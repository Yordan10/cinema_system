package com.example.cinemasystem.model;

import com.example.cinemasystem.enums.Genre;
import com.example.cinemasystem.serviceInterfaces.IMovie;
import lombok.Getter;
import lombok.Setter;

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

}
