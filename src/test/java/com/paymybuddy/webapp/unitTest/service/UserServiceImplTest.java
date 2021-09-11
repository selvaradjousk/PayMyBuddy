package com.paymybuddy.webapp.unitTest.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.paymybuddy.webapp.dto.UserDTO;
import com.paymybuddy.webapp.exception.DataNotConformException;
import com.paymybuddy.webapp.exception.DataNotFoundException;
import com.paymybuddy.webapp.model.User;
import com.paymybuddy.webapp.repository.UserRepository;
import com.paymybuddy.webapp.service.UserServiceImpl;
import com.paymybuddy.webapp.util.UserMapper;

@DisplayName("USER SERVICE - UNIT TEST ")
@ExtendWith(SpringExtension.class)
public class UserServiceImplTest {
	
    @InjectMocks
    private UserServiceImpl userService;
	
	    @Mock
	    private UserRepository userRepository;
	    
	    @Mock
	    private UserMapper userMapper;

	    @Mock
	    private BCryptPasswordEncoder passwordEncoder;

	    private static UserDTO userToSaveDTO;


	    private static User user, testUser1, testUser2, testUser3;

	    private static List<User> userList;
	    
        UserDTO testUserDTO, userSaved, userIdFoundDTO,
        userWrongEmailFormatDTO, userNoPasswordDTO, userNoFirstNameDTO, userNoUserNameDTO;
        
	    String testEmail = "myEmail@email.com";

	    @BeforeEach
	    public void setUp() {
	        
	        testUserDTO = new UserDTO(
	    			"testUserName",
		            "testFirstName",
					"myEmail@email.com",
					"password");
	    	
	        userIdFoundDTO = new UserDTO(
	    			"testUserName",
		            "testFirstName",
					"myEmail@email.com",
					"password");
	        
	        
	    	userToSaveDTO = new UserDTO(
	    			"testUserName",
		            "testFirstName",
					"myEmail@email.com",
					"password");

	        	        
	        userNoUserNameDTO = new UserDTO(
	    			"",
		            "testFirstName",
					"myEmail@email.com",
					"password");
	        
	        userNoFirstNameDTO = new UserDTO(
	    			"testUserName",
		            "",
					"myEmail@email.com",
					"password");

	        userNoPasswordDTO = new UserDTO(
	    			"testUserName",
		            "testFirstName",
					"myEmail@email.com",
					"");
	        
	        userWrongEmailFormatDTO = new UserDTO(
	    			"testUserName",
		            "testFirstName",
					"myEmailemail.com",
					"password");

	        
	        
	        user = new User(
	    			100,
	 	           "testUserName",
	 	            "testFirstName",
	 	            "testLastName",
	 			"myEmail@email.com",
	 			"password",
	 			LocalDate.parse("2021-08-28"),
	 			LocalDate.parse("2021-08-28"),
	 			"admin",
	 			true,
	 			1000.0);
	        
	        
	        testUser1 = new User(
	    			100,
	 	           "testUserName",
	 	            "testFirstName",
	 	            "testLastName",
	 			"myEmail@email.com",
	 			"password",
	 			LocalDate.parse("2021-08-28"),
	 			LocalDate.parse("2021-08-28"),
	 			"admin",
	 			true,
	 			1000.0);
	        
	        
	        testUser2 = new User(
	    			200,
	 	           "testUserName",
	 	            "testFirstName",
	 	            "testLastName",
	 			"myEmail@email2.com",
	 			"password",
	 			LocalDate.parse("2021-08-28"),
	 			LocalDate.parse("2021-08-28"),
	 			"admin",
	 			true,
	 			1000.0);  
	        
	        testUser3 = new User(
	    			300,
	 	           "testUserName",
	 	            "testFirstName",
	 	            "testLastName",
	 			"myEmail@email3.com",
	 			"password",
	 			LocalDate.parse("2021-08-28"),
	 			LocalDate.parse("2021-08-28"),
	 			"admin",
	 			true,
	 			1000.0);
	        
	        userList = Arrays.asList(testUser1, testUser2, testUser3);  
	        
	    }
	    
	    
	    
