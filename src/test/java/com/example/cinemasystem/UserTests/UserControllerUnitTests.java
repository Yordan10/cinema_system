package com.example.cinemasystem.UserTests;


import com.example.cinemasystem.controller.AccountController;
import com.example.cinemasystem.controller.MessageController;
import com.example.cinemasystem.controller.WebSocketController;
import com.example.cinemasystem.model.Greeting;
import com.example.cinemasystem.model.Message;
import com.example.cinemasystem.model.UserAccount;
import com.example.cinemasystem.model.request.UserCreateRequest;
import com.example.cinemasystem.service.UserService;
import com.example.cinemasystem.serviceInterfaces.IAccount;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.*;


@SpringBootTest
public class UserControllerUnitTests {
    @Autowired
    AccountController accountController;
    @Autowired
    MessageController messageController;

    @Autowired
    WebSocketController webSocketController;
    @MockBean
    UserService userService;

    @Test
    void getAccountByIdTest(){
        int id = 10;
        IAccount user = new UserAccount(10,"nz","jsd","hshja","jsd","hds","USER");

        when(userService.returnAccountByID(id)).thenReturn(new ResponseEntity(user, HttpStatus.OK));

        Assertions.assertEquals(new ResponseEntity(user,HttpStatus.OK),accountController.getAccountById(id));
    }


    @Test
    @WithMockUser(username = "name",roles={"USER"})
    void getAccountByUsernameTest(){

        IAccount user = new UserAccount(10,"nz","jsd","hshja","jsd","hds","USER");


        when(userService.returnAccountByUsername("name")).thenReturn(new ResponseEntity(user, HttpStatus.OK));

        Assertions.assertEquals(new ResponseEntity(user,HttpStatus.OK),accountController.getAccountByUsername());
    }

    @Test
    void userRegistrationTest(){
        UserCreateRequest userCreateRequest = new UserCreateRequest();
        UserAccount user = new UserAccount(10,"nz","jsd","hshja","jsd","hds","role");

        when(userService.userRegistration(userCreateRequest)).thenReturn(true);


        Assertions.assertEquals(new ResponseEntity(HttpStatus.OK),accountController.userRegistration(userCreateRequest));

    }
    @Test
    void mesageTest(){


        Assertions.assertEquals("message sent ...",messageController.hi("dsasdasd"));

    }
    @Test
    void setWebSocketControllerTest() throws Exception {

        Message message = new Message("message");
        message.setName("Message");
        String name = message.getName();
        Message message1 = new Message();
        Greeting greeting = new Greeting(message.getName());
        Greeting greeting2 = new Greeting();
        greeting.getContent();
        greeting.setContent("awd");
        webSocketController.message(message);
        Assertions.assertEquals("message sent ...",messageController.hi("dsasdasd"));

    }

}
