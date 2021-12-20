package com.example.cinemasystem.model;

import lombok.Getter;

public class Trailer {
    @Getter
    int id;

    @Getter
    String videoLink;
    public Trailer() {

    }

    public Trailer(int id, String videoLink) {
        this.id = id;
        this.videoLink = videoLink;

    }


}
