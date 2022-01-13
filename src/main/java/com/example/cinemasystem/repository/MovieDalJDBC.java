package com.example.cinemasystem.repository;

import com.example.cinemasystem.dalInterfaces.IMovieDAL;
import com.example.cinemasystem.enums.Genre;
import com.example.cinemasystem.serviceInterfaces.IMovie;
import com.example.cinemasystem.model.Movie;
import com.example.cinemasystem.model.Trailer;
import com.example.cinemasystem.model.request.MovieCreateRequest;
import com.example.cinemasystem.model.request.MovieEditRequest;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MovieDalJDBC extends JDBCRepository implements IMovieDAL {



    @Override
    public List<IMovie> getAllMovies()
    {
        ArrayList<IMovie> movies = new ArrayList<IMovie>();
        Connection connection = this.getDatabaseConnection();
        String sql = "SELECT * from individual_project.movie";
        Statement statement = null;
        try{
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);


            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                int length = resultSet.getInt("length");
                String genre = resultSet.getString("genre");
                Double rating = resultSet.getDouble("rating");
                String director = resultSet.getString("director");

                IMovie movie =  new Movie(id,title,description,length,Genre.valueOf(genre),rating,director);
                movies.add(movie);
            }

        }
        catch (SQLException throwable){
            System.out.println("Can't get all movies");
        }
        finally {
            try{
                if(statement!=null)
                {
                    statement.close();
                }
                connection.commit();
                connection.close();

            }
            catch (SQLException throwable){
                System.out.println("Can't close connection4");
            }
        }
        return movies;
    }

    @Override
    public IMovie getMovieById(int id){

        String sql = "SELECT * from individual_project.movie WHERE ID = ?" ;
        Connection connection = this.getDatabaseConnection();
        IMovie movie = null;
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(sql);
            statement.setInt(1,id);

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

            int movieId = resultSet.getInt("ID");
            String title = resultSet.getString("title");
            String description = resultSet.getString("description");
            int length = resultSet.getInt("length");
            String genre = resultSet.getString("genre");
            Double rating = resultSet.getDouble("rating");
            String director = resultSet.getString("director");

            movie = new Movie(movieId,title,description,length,Genre.valueOf(genre),rating,director);

        }
        catch (SQLException throwable)
        {
            System.out.println("Can't get movie by id");
        }
        finally {
            try{
                if(statement!=null)
                {
                    statement.close();
                }
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
    public int getMovieIdByTitle (String title){

        String sql = "SELECT ID from individual_project.movie WHERE title = ?" ;
        Connection connection = this.getDatabaseConnection();
        PreparedStatement statement = null;
        int movieId = 0;
        try{
             statement = connection.prepareStatement(sql);
            statement.setString(1,title);

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

             movieId = resultSet.getInt("ID");

        }
        catch (SQLException throwable)
        {
            System.out.println("Can't get movie by title");
        }
        finally {
            try{
                if(statement!=null)
                {
                    statement.close();
                }
                connection.commit();
                connection.close();

            }
            catch (SQLException throwable){
                System.out.println("Can't close connection");
            }
        }
        return movieId;
    }
    @Override
    public String getPhotoByMovieId(int id) {

        String sql = "SELECT * from individual_project.movie_photo WHERE movie_id = ?";
        Connection connection = this.getDatabaseConnection();
        String path = "";
        PreparedStatement statement = null;
        try {
             statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

            path = resultSet.getString("photo_path");



        } catch (SQLException throwable) {System.out.println("Can't get photo of movie");}

        finally {
            try{
                if(statement!=null)
                {
                    statement.close();
                }
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
        PreparedStatement statement = null;
        try {
             statement = connection.prepareStatement(sql);
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
                if(statement!=null)
                {
                    statement.close();
                }
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
    public boolean addMovie(MovieCreateRequest movieCreateRequest)
    {
        Connection connection = this.getDatabaseConnection();
        String sql = "INSERT INTO individual_project.movie (`title`, `description`, `length`, `genre`, `rating`, `director`) VALUES (?,?,?,?,?,?)";
        boolean bool = false;
        PreparedStatement statement = null;
        try{
             statement = connection.prepareStatement(sql);
            statement.setString(1,movieCreateRequest.getTitle());
            statement.setString(2,movieCreateRequest.getDescription());
            statement.setDouble(3,movieCreateRequest.getLength());
            statement.setString(4,movieCreateRequest.getGenre().toString());
            statement.setDouble(5,movieCreateRequest.getRating());
            statement.setString(6,movieCreateRequest.getDirector());


            statement.executeUpdate();
            bool=true;
        }
        catch (SQLException throwable){throwable.toString();}

        finally {
            try{
                if(statement!=null)
                {
                    statement.close();
                }
                connection.commit();
                connection.close();

            }
            catch (SQLException throwable){
                System.out.println("Can't close connection");
            }
        }
        return bool;

    }

    @Override
    public boolean addTrailerToMovie(int movieId,String trailer)
    {
        Connection connection = this.getDatabaseConnection();
        String sql = "INSERT INTO individual_project.movie_trailers (`movie_id`,`link`) VALUES (?,?)";
        boolean bool= false;
        PreparedStatement statement = null;
        try{
             statement = connection.prepareStatement(sql);
            statement.setInt(1,movieId);
            statement.setString(2,trailer);

            statement.executeUpdate();
            bool=true;
        }
        catch (SQLException throwable){throwable.toString();}

        finally {
            try{
                if(statement!=null)
                {
                    statement.close();
                }
                connection.commit();
                connection.close();

            }
            catch (SQLException throwable){
                System.out.println("Can't close connection");
            }
        }
        return bool;

    }
    @Override
    public boolean addPosterToMovie(String path,int movieId)
    {
        Connection connection = this.getDatabaseConnection();
        String sql = "INSERT INTO individual_project.movie_photo (`movie_id`,`photo_path`) VALUES (?,?)";
        PreparedStatement statement = null;
        boolean bool = false;
        try{
             statement = connection.prepareStatement(sql);
            statement.setInt(1,movieId);
            statement.setString(2,path);

            statement.executeUpdate();
            bool = true;
        }
        catch (SQLException throwable){throwable.toString();}

        finally {
            try{
                if(statement!=null)
                {
                    statement.close();
                }
                connection.commit();
                connection.close();

            }
            catch (SQLException throwable){
                System.out.println("Can't close connection");
            }
        }
        return bool;
    }

    @Override
    public void deleteMovie(int movieId)
    {
        Connection connection = this.getDatabaseConnection();
        String sql = "DELETE FROM individual_project.movie WHERE ID = ? ";
        PreparedStatement statement = null;
        try{
             statement = connection.prepareStatement(sql);
            statement.setInt(1,movieId);

            statement.executeUpdate();
        }
        catch (SQLException throwable){throwable.toString();}

        finally {
            try{
                if(statement!=null)
                {
                    statement.close();
                }
                connection.commit();
                connection.close();

            }
            catch (SQLException throwable){
                System.out.println("Can't close connection");
            }
        }

    }
    @Override
    public void deletePosterOfMovie(int movieId)
    {
        Connection connection = this.getDatabaseConnection();
        String sql = "DELETE FROM individual_project.movie_photo WHERE movie_id = ? ";
        PreparedStatement statement = null;
        try{
             statement = connection.prepareStatement(sql);
            statement.setInt(1,movieId);

            statement.executeUpdate();
        }
        catch (SQLException throwable){throwable.toString();}

        finally {
            try{
                if(statement!=null)
                {
                    statement.close();
                }
                connection.commit();
                connection.close();

            }
            catch (SQLException throwable){
                System.out.println("Can't close connection");
            }
        }

    }
    @Override
    public boolean editMovie(MovieEditRequest movieEditRequest)
    {
        boolean bool=false;
        Connection connection = this.getDatabaseConnection();
        String sql = "UPDATE  individual_project.movie SET `title`= ?,`description`=?,`length`=?,`genre`=?,`rating`=?,`director`=? WHERE `ID` = ? ";
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(sql);
            statement.setString(1,movieEditRequest.getTitle());
            statement.setString(2,movieEditRequest.getDescription());
            statement.setDouble(3,movieEditRequest.getLength());
            statement.setString(4,movieEditRequest.getGenre().toString());
            statement.setDouble(5,movieEditRequest.getRating());
            statement.setString(6,movieEditRequest.getDirector());
            statement.setInt(7,movieEditRequest.getId());


            statement.executeUpdate();
            bool=true;
        }
        catch (SQLException throwable){throwable.toString();}

        finally {
            try{
                if(statement!=null)
                {
                    statement.close();
                }
                connection.commit();
                connection.close();

            }
            catch (SQLException throwable){
                System.out.println("Can't close connection");

            }
        }

        return bool;
    }

    @Override
    public void deleteTrailerOfMovie(int movieId)
    {
        Connection connection = this.getDatabaseConnection();
        String sql = "DELETE FROM individual_project.movie_trailers WHERE movie_id = ? ";

        PreparedStatement statement = null;
        try{
             statement = connection.prepareStatement(sql);
            statement.setInt(1,movieId);

            statement.executeUpdate();
        }
        catch (SQLException throwable){throwable.toString();}

        finally {
            try{
                if(statement!=null)
                {
                    statement.close();
                }
                connection.commit();
                connection.close();

            }
            catch (SQLException throwable){
                System.out.println("Can't close connection");
            }
        }

    }
}