		// *******************************************************************
		@DisplayName("Find User By Email  - "
				+ "GIVEN User Email "
				+ "WHEN Requested find user by email"
				+ "THEN returns expected user by email")
	    @Test
	    public void testFindUserByEmail(){

			//GIVEN
	        when(userRepository
	        		.findUserByEmail(anyString()))
	        .thenReturn(user);
	        
	        when(userMapper
	        		.toUserDTO(user))
	        .thenReturn(userToSaveDTO);

	        //WHEN
	        UserDTO userDTO = userService
	        		.findUserByEmail(testEmail);
	        
	        UserDTO userFound = userService
	        		.findUserByEmail(anyString());

	        //THEN
	        assertEquals(userDTO.getEmail(), testEmail);
	        assertEquals(userFound.getEmail(), user.getEmail());
	        assertEquals(userFound.getPassword(), user.getPassword());
	        
			InOrder inOrder = inOrder(userRepository, userMapper);
			inOrder.verify(userRepository).findUserByEmail(anyString());
			inOrder.verify(userMapper).toUserDTO(user);
	        
	        verify(userRepository, times(2)).findUserByEmail(anyString());
	        verify(userMapper, times(2)).toUserDTO(user);
	    }

		// *******************************************************************
		
		

		@DisplayName("Find User by Email Null Exception - "
				+ "GIVEN User ID null "
				+ "WHEN Requested find user by Email"
				+ "THEN returns Exception")
		@Test
	    public void testFindUserByEmailExceptionIsThrown() {
			
	        when(userRepository
	        		.findByEmail(anyString()))
	        .thenReturn(null);

		    assertThrows(NullPointerException.class, ()
		     		  -> userService.findUserByEmail(anyString()));
		    
		    verify(userRepository, times(0)).findByEmail(anyString());
	    }	    
		
		
		

		// *******************************************************************	
	    @Test
	    @DisplayName("GET UserLIST"
	    		+ " - Given a list of users,"
	    		+ " WHEN Requested find all user list,"
	    		+ " then return expected User list size")
	    public void testGetUserList() {
	    	
	        when(userRepository
	        		.findAll())
	        .thenReturn(userList);

	        List<UserDTO> listUserDTO = userService
	        		.findAllUsers();

	        assertNotNull(listUserDTO);
	        assertEquals(3, listUserDTO.size());
	        
	        verify(userRepository).findAll();
	    }	
	    
	    
	    
	        
		
	 // *******************************************************************
	    
	    
	    
	    @Test
	    @DisplayName("GET USERLIST"
	    		+ " - Given with no list of users,"
	    		+ " when getUserList,"
	    		+ " then return Nothing")
	    public void testGetUserListwithEmptyList() {
	    	
	        when(userRepository
	        		.findAll())
	        .thenReturn(Collections.emptyList());

	        List<UserDTO> listUserDTO = userService
	        		.findAllUsers();
	        
	        assertNotNull(listUserDTO);
	        assertEquals(0, listUserDTO.size());
	        
	        verify(userRepository).findAll();
	    }  
	    

		 // ******************************************************************* 
	    
		
		@DisplayName("Find User by Id - "
				+ "GIVEN User ID "
				+ "WHEN Requested find user by ID"
				+ "THEN returns expected user by ID")
	    @Test
	    public void testFindUserById(){
			
			userList = Arrays.asList(testUser1, testUser2, testUser3);
			
			//GIVEN
	        when(userRepository
	        		.findUserById(anyInt()))
	        .thenReturn(user);

	        when(userMapper
	        		.toUserDTO(user))
	        .thenReturn(userIdFoundDTO);
			
	        //WHEN
			 UserDTO userIdDTO = userService
					 .findUserById(userIdFoundDTO.getId());

      
		   //THEN
		     assertNotNull(userIdDTO);
			 assertEquals(testUserDTO.getId(), userIdDTO.getId());
			 assertEquals(testUserDTO.getPassword(), userIdDTO.getPassword());
			 
			InOrder inOrder = inOrder(userRepository, userMapper);
			inOrder.verify(userRepository).findUserById(anyInt());
			inOrder.verify(userMapper).toUserDTO(user);
			
			verify(userRepository).findUserById(anyInt());
			verify(userMapper).toUserDTO(any(User.class));		 
			 
	    }
		
		

