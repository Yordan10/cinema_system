package com.example.cinemasystem.UserTests;


import com.example.cinemasystem.model.UserAccount;
import com.example.cinemasystem.service.AuthenticationUserDetailService;
import com.example.cinemasystem.service.UserService;
import com.example.cinemasystem.serviceInterfaces.IAccount;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collection;

import static org.mockito.Mockito.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthenticationTests {

    @Autowired
    AuthenticationUserDetailService authenticationUserDetailService;

@MockBean
UserService userServiceMock;

//    @Test
//    void getAccountForLoginTest() {
//
//
//        String username = "Josh";
//        IAccount user = new UserAccount(10,"nz","jsd","hshja","jsd","hds","USER");
//
//        when(userServiceMock.getAccountByUsername(username))
//                .thenReturn(user);
//
//        Assertions.assertEquals(new org.springframework.security.core.userdetails.User(user.getUsername(),
//                user.getPassword(), authenticationUserDetailService.getAuthorities(user.getRole())),authenticationUserDetailService.loadUserByUsername(username));
//
//    }



}
