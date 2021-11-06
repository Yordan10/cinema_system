package com.example.cinemasystem.ServiceInterfaces;

import com.example.cinemasystem.model.UserAccount;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface IUserService {
    ResponseEntity<Map<String, Boolean>> CheckLogin(UserAccount account);
    ResponseEntity<UserAccount> UserRegistration(UserAccount user);
    ResponseEntity<IAccount> ReturnAccountByID(int id);
    ResponseEntity<List<IAccount>>ReturnAllAccounts();
}