		// *******************************************************************    

		
		
		@DisplayName("Find User by Id - "
				+ "GIVEN User invalid ID "
				+ "WHEN Requested find user by ID"
				+ "THEN returns expected thrown exception")
	    @Test
	    public void testFindUserByIdThrowException(){
			
			userList = Arrays.asList(testUser1, testUser2, testUser3);
			
			//GIVEN
	        when(userRepository
	        		.findUserById(anyInt()))
	        .thenReturn(null);

	        when(userMapper
	        		.toUserDTO(any(User.class)))
	        .thenReturn(userIdFoundDTO);
			
	        //WHEN 	 //THEN
			 assertThrows(DataNotFoundException.class, ()
		        		-> userService.findUserById(userIdFoundDTO.getId()));
			 
			InOrder inOrder = inOrder(userRepository, userMapper);
			inOrder.verify(userRepository).findUserById(anyInt());
			
			verify(userRepository).findUserById(anyInt());
			verify(userMapper, times(0)).toUserDTO(any(User.class));
			 
 
	    }
		
		
		
		
	// *******************************************************************	
		
		
		
		
	@DisplayName("Save User (update)- "
			+ "GIVEN User "
			+ "WHEN Requested save user"
			+ "THEN returns saved user (update)")
    @Test
    public void testSaveUser(){
		
		when(userRepository
				.save(userMapper.toUserDO(any(UserDTO.class))))
		.thenReturn(testUser1);
		
		when(userMapper
				.toUserDTO(any(User.class)))
		.thenReturn(userToSaveDTO);
		
        //WHEN
		UserDTO newUserDTO = userService
				.saveUser(userToSaveDTO);

        //THEN
		
		assertEquals(testUser1.getEmail(), newUserDTO.getEmail());
		assertEquals(testUser1.getFirstName(), newUserDTO.getFirstName());
		
		InOrder inOrder = inOrder(userRepository, userMapper);
		inOrder.verify(userRepository).save(userMapper.toUserDO(any(UserDTO.class)));
		inOrder.verify(userMapper).toUserDTO(any(User.class));
		
		verify(userRepository).save(userMapper.toUserDO(any(UserDTO.class)));
		verify(userMapper).toUserDTO(any(User.class));
    }
	
	
	

	// *******************************************************************
	
	
	

	@DisplayName("Save New User - "
			+ "GIVEN new User "
			+ "WHEN Requested save user"
			+ "THEN returns saved user")
    @Test
    public void testSaveNewUser(){
		
		when(passwordEncoder
				.encode(anyString()))
		.thenReturn("HAAAAYIIIIIIIIIIYYYYYIIIYOOOOO");
		
		testUser1.setPassword("HAAAAYIIIIIIIIIIYYYYYIIIYOOOOO");
		
		when(userRepository
				.save(userMapper.toUserDO(any(UserDTO.class))))
		.thenReturn(testUser1);
		
		when(userMapper
				.toUserDTO(any(User.class)))
		.thenReturn(userToSaveDTO);
		
        //WHEN
        UserDTO newUserDTO = userService
        		.saveNewUser(userToSaveDTO,"password");

        //THEN
        assertEquals(testUser1.getEmail(), newUserDTO.getEmail());
        assertEquals(testUser1.getFirstName(), newUserDTO.getFirstName());


		InOrder inOrder = inOrder(passwordEncoder, userRepository, userMapper);
		inOrder.verify(passwordEncoder).encode(anyString());		 
		inOrder.verify(userRepository).save(userMapper.toUserDO(any(UserDTO.class)));
		inOrder.verify(userMapper).toUserDTO(any(User.class));
		
        verify(passwordEncoder, times(1)).encode(anyString());
		verify(userRepository).save(userMapper.toUserDO(any(UserDTO.class)));
		verify(userMapper).toUserDTO(any(User.class));
        
    }	
	
	
	
