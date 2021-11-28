package com.example.cinemasystem.model.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class UserCreateRequest {

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
}
