package com.example.cinemasystem.Service;

import com.example.cinemasystem.DALInterfaces.IReservationDAL;
import com.example.cinemasystem.ServiceInterfaces.IReservation;
import com.example.cinemasystem.ServiceInterfaces.IReservationService;
import com.example.cinemasystem.model.request.ReservationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class ReservationService implements IReservationService {

 private final IReservationDAL dal;

  @Override
  @Async("asyncExecutor")
    public CompletableFuture<ResponseEntity> getAllReservations(){

      CompletableFuture<List<IReservation>> reservations = CompletableFuture.completedFuture(dal.getAllReservations());

      if(reservations!= null)
      {
          return reservations.thenApply(ResponseEntity::ok);
      }
      else
      {
          return (CompletableFuture) ResponseEntity.notFound();
      }
  }

  @Override
    public void makeReservation(int accountId, ReservationRequest request)
  {
      dal.makeReservation(accountId,request);
  }


}
