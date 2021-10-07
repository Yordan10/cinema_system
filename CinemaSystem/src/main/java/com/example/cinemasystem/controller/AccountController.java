package com.example.cinemasystem.controller;

import com.example.cinemasystem.model.UserAccount;
import com.example.cinemasystem.repository.FakeDataStore;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RequestMapping("/account")
public class AccountController {
    private static final FakeDataStore fakeDataStore = new FakeDataStore();

    @PostMapping("/login")
    public ResponseEntity<Map<String, Boolean>> CheckLogin(@RequestBody UserAccount account) {

        Map<String, Boolean> response = new HashMap<>();

        if (fakeDataStore.checkUser(account.getUsername(), account.getPassword())) {
            response.put("error", Boolean.FALSE);
            return ResponseEntity.ok(response);
        }
        response.put("error", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<UserAccount> UserRegistration(@RequestBody UserAccount user) {

        if (fakeDataStore.AddAccount(user) == -1) {
            String entity = "User with this username already exists.";
            return new ResponseEntity(entity, HttpStatus.CONFLICT);
        } else if (fakeDataStore.AddAccount(user) == -2) {
            String entity = "User with this email already exists.";
            return new ResponseEntity(entity, HttpStatus.CONFLICT);
        } else {
            String entity = "You have signed up successfully!";
            return new ResponseEntity(entity, HttpStatus.CREATED);
        }
    }
}
