package com.example.cinemasystem.DALInterfaces;

import com.example.cinemasystem.ServiceInterfaces.IReservation;
import com.example.cinemasystem.model.request.ReservationRequest;

import java.util.List;

public interface IReservationDAL {
    List<IReservation> getAllReservations();
    void makeReservation(int accountId, ReservationRequest request);
}
