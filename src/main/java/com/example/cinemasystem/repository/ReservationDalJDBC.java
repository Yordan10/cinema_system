package com.example.cinemasystem.repository;

import com.example.cinemasystem.DALInterfaces.IReservationDAL;
import com.example.cinemasystem.ServiceInterfaces.IReservation;
import com.example.cinemasystem.model.Reservation;
import com.example.cinemasystem.model.request.ReservationRequest;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.*;
import java.util.Date;

@Repository
public class ReservationDalJDBC extends JDBCRepository implements IReservationDAL {

    @Override
    public List<IReservation> getAllReservations(){
        List<IReservation> reservations = new ArrayList<>();
        Connection connection = this.getDatabaseConnection();
        String sql = "SELECT * from individual_project.reservations";

        try {

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);


            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                int accountId = resultSet.getInt("account_id");
                int movieId = resultSet.getInt("movie_id");
                Date transactionDate = resultSet.getDate("date");
                int numberOfTickets = resultSet.getInt("number_of_tickets");
                Double price = resultSet.getDouble("price");
                String projectionDay = resultSet.getString("projection_day");
                String projectionHour = resultSet.getString("projection_hour");


                IReservation reservation = new Reservation(id,movieId,accountId,transactionDate,price,numberOfTickets,projectionDay,projectionHour);
                reservations.add(reservation);
            }


        } catch (SQLException throwable) {System.out.println("Can't get all reservations");}
        finally {
            try{
                connection.commit();
                connection.close();
            }
            catch (SQLException throwable){
                System.out.println("Can't close connection");
            }
        }
        return reservations;
    }

    @Override
    public void makeReservation(int accountId, ReservationRequest request)
    {
        Connection connection = this.getDatabaseConnection();
        String sql = "INSERT INTO individual_project.reservations (`account_id`, `movie_id`, `date`, `number_of_tickets`, `price`, `projection_day`, `projection_hour`) VALUES (?,?,?,?,?,?,?)";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, accountId);
            statement.setInt(2, request.getMovieId());
            statement.setDate(3,request.getTransactionDate());
            statement.setInt(4,request.getNumberOfTickets());
            statement.setDouble(5,request.getPrice());
            statement.setString(6,request.getProjectionDay());
            statement.setString(7,request.getProjectionHour());

            statement.executeUpdate();

        } catch (SQLException throwable) {}
        finally {
            try{
                connection.commit();
                connection.close();
            }
            catch (SQLException throwable){
                System.out.println("Can't close connection");
            }
        }
    }


}
