package com.example.cinemasystem.repository;

import com.example.cinemasystem.DALInterfaces.IMovieDAL;
import com.example.cinemasystem.Enums.Genre;
import com.example.cinemasystem.ServiceInterfaces.IAccount;
import com.example.cinemasystem.ServiceInterfaces.IMovie;
import com.example.cinemasystem.model.Movie;
import com.example.cinemasystem.model.Trailer;
import com.example.cinemasystem.model.request.MovieCreateRequest;
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
                String description = resultSet.getString("description");
                Double length = resultSet.getDouble("length");
                String genre = resultSet.getString("genre");
                Double rating = resultSet.getDouble("rating");
                String director = resultSet.getString("director");

                IMovie movie =  new Movie(id,title,description,length,Genre.valueOf(genre),rating,director);
                movies.add(movie);
            }

        }
        catch (SQLException throwable){
            System.out.println("Can't connect to database");
        }
        finally {
            try{
                connection.commit();
                connection.close();
            }
            catch (SQLException throwable){
                System.out.println("Can't close connection");
            }
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
            String description = resultSet.getString("description");
            Double length = resultSet.getDouble("length");
            String genre = resultSet.getString("genre");
            Double rating = resultSet.getDouble("rating");
            String director = resultSet.getString("director");

            movie = new Movie(movieId,title,description,length,Genre.valueOf(genre),rating,director);
        }
        catch (SQLException throwable)
        {
            System.out.println("Can't connect to database");
        }
        finally {
            try{
                connection.commit();
                connection.close();
            }
            catch (SQLException throwable){
                System.out.println("Can't close connection");
            }
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



        } catch (SQLException throwable) {System.out.println("Can't connect to database");}

        finally {
            try{
                connection.commit();
                connection.close();
            }
            catch (SQLException throwable){
                System.out.println("Can't close connection");
            }
        }
        return path;
    }

    @Override
    public Trailer getTrailerByMovieId(int id){
        String sql = "SELECT * from movie_trailers WHERE movie_id = ?";
        Connection connection = this.getDatabaseConnection();
        Trailer trailer = null;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {

                int videoId = resultSet.getInt("ID");
                String link = resultSet.getString("link");

                trailer = new Trailer(videoId, link);
            }

        }
        catch (SQLException throwable) {System.out.println("Can't get video of charity");}

        finally {
            try{
                connection.commit();
                connection.close();
            }
            catch (SQLException throwable){
                System.out.println("Can't close connection");
            }
        }
        return trailer;

    }
    @Override
    public void AddMovie(MovieCreateRequest movieCreateRequest)
    {
        Connection connection = this.getDatabaseConnection();
        String sql = "INSERT INTO individual_project.movie (`title`, `description`, `length`, `genre`, `rating`, `director`) VALUES (?,?,?,?,?,?)";

        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,movieCreateRequest.getTitle());
            statement.setString(2,movieCreateRequest.getDescription());
            statement.setDouble(3,movieCreateRequest.getLength());
            statement.setString(4,movieCreateRequest.getGenre().toString());
            statement.setDouble(5,movieCreateRequest.getRating());
            statement.setString(6,movieCreateRequest.getDirector());


            statement.executeUpdate();
        }
        catch (SQLException throwable){throwable.toString();}

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
