package com.example.cinemasystem.repository;

import com.example.cinemasystem.DALInterfaces.IAccountDAL;
import com.example.cinemasystem.ServiceInterfaces.IAccount;
import com.example.cinemasystem.model.UserAccount;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AccountDalJDBC extends JDBCRepository implements IAccountDAL {
    @Override
    public List<IAccount> getAllAccounts() {

        ArrayList<IAccount> accounts = new ArrayList<IAccount>();
        Connection connection = this.getDatabaseConnection();
        String sql = "SELECT * from individual_project.user";
        Statement statement = null;
        try {

             statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);


            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String role = resultSet.getString("role");

                IAccount account = new UserAccount(id, username, password,email,firstName,lastName,role);
                accounts.add(account);
            }

        } catch (SQLException throwable) {System.out.println("Cant' get all accounts");}

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

        return accounts;
    }

    @Override
    public IAccount getAccountById(int id) {

        String sql = "SELECT * from individual_project.user WHERE ID = ?" ;
        Connection connection = this.getDatabaseConnection();
        IAccount account = null;
        PreparedStatement statement = null;
        try {

             statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

            int accountId = resultSet.getInt("ID");
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            String email = resultSet.getString("email");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String role = resultSet.getString("role");

            account = new UserAccount(accountId, username, password,email,firstName,lastName,role);


            connection.commit();
            connection.close();

        } catch (SQLException throwable) {System.out.println("Can't get account by id");}

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

        return account;
    }

    @Override
    public IAccount getAccountByUsername(String username) {

        String sql = "SELECT * from  individual_project.user WHERE username = ?" ;
        Connection connection = this.getDatabaseConnection();
        PreparedStatement statement = null;
        try {

             statement = connection.prepareStatement(sql);
            statement.setString(1, username);

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

            int accountId = resultSet.getInt("ID");
            String accountName = resultSet.getString("username");
            String password = resultSet.getString("password");
            String email = resultSet.getString("email");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String role = resultSet.getString("role");

            IAccount account = new UserAccount(accountId, accountName, password,email,firstName,lastName,role);

            return account;

        } catch (SQLException throwable) {System.out.println("Can't get account by username");}

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

        return null;
    }


    @Override
    public boolean addAccount(IAccount account) {
        Connection connection = this.getDatabaseConnection();
        String sql = "INSERT INTO   individual_project.user ( `ID`,`username`, `password`, `email` ,`first_name`, `last_name`, `role`) VALUES ( null, ?, ?, ?, ?, ?,?);";
        PreparedStatement statement = null;
        try {
             statement = connection.prepareStatement(sql);

            statement.setString(1, account.getUsername());
            statement.setString(2, account.getPassword());
            statement.setString(3, account.getEmail());
            statement.setString(4, account.getFirstName());
            statement.setString(5, account.getLastName());
            statement.setString(6, account.getRole());



            statement.executeUpdate();
            return true;

        } catch (SQLException throwable) {System.out.println("Cant add userAccount to database");}

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
        return false;
    }
}
