package com.example.cinemasystem.UserTests;


import com.example.cinemasystem.Service.UserService;
import com.example.cinemasystem.ServiceInterfaces.IAccount;
import com.example.cinemasystem.model.UserAccount;
import com.example.cinemasystem.model.request.UserCreateRequest;
import com.example.cinemasystem.repository.AccountDalJDBC;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceUnitTests {
   @Autowired
    UserService userService;

    @MockBean
    AccountDalJDBC accountDalJDBC;

    @Test
     void getAllAccountsTest()  {

        List<IAccount> users = new ArrayList<>();
        UserAccount user =  new UserAccount(1,"ne","ekke","keke","err","lastname","USER");
        users.add(user);

        when(accountDalJDBC.getAllAccounts()).thenReturn(Stream.of(user)
                .collect(Collectors.toList()));
        Assertions.assertEquals(new ResponseEntity(users, HttpStatus.OK),userService.returnAllAccounts());

    }
    @Test
     void getAllAccountsFailTest()  {

        when(accountDalJDBC.getAllAccounts()).thenReturn(null);
        Assertions.assertEquals(new ResponseEntity(HttpStatus.NOT_FOUND),userService.returnAllAccounts());

    }
    @Test
     void getAccountByIdTest(){

        int id = 10;
        UserAccount user = new UserAccount(10,"nz","jsd","hshja","jsd","hds","USER");

        when(accountDalJDBC.getAccountById(id))
                .thenReturn(user);

        Assertions.assertEquals(new ResponseEntity(user, HttpStatus.OK),userService.returnAccountByID(id));

    }
    @Test
     void getAccountByIdFailTest(){

        int id = 9;

        when(accountDalJDBC.getAccountById(id))
                .thenReturn(null);

        Assertions.assertEquals(new ResponseEntity(HttpStatus.NOT_FOUND),userService.returnAccountByID(id));


    }

    @Test
     void getAccountByUsernameTest(){

        String username = "name";
        UserAccount user = new UserAccount(10,"nz","jsd","hshja","jsd","hds","AA");

        when(accountDalJDBC.getAccountByUsername(username))
                .thenReturn(user);

        Assertions.assertEquals(user.getId(),userService.getAccountByUsername(username).getId());

    }
    @Test
     void returnAccountByUsernameTest(){

        String username = "name";
        UserAccount user = new UserAccount(10,"nz","jsd","hshja","jsd","hds","role");

        when(accountDalJDBC.getAccountByUsername(username))
                .thenReturn(user);

        Assertions.assertEquals(new ResponseEntity(user, HttpStatus.OK),userService.returnAccountByUsername(username));

    }
    @Test
     void getAccountByUsernameFailTest(){

        String username = "name";
        when(accountDalJDBC.getAccountByUsername(username))
                .thenReturn(null);

        Assertions.assertEquals(new ResponseEntity(HttpStatus.NOT_FOUND),userService.returnAccountByUsername(username));

    }
    @Test
     void UserRegistrationTest(){

        UserCreateRequest userCreateRequest = new UserCreateRequest();
        userCreateRequest.setUsername("secret");
        userCreateRequest.setPassword("taina");
        userCreateRequest.setEmail("com");
        userCreateRequest.setLastName("yo");
        userCreateRequest.setId(1);
        userCreateRequest.setRole("role");

        UserAccount user = new UserAccount(10,"nz","jsd","hshja","jsd","hds","role");

        when(accountDalJDBC.addAccount(user)).thenReturn(true);

        Assertions.assertEquals(true,userService.userRegistration(userCreateRequest));
    }
}
