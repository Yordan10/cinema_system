package com.example.cinemasystem.ServiceInterfaces;

import com.example.cinemasystem.model.request.ReservationRequest;
import org.springframework.http.ResponseEntity;

import java.util.concurrent.CompletableFuture;

public interface IReservationService {

    CompletableFuture<ResponseEntity> getAllReservations();
    void makeReservation(int accountId, ReservationRequest request);
}
