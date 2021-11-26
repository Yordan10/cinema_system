package com.example.cinemasystem.controller;

import com.example.cinemasystem.Service.UserService;
import com.example.cinemasystem.ServiceInterfaces.IAccount;
import com.example.cinemasystem.ServiceInterfaces.IUserService;
import com.example.cinemasystem.model.UserAccount;
import com.example.cinemasystem.model.request.UserCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @GetMapping("/user")
    public ResponseEntity<IAccount> GetAccountByUsername()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        return userService.ReturnAccountByUsername(currentPrincipalName);
    }

    @PostMapping("/register")
    public ResponseEntity UserRegistration(@RequestBody UserCreateRequest userCreateRequest) {

       userService.UserRegistration(userCreateRequest);
       return ResponseEntity.ok().build();
    }
}
