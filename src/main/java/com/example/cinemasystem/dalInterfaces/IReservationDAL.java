package com.example.cinemasystem.dalInterfaces;

import com.example.cinemasystem.serviceInterfaces.IReservation;
import com.example.cinemasystem.model.request.ReservationRequest;

import java.util.List;

public interface IReservationDAL {
    List<IReservation> getAllReservations();
    boolean makeReservation(int accountId, ReservationRequest request);
    List<IReservation> getAllReservationsByAccount(int accountId);
    List<IReservation> getAllReservationsByAccountOrderedByPrice(int accountId);
    List<IReservation> getAllReservationsOrderedByPrice();
    List<IReservation> getAllReservationsByAccountForCurrentMonth(int month,int accountId);
    List<IReservation> getAllReservationsForCurrentMonth(int month);
}
