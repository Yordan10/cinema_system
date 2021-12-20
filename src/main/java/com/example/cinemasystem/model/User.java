package com.example.cinemasystem.model;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public abstract class User {



    protected int id;

    protected   String username;

    protected   String password;

    protected   String email;

    protected   String firstName;

    protected   String lastName;

    protected   String role;

    public  User() {

    }

    protected  User(int id,String username, String password,String email, String fName,String lName, String role)
    {
        this.id=id;
        this.username=username;
        this.password=password;
        this.email=email;
        this.firstName=fName;
        this.lastName=lName;
        this.role=role;

    }


}


