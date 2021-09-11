package com.paymybuddy.webapp.IT.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;

import com.paymybuddy.webapp.config.MyUserDetails;
import com.paymybuddy.webapp.model.User;
import com.paymybuddy.webapp.repository.UserRepository;
import com.paymybuddy.webapp.service.MyUserDetailService;

@DisplayName("MyUserDetailService - IT DB TEST ")
@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("integration")
class MyUserDetailService_IT {

	
	@Autowired
	UserRepository userRepository;

	@Autowired
	MyUserDetailService myUserDetailService;
	
	/** The to test user 1. */
	User toTestUser1 = new User(
    		100,
            "testemail1@email.com",
            "testFirstName",
            "testLastName",
            "testemail1@email.com",
            "myPassword",
            LocalDate.parse("2019-12-31"),
            LocalDate.parse("2019-12-31"),
            "admin",
            true,
            1000.0);

	/** The to test user 2. */
	User toTestUser2 = new User(
    		100,
            "testemail1@email.com",
            "testFirstName",
            "testLastName",
            "testemail1@email.com",
            "myPassword",
            LocalDate.parse("2019-12-31"),
            LocalDate.parse("2019-12-31"),
            "admin",
            true,
            1000.0);


	/** The to test 1. */
	MyUserDetails toTest1 = new MyUserDetails(toTestUser1);

	/** The to test 2. */
	MyUserDetails toTest2 = new MyUserDetails(toTestUser2);

	
	
	
    @WithMockUser("testemail1@email.com")
    @Test
    @DisplayName("Given - If user is not registered,"
    		+ " When loadUserByUsername, THEN throw DataNotFoundException")
    public void givenAnUnFoundUser_whenLoadUserByUsername_thenUsernameNotFoundExceptionIsThrown() {
    	toTestUser2 = userRepository.findUserByEmail(toTestUser1.getEmail());

    	assertEquals(toTestUser2.getEmail(), toTestUser1.getEmail());
    	
    }
	
}
