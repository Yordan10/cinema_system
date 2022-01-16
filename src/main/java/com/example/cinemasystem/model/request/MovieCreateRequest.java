package com.example.cinemasystem.model.request;

import com.example.cinemasystem.enums.Genre;
import lombok.Data;
import lombok.Getter;

@Getter
public class MovieCreateRequest {

    protected int id;


    protected   String title;

    protected   String description;

    protected  Double length;

    protected Genre genre;

    protected   Double rating;

    protected   String director;

    protected String trailer;
}
