package com.example.cinemasystem.DALInterfaces;

import com.example.cinemasystem.ServiceInterfaces.IReservation;
import com.example.cinemasystem.model.request.ReservationRequest;

import java.util.List;

public interface IReservationDAL {
    List<IReservation> getAllReservations();
    void makeReservation(int accountId, ReservationRequest request);
    List<IReservation> getAllReservationsByAccount(int accountId);
    List<IReservation> getAllReservationsByAccountOrderedByPrice(int accountId);
    List<IReservation> getAllReservationsOrderedByPrice();
}
