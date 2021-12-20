package com.example.cinemasystem.model.request;

import com.example.cinemasystem.enums.Genre;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter @Setter
public class MovieEditRequest {

    protected int id;
    protected   String title;
    protected   String description;
    protected  Double length;
    protected Genre genre;
    protected   Double rating;
    protected   String director;
}
