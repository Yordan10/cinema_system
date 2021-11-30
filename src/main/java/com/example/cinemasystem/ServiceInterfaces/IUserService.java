package com.example.cinemasystem.ServiceInterfaces;

import com.example.cinemasystem.model.UserAccount;
import com.example.cinemasystem.model.request.UserCreateRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface IUserService {


    ResponseEntity<IAccount> ReturnAccountByID(int id);
    ResponseEntity<List<IAccount>>ReturnAllAccounts();
    boolean UserRegistration(UserCreateRequest userCreateRequest);
    ResponseEntity<IAccount> ReturnAccountByUsername(String username);
}