	// *******************************************************************
	

	

	@DisplayName("Save New User Empty UserName Entry- "
			+ "GIVEN new User with empty user name value"
			+ "WHEN Requested save user"
			+ "THEN returns Exception thrown")
    @Test
    public void testSaveNewUserEmptyUserName(){
		
		when(passwordEncoder
				.encode(anyString()))
		.thenReturn("HAAAAYIIIIIIIIIIYYYYYIIIYOOOOO");
		
		userNoUserNameDTO.setPassword("HAAAAYIIIIIIIIIIYYYYYIIIYOOOOO");

		when(userRepository
				.save(userMapper
						.toUserDO(any(UserDTO.class))))
		.thenReturn(testUser1);
		
		when(userMapper
				.toUserDTO(any(User.class)))
		.thenReturn(userNoUserNameDTO);
		
		
        //WHEN //THEN
		 assertThrows(DataNotConformException.class, ()
	        		-> userService
	        		.saveNewUser(userNoUserNameDTO,"password"));

			verify(passwordEncoder, times(0)).encode(anyString());
			verify(userRepository, times(0)).save(userMapper.toUserDO(any(UserDTO.class)));
			verify(userMapper, times(0)).toUserDTO(any(User.class));
        
    }
	
	// *******************************************************************
	

	@DisplayName("Save New User Empty FirstName Entry- "
			+ "GIVEN new User with empty first name value"
			+ "WHEN Requested save user"
			+ "THEN returns Exception thrown")
    @Test
    public void testSaveNewUserEmptyFirstName(){
		
		when(passwordEncoder
				.encode(anyString()))
		.thenReturn("HAAAAYIIIIIIIIIIYYYYYIIIYOOOOO");
		
		userNoFirstNameDTO.setPassword("HAAAAYIIIIIIIIIIYYYYYIIIYOOOOO");
		
		when(userRepository
				.save(userMapper.toUserDO(any(UserDTO.class))))
		.thenReturn(testUser1);
		
		when(userMapper
				.toUserDTO(any(User.class)))
		.thenReturn(userNoFirstNameDTO);
		
		
        //WHEN //THEN
		 assertThrows(DataNotConformException.class, ()
	        		-> userService
	        		.saveNewUser(userNoFirstNameDTO,"password"));

			verify(passwordEncoder, times(0)).encode(anyString());
			verify(userRepository, times(0)).save(userMapper.toUserDO(any(UserDTO.class)));
			verify(userMapper, times(0)).toUserDTO(any(User.class));
        
    }	
	
	// *******************************************************************
	

	@DisplayName("Save New User Empty Password Entry- "
			+ "GIVEN new User with empty Password value"
			+ "WHEN Requested save user"
			+ "THEN returns Exception thrown")
    @Test
    public void testSaveNewUserPasswordEmpty(){
		
		when(passwordEncoder
				.encode(anyString()))
		.thenReturn("");
		
		userNoPasswordDTO.setPassword("");
		
		when(userRepository
				.save(userMapper.toUserDO(any(UserDTO.class))))
		.thenReturn(testUser1);
		
		when(userMapper
				.toUserDTO(any(User.class)))
		.thenReturn(userNoPasswordDTO);
		
		

        //WHEN //THEN
		 assertThrows(DataNotConformException.class, ()
	        		-> userService
	        		.saveNewUser(userNoPasswordDTO,"password"));

 

		verify(passwordEncoder, times(0)).encode(anyString());
		verify(userRepository, times(0)).save(userMapper.toUserDO(any(UserDTO.class)));
		verify(userMapper, times(0)).toUserDTO(any(User.class));
        
    }	
	
	// *******************************************************************
	

