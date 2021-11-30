package com.example.cinemasystem.Service;


import com.example.cinemasystem.DALInterfaces.IAccountDAL;
import com.example.cinemasystem.ServiceInterfaces.IAccount;
import com.example.cinemasystem.ServiceInterfaces.IUserService;
import com.example.cinemasystem.model.UserAccount;
import com.example.cinemasystem.model.request.UserCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {


    private final IAccountDAL dal;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();



    @Override
    public ResponseEntity<List<IAccount>> ReturnAllAccounts()
    {
        if (dal.getAllAccounts() == null)
        {
            return ResponseEntity.notFound().build();
        }
        else
        {
            return ResponseEntity.ok().body(dal.getAllAccounts());
        }

    }



    public IAccount GetAccountByUsername(String username)
    {
       return dal.getAccountByUsername(username);
    }



    @Override
    public boolean UserRegistration(UserCreateRequest userCreateRequest) {

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
    public ResponseEntity<IAccount> ReturnAccountByID(int id)
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
    public ResponseEntity<IAccount> ReturnAccountByUsername(String username)
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

