package com.example.cinemasystem.ReservationTests;
import com.example.cinemasystem.controller.ReservationController;
import com.example.cinemasystem.model.Reservation;
import com.example.cinemasystem.model.UserAccount;
import com.example.cinemasystem.service.ReservationService;
import com.example.cinemasystem.model.request.ReservationRequest;
import com.example.cinemasystem.repository.ReservationDalJDBC;
import com.example.cinemasystem.service.UserService;
import com.example.cinemasystem.serviceInterfaces.IAccount;
import com.example.cinemasystem.serviceInterfaces.IReservation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
public class ReservationServiceUnitTests {

@InjectMocks
 @Autowired
    ReservationService reservationService;

 @MockBean
 ReservationDalJDBC dal;


 @Test
    void makeReservationTest(){
  int id = 1;
     ReservationRequest request = new ReservationRequest();
     request.getId();
     request.getMovieId();
     request.getNumberOfTickets();
     request.getPrice();
     request.getProjectionDay();
     request.getProjectionHour();
     request.getTransactionDate();

     Date date = new Date(1023,12,12);
     Reservation reservation1 = new Reservation();
     Reservation reservation = new Reservation(1,2,"dew",1, date,10.2,1,"asd","ads");
     Reservation reservation2 = new Reservation(1,2,"dew",1, date,10.2,1,"asd","ads","ads");

     reservation.getMovieId();
     reservation.getPrice();
     reservation.getAccountId();
     reservation.getNumberOfTickets();
     reservation.getProjectionDay();
     reservation.getProjectionHour();
     reservation.getTransactionDate();
     reservation.getAccountName();
     reservation.getId();
     reservation.getMovieName();

     reservation.setAccountId(1);
     reservation.setAccountName("d");
     reservation.setMovieId(3);
     reservation.setMovieName("asd");
     reservation.setPrice(121.1);
     reservation.setId(123);
     reservation.setTransactionDate(date);
     reservation.setNumberOfTickets(12);
     reservation.setProjectionHour("18:00");
     reservation.setProjectionDay("Monday");


     when(dal.makeReservation(id,request)).thenReturn(true);

  Assertions.assertEquals(true,reservationService.makeReservation(id,request));
 }

//    @Test
//    void getAllReservationsTestsAsync() throws Exception {
//
//
//        List<IReservation> reservations = new ArrayList<>();
//       Reservation reservation = new Reservation();
//       reservations.add(reservation);
//
//        CompletableFuture<List<IReservation>> reservation2s= CompletableFuture.completedFuture(reservations);
//
//
//       // Mockito.verify(dal,Mockito.timeout(1000).times(1)).getAllReservations();
//      when(dal.getAllReservations()).thenReturn(Stream.of(reservation).collect(Collectors.toList()));
//
//
//        Assertions.assertEquals(new ResponseEntity(reservation2s,HttpStatus.OK),reservationService.getAllReservations());
//    }


}
