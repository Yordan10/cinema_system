package com.example.cinemasystem.model.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter @Setter
public class UserCreateRequest {

    protected int id;

    protected   String username;

    protected   String password;

    protected   String email;

    protected   String firstName;

    protected   String lastName;

    protected   String role;
}
