package com.example.cinemasystem.controller;

import com.example.cinemasystem.Service.UserService;
import com.example.cinemasystem.ServiceInterfaces.IAccount;
import com.example.cinemasystem.ServiceInterfaces.IUserService;
import com.example.cinemasystem.model.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private IUserService userService ;

    @GetMapping
    public ResponseEntity<List<IAccount>> GetAllAccounts()
    {
        return userService.ReturnAllAccounts();
    }
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
