package com.paymybuddy.webapp.unitTest.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.paymybuddy.webapp.dto.UserDTO;
import com.paymybuddy.webapp.service.IUserService;

@SpringBootTest
@ActiveProfiles("test")
public class UserServiceImplTest {

    @Autowired
    IUserService userService;

	// *******************************************************************

    @Test
    @DisplayName("Test findUserByEmail")
    public void findUserByEmailTest(){
        //GIVEN
        String email = "testemail2@email.com";
        //WHEN
        UserDTO userDTO = userService.findUserByEmail(email);
        //THEN
        assertEquals(userDTO.getEmail(), "testemail2@email.com");
    }
    
	// *******************************************************************
    
    
}
