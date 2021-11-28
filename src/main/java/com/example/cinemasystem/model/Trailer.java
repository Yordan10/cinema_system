package com.example.cinemasystem.model;

import lombok.Getter;
import lombok.Setter;

public class Trailer {
    @Getter
    @Setter
    int ID;

    @Getter @Setter
    String videoLink;
    public Trailer() {

    }

    public Trailer(int id, String videoLink) {
        this.ID = id;
        this.videoLink = videoLink;

    }


}
