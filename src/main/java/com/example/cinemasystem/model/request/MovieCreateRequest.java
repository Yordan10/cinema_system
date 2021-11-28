package com.example.cinemasystem.model.request;

import com.example.cinemasystem.Enums.Genre;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class MovieCreateRequest {
    @Getter
    @Setter
    protected int id;

    @Getter @Setter
    protected   String title;

    @Getter @Setter
    protected   String description;

    @Getter @Setter
    protected  Double length;

    @Getter @Setter
    protected Genre genre;

    @Getter @Setter
    protected   Double rating;

    @Getter @Setter
    protected   String director;
}
