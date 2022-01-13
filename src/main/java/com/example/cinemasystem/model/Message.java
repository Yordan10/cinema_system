package com.example.cinemasystem.model;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Message {

    private String name;


    public Message(String name) {
        this.name = name;

    }

    public Message() {
    }

}
