package com.example.cinemasystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
public class CinemaSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(CinemaSystemApplication.class, args);
    }

}
