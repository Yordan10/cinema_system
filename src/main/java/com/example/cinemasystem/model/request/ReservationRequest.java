package com.example.cinemasystem.model.request;

import lombok.Data;
import lombok.Getter;

import java.sql.Date;


@Getter

public class ReservationRequest {

    private int id;
    private int movieId;


    private Date transactionDate;

    private Double price;

    private int numberOfTickets;

    private String projectionDay;

    private String projectionHour;

}
