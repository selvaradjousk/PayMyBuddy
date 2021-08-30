package com.paymybuddy.webapp.unitTest.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.paymybuddy.webapp.dto.UserDTO;
import com.paymybuddy.webapp.service.IUserService;

@DisplayName("USER SERVICE - H2 DB TEST ")
@SpringBootTest
@ActiveProfiles("test")
public class UserServiceImplTest {

    @Autowired
    IUserService userService;
    
    String testEmail = "testemail2@email.com";

	// *******************************************************************
	@DisplayName("Find User By Email  - "
			+ "GIVEN User Email "
			+ "WHEN Requested find user by email"
			+ "THEN returns expected user by email")
    @Test
    public void testFindUserByEmail(){

		//GIVEN
        // a user Email

        //WHEN
        UserDTO userDTO = userService.findUserByEmail(testEmail);

        //THEN
        assertEquals(userDTO.getEmail(), testEmail);
    }
	
	
	// *******************************************************************
	@DisplayName("Find User All User (Service) - "
			+ "GIVEN User list "
			+ "WHEN Requested find all user list"
			+ "THEN returns expected user list size")
    @Test
    public void testFindAllUser(){

		//GIVEN
		// list of users populated in the H2 DB dataset
		
        //WHEN
        List<UserDTO> listUserDTO = userService.findAllUsers();

        //THEN
        assertEquals(10, listUserDTO.size());
    }

	// *******************************************************************
	
	@DisplayName("Find User by Id - "
			+ "GIVEN User ID "
			+ "WHEN Requested find user by ID"
			+ "THEN returns expected user by ID")
    @Test
    public void testFindUserById(){

		//GIVEN
		UserDTO userDTO = userService.findUserByEmail(testEmail);
		
        //WHEN
		 UserDTO userIdDTO = userService.findUserById(userDTO.getId());

        //THEN
		 assertEquals(userIdDTO.getId(), userDTO.getId());
    }
	
	// *******************************************************************
	
	@DisplayName("Save User - "
			+ "GIVEN new User "
			+ "WHEN Requested save user"
			+ "THEN returns saved user")
    @Test
    public void testSaveUser(){
		UserDTO userDTO = new UserDTO();
		//GIVEN
		userDTO = userService.findUserByEmail(testEmail);
		userDTO.setEmail("testEmail@email.com");
		userDTO.setId(100);
//		UserDTO dto = new UserDTO("userName", "firstName", testEmail, "password");
		
        //WHEN
		UserDTO newUserDTO = userService.saveUser(userDTO);

        //THEN
		
		assertNotEquals(0, newUserDTO.getId());
		assertNotEquals(0, newUserDTO.getId());
    }

	// *******************************************************************

	
	
}
