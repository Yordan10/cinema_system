package com.example.cinemasystem.DALInterfaces;

import com.example.cinemasystem.ServiceInterfaces.IAccount;

import java.util.List;


    public interface IAccountDAL {
        List<IAccount> getAllAccounts();
        IAccount getAccountById(int id);
        void addAccount(IAccount account);
        IAccount getAccountByUsername(String username);
    }

