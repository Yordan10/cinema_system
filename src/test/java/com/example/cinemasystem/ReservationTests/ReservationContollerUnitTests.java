package com.example.cinemasystem.ReservationTests;

import com.example.cinemasystem.controller.ReservationController;
import com.example.cinemasystem.model.UserAccount;
import com.example.cinemasystem.model.request.ReservationRequest;
import com.example.cinemasystem.service.ReservationService;
import com.example.cinemasystem.service.UserService;
import com.example.cinemasystem.serviceInterfaces.IAccount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;


import static org.mockito.Mockito.*;

@SpringBootTest
public class ReservationContollerUnitTests {

    @Autowired
    ReservationController reservationController;

    @MockBean
    ReservationService reservationService;

    @MockBean
    UserService userService;

    @Test
    @WithMockUser(username = "asjk",roles={"USER"})
    void makeReservation2Test(){

        String username = "memdssadsd";
        ReservationRequest request = new ReservationRequest();
        request.getId();
        request.getMovieId();
        request.getNumberOfTickets();
        request.getPrice();
        request.getProjectionDay();
        request.getProjectionHour();
        request.getTransactionDate();

        IAccount account = new UserAccount(1,"sdad","asdsa","ad","asd","asd","USER");
        when(userService.getAccountByUsername("asjk")).thenReturn(account);

        when(reservationService.makeReservation(account.getId(),request)).thenReturn(true);

        Assertions.assertEquals(new ResponseEntity(HttpStatus.OK),reservationController.makeReservation(request));
    }
}
