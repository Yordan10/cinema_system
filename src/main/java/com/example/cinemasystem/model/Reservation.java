package com.example.cinemasystem.model;

import com.example.cinemasystem.ServiceInterfaces.IReservation;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter@
        Setter
public class Reservation implements IReservation {

    private int id;
    private int movieId;
    private int accountId;

    private String movieName;

    private Date transactionDate;

    private Double price;

    private int numberOfTickets;

    private String projectionDay;

    private String projectionHour;

    public Reservation(int id,int movieId,String movieName,int accountId,Date transactionDate, Double price,int numberOfTickets,String projectionDay,String projectionHour)
    {
        this.id=id;
        this.movieId=movieId;
        this.movieName=movieName;
        this.accountId=accountId;
        this.transactionDate=transactionDate;
        this.numberOfTickets=numberOfTickets;
        this.price=price;
        this.projectionDay=projectionDay;
        this.projectionHour=projectionHour;

    }
    public Reservation(){}


}
