package com.example.cinemasystem.service;

import com.example.cinemasystem.dalInterfaces.IReservationDAL;
import com.example.cinemasystem.serviceInterfaces.IReservation;
import com.example.cinemasystem.serviceInterfaces.IReservationService;
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
    public boolean makeReservation(int accountId, ReservationRequest request)
  {
      boolean bool = false;
      if(dal.makeReservation(accountId,request))
      {
          bool = true;
      }

  return bool;
  }

    @Override
    @Async("asyncExecutor")
    public CompletableFuture<ResponseEntity> getAllReservationsByAccount(int accountId){

        CompletableFuture<List<IReservation>> reservations = CompletableFuture.completedFuture(dal.getAllReservationsByAccount(accountId));

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
    @Async("asyncExecutor")
    public CompletableFuture<ResponseEntity> getAllReservationsByAccountForCurrentMonth(int month,int accountId){

        CompletableFuture<List<IReservation>> reservations = CompletableFuture.completedFuture(dal.getAllReservationsByAccountForCurrentMonth(month,accountId));

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
    @Async("asyncExecutor")
    public CompletableFuture<ResponseEntity> getAllReservationsForCurrentMonth(int month){

        CompletableFuture<List<IReservation>> reservations = CompletableFuture.completedFuture(dal.getAllReservationsForCurrentMonth(month));

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
    @Async("asyncExecutor")
    public CompletableFuture<ResponseEntity> getAllReservationsByAccountOrderedByPrice(int accountId){

        CompletableFuture<List<IReservation>> reservations = CompletableFuture.completedFuture(dal.getAllReservationsByAccountOrderedByPrice(accountId));

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
    @Async("asyncExecutor")
    public CompletableFuture<ResponseEntity> getAllReservationsOrderedByPrice(){

        CompletableFuture<List<IReservation>> reservations = CompletableFuture.completedFuture(dal.getAllReservationsOrderedByPrice());

        if(reservations!= null)
        {
            return reservations.thenApply(ResponseEntity::ok);
        }
        else
        {
            return (CompletableFuture) ResponseEntity.notFound();
        }
    }
}
