package com.example.cinemasystem.controller;

import com.example.cinemasystem.serviceInterfaces.IReservationService;
import com.example.cinemasystem.serviceInterfaces.IUserService;
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
    public CompletableFuture<ResponseEntity> getAllReservations()
    {
        return reservationService.getAllReservations();
    }

    @GetMapping("/byAccount")
    public CompletableFuture<ResponseEntity> getAllReservationsByAccount()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        int accountID = userService.getAccountByUsername(currentPrincipalName).getId();
        return reservationService.getAllReservationsByAccount(accountID);
    }

    @PostMapping("reserve")
    public ResponseEntity makeReservation(@RequestBody ReservationRequest request){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        int accountID = userService.getAccountByUsername(currentPrincipalName).getId();

        reservationService.makeReservation(accountID,request);

        return ResponseEntity.ok().build();
    }
    @GetMapping("/byAccount/highest-price")
    public CompletableFuture<ResponseEntity> getAllReservationsByAccountOrderedByPrice()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        int accountID = userService.getAccountByUsername(currentPrincipalName).getId();
        return reservationService.getAllReservationsByAccountOrderedByPrice(accountID);
    }

    @GetMapping("/byAccount/{month}")
    public CompletableFuture<ResponseEntity> getAllReservationsByAccountForCurrentMonth(@PathVariable(value = "month") int month)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        int accountID = userService.getAccountByUsername(currentPrincipalName).getId();
        return reservationService.getAllReservationsByAccountForCurrentMonth(month,accountID);
    }

    @GetMapping("/{month}")
    public CompletableFuture<ResponseEntity> getAllReservationsForCurrentMonth(@PathVariable(value = "month") int month)
    {

        return reservationService.getAllReservationsForCurrentMonth(month);
    }
    @GetMapping("/highest-price")
    public CompletableFuture<ResponseEntity> getAllReservationsOrderedByPrice()
    {

        return reservationService.getAllReservationsOrderedByPrice();
    }

}
