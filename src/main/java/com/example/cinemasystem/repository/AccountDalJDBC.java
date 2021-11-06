package com.example.cinemasystem.repository;

import com.example.cinemasystem.DALInterfaces.IAccountDAL;
import com.example.cinemasystem.ServiceInterfaces.IAccount;
import com.example.cinemasystem.model.UserAccount;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;

@Repository
public class AccountDalJDBC extends JDBCRepository implements IAccountDAL {
    @Override
    public ArrayList<IAccount> getAllAccounts() {

        ArrayList<IAccount> accounts = new ArrayList<IAccount>();
        Connection connection = this.getDatabaseConnection();
        String sql = "SELECT * from individual_project.user";

        try {

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);


            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");

                IAccount account = new UserAccount(id, username, password,email,firstName,lastName);
                accounts.add(account);
            }

            connection.commit();
            connection.close();

        } catch (SQLException throwable) {System.out.println("Ne sum swyrzan");}

        return accounts;
    }

    @Override
    public IAccount getAccountById(int id) {

        String sql = "SELECT * from individual_project.user WHERE ID = ?" ;
        Connection connection = this.getDatabaseConnection();
        IAccount account = null;
        try {

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

            int accountId = resultSet.getInt("ID");
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            String email = resultSet.getString("email");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");

            account = new UserAccount(accountId, username, password,email,firstName,lastName);


            connection.commit();
            connection.close();

        } catch (SQLException throwable) {System.out.println("Ne sum swyrzan");}

        return account;
    }
}
