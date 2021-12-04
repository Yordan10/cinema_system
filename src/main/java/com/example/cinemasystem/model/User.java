package com.example.cinemasystem.model;

import lombok.Getter;
import lombok.Setter;

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

    @Getter @Setter
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


