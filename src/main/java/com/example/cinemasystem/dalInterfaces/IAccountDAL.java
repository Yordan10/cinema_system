package com.example.cinemasystem.dalInterfaces;

import com.example.cinemasystem.serviceInterfaces.IAccount;

import java.util.List;


    public interface IAccountDAL {
        List<IAccount> getAllAccounts();
        IAccount getAccountById(int id);
        boolean addAccount(IAccount account);
        IAccount getAccountByUsername(String username);
    }

