package com.paymybuddy.webapp.unitTest.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.paymybuddy.webapp.model.User;
import com.paymybuddy.webapp.service.IUserService;

@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    IUserService userService;

    //--------------------------------------------------------------------------------------------------------

    @Test
    @DisplayName("Test findUserByEmail")
    public void findUserByEmailTest(){
        //GIVEN
        String email = "testemail2@email.com";
        //WHEN
        User user = userService.findUserByEmail(email);
        //THEN
        assertEquals(user.getEmail(), "testemail2@email.com");
    }
}
