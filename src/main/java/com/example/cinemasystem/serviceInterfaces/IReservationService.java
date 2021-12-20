package com.example.cinemasystem.serviceInterfaces;

import com.example.cinemasystem.model.request.ReservationRequest;
import org.springframework.http.ResponseEntity;

import java.util.concurrent.CompletableFuture;

public interface IReservationService {

    CompletableFuture<ResponseEntity> getAllReservations();
    boolean makeReservation(int accountId, ReservationRequest request);
    CompletableFuture<ResponseEntity> getAllReservationsByAccount(int accountId);
    CompletableFuture<ResponseEntity> getAllReservationsByAccountOrderedByPrice(int accountId);
    CompletableFuture<ResponseEntity> getAllReservationsOrderedByPrice();
    CompletableFuture<ResponseEntity> getAllReservationsByAccountForCurrentMonth(int month,int accountId);
    CompletableFuture<ResponseEntity> getAllReservationsForCurrentMonth(int month);
}
