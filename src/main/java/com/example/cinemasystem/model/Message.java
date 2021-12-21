package com.example.cinemasystem.model;

import lombok.Getter;

public class Message {

    @Getter
    private String content;

    public Message(){}

    public Message(String content){
        this.content=content;
    }


}
