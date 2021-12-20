package com.example.cinemasystem.service;


import com.example.cinemasystem.dalInterfaces.IAccountDAL;
import com.example.cinemasystem.serviceInterfaces.IAccount;
import com.example.cinemasystem.serviceInterfaces.IUserService;
import com.example.cinemasystem.model.UserAccount;
import com.example.cinemasystem.model.request.UserCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CompletableFuture;

@Service
public class UserService implements IUserService {



    IAccountDAL dal;

     private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public UserService(IAccountDAL dal)
    {
        this.dal = dal;
    }


    @Override
    public ResponseEntity<List<IAccount>> returnAllAccounts()
    {
        List<IAccount> accounts = dal.getAllAccounts();

        if (accounts == null)
        {
            return ResponseEntity.notFound().build();
        }
        else
        {
            return ResponseEntity.ok().body(accounts);
        }

    }
    @Override
    @Async("asyncExecutor")
    public CompletableFuture<ResponseEntity> getAllAccountsAsync() {
        CompletableFuture<List<IAccount>> accounts = CompletableFuture.completedFuture(dal.getAllAccounts());


        if(accounts != null) {

            return accounts.thenApply(ResponseEntity::ok);
        } else {

            return (CompletableFuture) ResponseEntity.notFound();
        }
    }



    public IAccount getAccountByUsername(String username)
    {
       return dal.getAccountByUsername(username);
    }



    @Override
    public boolean userRegistration(UserCreateRequest userCreateRequest) {

        IAccount user;
        Optional<IAccount> byUsername = Optional.ofNullable(dal.getAccountByUsername(userCreateRequest.getUsername()));
        if(byUsername.isPresent()){
            throw new RuntimeException("User already registered. Please use different username");
        }
        user = new UserAccount(userCreateRequest.getId(),
                userCreateRequest.getUsername(),
                passwordEncoder.encode(userCreateRequest.getPassword()),
                userCreateRequest.getEmail(),
                userCreateRequest.getFirstName(),
                userCreateRequest.getLastName(),
                userCreateRequest.getRole());

        dal.addAccount(user);


      return true;
    }


    @Override
    public ResponseEntity<IAccount> returnAccountByID(int id)
    {
        IAccount account = dal.getAccountById(id);
        if (account == null)
        {
            return ResponseEntity.notFound().build();
        }
        else
        {
            return ResponseEntity.ok().body(account);
        }
    }
    @Override
    public ResponseEntity<IAccount> returnAccountByUsername(String username)
    {
        IAccount account = dal.getAccountByUsername(username);
        if (account == null)
        {
            return ResponseEntity.notFound().build();
        }
        else
        {
            return ResponseEntity.ok().body(account);
        }
    }
}

