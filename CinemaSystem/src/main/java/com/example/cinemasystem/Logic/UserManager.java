package com.example.cinemasystem.Logic;


import com.example.cinemasystem.Interfaces.IAccount;
import com.example.cinemasystem.Interfaces.IUserManager;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


public class UserManager implements IUserManager {
    @Autowired
    public List<IAccount> userAccounts;

    public  UserManager()
    {
        userAccounts = new ArrayList<IAccount>();
    }

    public IAccount GetAccountById(int id)
    {
        for (IAccount account : userAccounts )
        {
            if (account.getId() == id)
            {
                return account;
            }
        }
        return null;
    }

}

