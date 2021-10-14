package com.example.cinemasystem.controller;

import com.example.cinemasystem.Service.UserService;
import com.example.cinemasystem.ServiceInterfaces.IAccount;
import com.example.cinemasystem.ServiceInterfaces.IUserService;
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
    private static final IUserService userService = new UserService();

    @GetMapping("/{id}")
    public ResponseEntity<IAccount> GetAccountById(@PathVariable(value = "id") int id)
    {
        return userService.ReturnAccountByID(id);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Boolean>> CheckLogin(@RequestBody UserAccount account) {

       return userService.CheckLogin(account);
    }

    @PostMapping("/register")
    public ResponseEntity<UserAccount> UserRegistration(@RequestBody UserAccount user) {

      return userService.UserRegistration(user);
    }
}
