package com.paymybuddy.webapp.IT.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.paymybuddy.webapp.dto.UserDTO;
import com.paymybuddy.webapp.exception.DataAlreadyExistException;
import com.paymybuddy.webapp.exception.DataNotConformException;
import com.paymybuddy.webapp.service.IUserService;

@DisplayName("USER SERVICE IT - MySQL DB TEST ")
@SpringBootTest
@ActiveProfiles("integration")
public class ServiceUser_IT {

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
	
	
	@DisplayName("Find User by Email Null Exception - "
			+ "GIVEN User ID null "
			+ "WHEN Requested find user by Email"
			+ "THEN returns Exception")
    @Test
    public void testFindUserByEmailNull(){

		//THEN <== //WHEN <== //GIVEN
		 assertThrows(NullPointerException.class, () -> userService.findUserByEmail(""));
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
        assertEquals(11, listUserDTO.size());
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

	
	@DisplayName("Save User (update)- "
			+ "GIVEN User "
			+ "WHEN Requested save user"
			+ "THEN returns saved user (update)")
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

	@DisplayName("Save New User - "
			+ "GIVEN new User "
			+ "WHEN Requested save user"
			+ "THEN returns saved user")
    @Test
    public void testSaveNewUser(){

        //GIVEN
        UserDTO userDTO = new UserDTO(
        		"UserName",
        		"FirstName",
        		"email1@email.com",
        		"password");

        //WHEN
        UserDTO newUserDTO = userService
        		.saveNewUser(userDTO,"password");

        //THEN
        assertTrue(newUserDTO.getId()>0);
    }	
	// *******************************************************************
	
	
	@DisplayName("Save New User invalid empty user name field format input - "
			+ "GIVEN new User invalid username input"
			+ "WHEN Requested save user"
			+ "THEN returns Exception")
    @Test
    public void testSaveNewUserInputInvalidEmptyUserNameExceptionThrown(){

        //GIVEN
        UserDTO userDTO = new UserDTO(
        		"",
        		"FirstName",
        		"email1@email.com",
        		"password");

        //THEN   < ==  //WHEN
        assertThrows(DataNotConformException.class, () -> userService.saveNewUser(userDTO, "password"));
    }	
	// *******************************************************************
	
	
	@DisplayName("Save New User invalid empty first name field format input - "
			+ "GIVEN new User invalid firstname input"
			+ "WHEN Requested save user"
			+ "THEN returns Exception")
    @Test
    public void testSaveNewUserInputInvalidEmptyFirstNameExceptionThrown(){

        //GIVEN
        UserDTO userDTO = new UserDTO(
        		"UserName",
        		"",
        		"email1@email.com",
        		"password");

        //THEN   < ==  //WHEN
        assertThrows(DataNotConformException.class, () -> userService.saveNewUser(userDTO, "password"));
    }
	
	// *******************************************************************	
	
	
	@DisplayName("Save New User invalid empty email field entry input - "
			+ "GIVEN new User invalid empty email input"
			+ "WHEN Requested save user"
			+ "THEN returns Exception")
    @Test
    public void testSaveNewUserInputInvalidEmptyEmailFieldentryExceptionThrown(){

        //GIVEN
        UserDTO userDTO = new UserDTO(
        		"UserName",
        		"firstName",
        		"",
        		"password");

        //THEN   < ==  //WHEN
        assertThrows(DataNotConformException.class, () -> userService.saveNewUser(userDTO, "password"));
    }	
	
	// *******************************************************************
	
	
	@DisplayName("Save New User MISMATCH password field entry input - "
			+ "GIVEN new User invalid password input"
			+ "WHEN Requested save user"
			+ "THEN returns Exception")
    @Test
    public void testSaveNewUserInputInvalidPasswordFieldentryExceptionThrown(){

        //GIVEN
        UserDTO userDTO = new UserDTO(
        		"UserName",
        		"firstName",
        		"email@email.com",
        		"yyyy");

        //THEN   < ==  //WHEN
        assertThrows(DataNotConformException.class, () -> userService.saveNewUser(userDTO, "check"));
    }
	
	
	// *******************************************************************
	
	
	@DisplayName("Save New User invalid Empty password field entry input - "
			+ "GIVEN new User invalid password input"
			+ "WHEN Requested save user"
			+ "THEN returns Exception")
    @Test
    public void testSaveNewUserInputInvalidEmptyPasswordFieldentryExceptionThrown(){

        //GIVEN
        UserDTO userDTO = new UserDTO(
        		"UserName",
        		"firstName",
        		"email@email.com",
        		"");

        //THEN   < ==  //WHEN
        assertThrows(DataNotConformException.class, () -> userService.saveNewUser(userDTO, ""));
    }
	
	// *******************************************************************

	
	@DisplayName("Save New User invalid email format input - "
			+ "GIVEN new User invalid email input"
			+ "WHEN Requested save user"
			+ "THEN returns Exception")
    @Test
    public void testSaveNewUserInvalidEmailInputExceptionThrown(){

        //GIVEN
        UserDTO userDTO = new UserDTO(
        		"UserName",
        		"FirstName",
        		"email1.com",
        		"password");

        //THEN   < ==  //WHEN
        assertThrows(DataNotConformException.class, () -> userService.saveNewUser(userDTO, "password"));
    }	
	// *******************************************************************
	
	
	@DisplayName("Save New User Existing email format input - "
			+ "GIVEN new User existing email input"
			+ "WHEN Requested save user"
			+ "THEN returns Exception")
    @Test
    public void testSaveNewUserExistingEmailInputExceptionThrown(){

        //GIVEN
        UserDTO userDTO = new UserDTO(
        		"UserName",
        		"FirstName",
        		"testemail2@email.com",
        		"password");

        //THEN   < ==  //WHEN
        assertThrows(DataAlreadyExistException.class, () -> userService.saveNewUser(userDTO, "password"));
    }

	// *******************************************************************	
	
	@DisplayName("Save New User Non AlphaNumeric Characters Input email format input - "
			+ "GIVEN new User input invalid"
			+ "WHEN Requested save user"
			+ "THEN returns Exception")
    @Test
    public void testSaveNewUserEmailWithNonAlphaNumericCharactersInputExceptionThrown(){

        //GIVEN
        UserDTO userDTO = new UserDTO(
        		"UserName",
        		"FirstName",
        		"3^çé??????@email.com",
        		"password");

        //THEN   < ==  //WHEN
        assertThrows(DataNotConformException.class, () -> userService.saveNewUser(userDTO, "password"));
    }

	// *******************************************************************
	
	
	
	@DisplayName("Save New User Non AlphaNumeric Characters username input - "
			+ "GIVEN new User input invalid"
			+ "WHEN Requested save user"
			+ "THEN returns Exception")
    @Test
    public void testSaveNewUserUserNameWithNonAlphaNumericCharactersInputExceptionThrown(){

        //GIVEN
        UserDTO userDTO = new UserDTO(
        		"3^çé??????",
        		"FirstName",
        		"email@email.com",
        		"password");

        //THEN   < ==  //WHEN
        assertThrows(DataNotConformException.class, () -> userService.saveNewUser(userDTO, "password"));
    }

	// *******************************************************************
	
	
	@DisplayName("Save New User Non AlphaNumeric Characters firstname input - "
			+ "GIVEN new User input invalid"
			+ "WHEN Requested save user"
			+ "THEN returns Exception")
    @Test
    public void testSaveNewUserFirstNameWithNonAlphaNumericCharactersInputExceptionThrown(){

        //GIVEN
        UserDTO userDTO = new UserDTO(
        		"UserName",
        		"3^çé??????",
        		"email@email.com",
        		"password");

        //THEN   < ==  //WHEN
        assertThrows(DataNotConformException.class, () -> userService.saveNewUser(userDTO, "password"));
    }

	// *******************************************************************
	
	
	
}