	@DisplayName("Save New User Empty Email Entry- "
			+ "GIVEN new User with empty Email value"
			+ "WHEN Requested save user"
			+ "THEN returns Exception thrown")
    @Test
    public void testSaveNewUserEmailEmpty(){
		
		when(passwordEncoder
				.encode(anyString()))
		.thenReturn("HAAAAYIIIIIIIIIIYYYYYIIIYOOOOO");
		
		userWrongEmailFormatDTO.setPassword("HAAAAYIIIIIIIIIIYYYYYIIIYOOOOO");
		
		when(userRepository
				.save(userMapper.toUserDO(any(UserDTO.class))))
		.thenReturn(testUser1);
		
		when(userMapper
				.toUserDTO(any(User.class)))
		.thenReturn(userWrongEmailFormatDTO);


        //WHEN //THEN
		 assertThrows(DataNotConformException.class, ()
	        		-> userService
	        		.saveNewUser(userWrongEmailFormatDTO,"password"));

		 
	 
		verify(passwordEncoder, times(0)).encode(anyString());
		verify(userRepository, times(0)).save(userMapper.toUserDO(any(UserDTO.class)));
		verify(userMapper, times(0)).toUserDTO(any(User.class));
        
    }	
	
	// *******************************************************************


	@DisplayName("Save New User UserName Non Alphanumeric- "
			+ "GIVEN new User with UserName Non Alphanumeric value"
			+ "WHEN Requested save user"
			+ "THEN returns Exception thrown")
    @Test
    public void testSaveNewUserUserNameNonAlphanumeric(){
		
				UserDTO userFirstNameNonAlphanumeric = new UserDTO(
						"~~~{{{{\\\\§§§§§§§",
						"testFirstName",
						"myEmail@email.com",
						"password");
		
		
		when(passwordEncoder
				.encode(anyString()))
		.thenReturn("HAAAAYIIIIIIIIIIYYYYYIIIYOOOOO");
		
		userFirstNameNonAlphanumeric.setPassword("HAAAAYIIIIIIIIIIYYYYYIIIYOOOOO");
		
		when(userRepository
				.save(userMapper.toUserDO(any(UserDTO.class))))
		.thenReturn(testUser1);
		
		when(userMapper
				.toUserDTO(any(User.class)))
		.thenReturn(userFirstNameNonAlphanumeric);


        //WHEN //THEN
		 assertThrows(DataNotConformException.class, ()
	        		-> userService
	        		.saveNewUser(userFirstNameNonAlphanumeric,"password"));
		 

		verify(passwordEncoder, times(0)).encode(anyString());
		verify(userRepository, times(0)).save(userMapper.toUserDO(any(UserDTO.class)));
		verify(userMapper, times(0)).toUserDTO(any(User.class));
        
    }	
	
	// *******************************************************************

	@DisplayName("Save New User FirstName Non Alphanumeric- "
			+ "GIVEN new User with FirstName Non Alphanumeric value"
			+ "WHEN Requested save user"
			+ "THEN returns Exception thrown")
    @Test
    public void testSaveNewUserFirstNameNonAlphanumeric(){
		
				UserDTO userUserNameNonAlphanumeric = new UserDTO(
				        "testFirstName",
						"~~~{{{{\\\\§§§§§§§",
						"myEmail@email.com",
						"password");
		
		
		when(passwordEncoder
				.encode(anyString()))
		.thenReturn("HAAAAYIIIIIIIIIIYYYYYIIIYOOOOO");
		
		userUserNameNonAlphanumeric.setPassword("HAAAAYIIIIIIIIIIYYYYYIIIYOOOOO");
		
		
		when(userRepository
				.save(userMapper.toUserDO(any(UserDTO.class))))
		.thenReturn(testUser1);
		
		when(userMapper
				.toUserDTO(any(User.class)))
		.thenReturn(userUserNameNonAlphanumeric);


        //WHEN //THEN
		 assertThrows(DataNotConformException.class, ()
	        		-> userService
	        		.saveNewUser(userUserNameNonAlphanumeric,"password"));

		verify(passwordEncoder, times(0)).encode(anyString());
		verify(userRepository, times(0)).save(userMapper.toUserDO(any(UserDTO.class)));
		verify(userMapper, times(0)).toUserDTO(any(User.class));
        
    }	
    
}
