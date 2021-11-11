package com.example.cinemasystem.repository;

import com.example.cinemasystem.DALInterfaces.IMovieDAL;
import com.example.cinemasystem.Enums.Genre;
import com.example.cinemasystem.ServiceInterfaces.IAccount;
import com.example.cinemasystem.ServiceInterfaces.IMovie;
import com.example.cinemasystem.model.Movie;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;

@Repository
public class MovieDalJDBC extends JDBCRepository implements IMovieDAL {

    @Override
    public ArrayList<IMovie> getAllMovies()
    {
        ArrayList<IMovie> movies = new ArrayList<IMovie>();
        Connection connection = this.getDatabaseConnection();
        String sql = "SELECT * from individual_project.movie";

        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);


            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String title = resultSet.getString("title");
                Double length = resultSet.getDouble("length");
                String genre = resultSet.getString("genre");
                Double rating = resultSet.getDouble("rating");
                String director = resultSet.getString("director");

                IMovie movie =  new Movie(id,title,length,Genre.valueOf(genre),rating,director);
                movies.add(movie);
            }

            connection.commit();
            connection.close();
        }
        catch (SQLException throwable){
            System.out.println("Can't connect to database");
        }
        return movies;
    }

    @Override
    public IMovie getMovieById(int id){

        String sql = "SELECT * from individual_project.movie WHERE ID = ?" ;
        Connection connection = this.getDatabaseConnection();
        IMovie movie = null;
        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,id);

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

            int movieId = resultSet.getInt("ID");
            String title = resultSet.getString("title");
            Double length = resultSet.getDouble("length");
            String genre = resultSet.getString("genre");
            Double rating = resultSet.getDouble("rating");
            String director = resultSet.getString("director");

            movie = new Movie(movieId,title,length,Genre.valueOf(genre),rating,director);
        }
        catch (SQLException throwable)
        {
            System.out.println("Can't connect to database");
        }
        return movie;
    }
    @Override
    public String getPhotoByMovieId(int id) {

        String sql = "SELECT * from individual_project.movie_photo WHERE movie_id = ?";
        Connection connection = this.getDatabaseConnection();
        String path = "";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

            path = resultSet.getString("photo_path");

            connection.commit();
            connection.close();

        } catch (SQLException throwable) {System.out.println("Ne sum swyrzan");}

        return path;
    }
}
