package com.example.cinemasystem.model;


import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;

public abstract class User {


    @Getter @Setter
    protected int id;

    @Getter @Setter
    protected   String username;

    @Getter @Setter
    protected   String password;

    @Getter @Setter
    protected   String email;

    @Getter @Setter
    protected   String firstName;

    @Getter @Setter
    protected   String lastName;

    public  User() {

    }

    public User(int id,String username, String password,String email, String fName,String lName)
    {
        this.id=id;
        this.username=username;
        this.password=password;
        this.email=email;
        this.firstName=fName;
        this.lastName=lName;

    }


}


