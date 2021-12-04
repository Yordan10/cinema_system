package com.example.cinemasystem.ServiceInterfaces;

import com.example.cinemasystem.model.request.UserCreateRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface IUserService {


    ResponseEntity<IAccount> returnAccountByID(int id);
    ResponseEntity<List<IAccount>> returnAllAccounts();
    boolean userRegistration(UserCreateRequest userCreateRequest);
    ResponseEntity<IAccount> returnAccountByUsername(String username);
    CompletableFuture<List<IAccount>> getAllAccountsAsync();
    IAccount getAccountByUsername(String username);
}
