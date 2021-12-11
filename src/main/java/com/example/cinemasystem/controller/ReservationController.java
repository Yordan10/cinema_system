package com.example.cinemasystem.controller;

import com.example.cinemasystem.ServiceInterfaces.IReservationService;
import com.example.cinemasystem.ServiceInterfaces.IUserService;
import com.example.cinemasystem.model.request.ReservationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RequestMapping("/reservation")
public class ReservationController {
    @Autowired
    private IReservationService reservationService;
    @Autowired
    private IUserService userService;

    @GetMapping
    public CompletableFuture<ResponseEntity> getAllReservations(){
        return reservationService.getAllReservations();
    }

    @PostMapping("reserve")
    public ResponseEntity makeReservation(@RequestBody ReservationRequest request){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        int accountID = userService.getAccountByUsername(currentPrincipalName).getId();

        reservationService.makeReservation(accountID,request);

        return ResponseEntity.ok().build();
    }

}
