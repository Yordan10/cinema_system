package com.example.cinemasystem.repository;

import com.example.cinemasystem.ServiceInterfaces.IAccount;
import com.example.cinemasystem.Service.UserService;
import com.example.cinemasystem.model.Movie;
import com.example.cinemasystem.model.UserAccount;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FakeDataStore {

    private final List<Movie> movieList = new ArrayList<>();


    public FakeDataStore()
    {

    }



}
