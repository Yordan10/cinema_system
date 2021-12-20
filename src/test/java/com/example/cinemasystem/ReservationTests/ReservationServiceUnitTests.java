package com.example.cinemasystem.ReservationTests;
import com.example.cinemasystem.service.ReservationService;
import com.example.cinemasystem.model.request.ReservationRequest;
import com.example.cinemasystem.repository.ReservationDalJDBC;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.Mockito.*;
@SpringBootTest
public class ReservationServiceUnitTests {
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

     when(dal.makeReservation(id,request)).thenReturn(true);

  Assertions.assertEquals(true,reservationService.makeReservation(id,request));
 }
}
