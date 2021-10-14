package com.example.cinemasystem.model;

import com.example.cinemasystem.ServiceInterfaces.IAccount;

public class UserAccount extends  User implements IAccount {
    public UserAccount(int id,String username, String password,String email, String firstName, String lastName)
    {
        super(id,username,password,email,firstName,lastName);

    }
}
