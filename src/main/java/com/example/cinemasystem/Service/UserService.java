package com.example.cinemasystem.Service;


import com.example.cinemasystem.DALInterfaces.IAccountDAL;
import com.example.cinemasystem.ServiceInterfaces.IAccount;
import com.example.cinemasystem.ServiceInterfaces.IUserService;
import com.example.cinemasystem.model.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService implements IUserService {

    public List<IAccount> userAccounts;
    IAccountDAL dal;


    @Autowired
    public UserService(IAccountDAL dal) {
        this.dal = dal;

    }

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


    public boolean checkUser(String username, String password) {
        for (IAccount user : this.userAccounts) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public IAccount GetAccountByUsername(String username) {
        for (IAccount account : this.userAccounts) {
            if (account.getUsername().equals(username))
                return account;
        }
        return null;
    }

    public IAccount GetAccountByEmail(String email) {
        for (IAccount account : this.userAccounts) {
            if (account.getEmail().equals(email))
                return account;
        }
        return null;
    }

    public int AddAccount(IAccount account) {
        if (this.GetAccountByUsername(account.getUsername()) != null ) {
            return -1;
        }else if(this.GetAccountByEmail(account.getEmail()) != null){
            return -2;
        }
        this.userAccounts.add(account);
        return 0;
    }
    public ResponseEntity<Map<String, Boolean>> CheckLogin( UserAccount account) {

        Map<String, Boolean> response = new HashMap<>();

        if (checkUser(account.getUsername(), account.getPassword())) {
            response.put("error", Boolean.FALSE);
            return ResponseEntity.ok(response);
        }
        response.put("error", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
    public ResponseEntity<UserAccount> UserRegistration(UserAccount user) {

        if (AddAccount(user) == -1) {
            String entity = "User with this username already exists.";
            return new ResponseEntity(entity, HttpStatus.CONFLICT);
        } else if (AddAccount(user) == -2) {
            String entity = "User with this email already exists.";
            return new ResponseEntity(entity, HttpStatus.CONFLICT);
        } else {
            String entity = "You have signed up successfully!";
            return new ResponseEntity(entity, HttpStatus.CREATED);
        }
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
}

