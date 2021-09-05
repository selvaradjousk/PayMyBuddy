package com.paymybuddy.webapp.unitTest.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.web.context.WebApplicationContext;

import com.paymybuddy.webapp.controller.UserController;
import com.paymybuddy.webapp.repository.UserRepository;
import com.paymybuddy.webapp.service.UserServiceImpl;

@DisplayName("USER CONTROLLER - H2 DB TEST ")
@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext context;

	@Autowired
	UserRepository userRepository;

	@InjectMocks
	UserController userController;

	@Mock
	UserServiceImpl userService;

	// ********************************************************************
	   
	@DisplayName("REGISTER page Url request Response - 200 OK - "
			+ "GIVEN Register url /register "
			+ "WHEN Requested GET /register page"
			+ "THEN returns expected 200 OK http response") 
	@Test
	public void testRegisterAttributesExist() throws Exception {

		mockMvc.perform(get("/register"))
				.andExpect(view().name("register")).
				andDo(MockMvcResultHandlers.print())
				.andExpect(model().hasNoErrors())
				.andExpect(status().isOk());
	}


    // ********************************************************************


	@DisplayName("REGISTER POST valid request Response - 302 redirect  /login - "
			+ "GIVEN POST request url /register "
			+ "WHEN Requested POST /register "
			+ "THEN returns expected 302 redirect /login http response") 
    @Test
    public void testAddNewUserWithValidInfo() throws Exception {
        mockMvc.perform(post("/register")
                .param("firstName","testfirstname")
                .param("lastName", "testlastname")
                .param("email", "testemail@email.com")
                .param("password", "testpassword")
                .param("confirmation", "testpassword"))
        		.andExpect(status().isFound())
        		.andExpect(model().hasNoErrors())
        		.andExpect(status().is(302))
        		.andExpect(view().name("redirect:/login"))
        		;
    }

	   // ******************************************************************** 
	
	@DisplayName("REGISTER POST request invalid Characters input Response - 302 - "
			+ "GIVEN POST request url /register "
			+ "WHEN Requested POST /register "
			+ "THEN returns expected 302 redirect /register http response - Non alphanumeric names not accepted") 
	@Test
	public void testAddNewUserPostRequestInvalidAlphanumericCharacters() throws Exception {
	     mockMvc.perform(post("/register")
	             .param("firstName","testfirstname/#1")
	             .param("lastName", "testlastname1#")
	             .param("email", "testemai#l1@email.com")
	             .param("password", "testpassword1")
	             .param("confirmation", "testpassword1"))
	     		.andExpect(status().isFound())
	     		.andExpect(model().hasNoErrors())
	     		.andExpect(view().name("redirect:/register?&firstName=testfirstname/#1&lastName=testlastname1#&email=testemai#l1@email.com&password=testpassword1&confirmation=testpassword1&errorMessage=Non alphanumeric names not accepted"));
 }

 // ********************************************************************


	@DisplayName("REGISTER POST request empty Firstname input Response - 302 - "
			+ "GIVEN POST request url /register empty firstname "
			+ "WHEN Requested POST /register "
			+ "THEN returns expected 302 redirect /register http response - Non alphanumeric names not accepted") 
	@Test
	public void testAddNewUserPostRequestEmptyFirstname() throws Exception {
	     mockMvc.perform(post("/register")
	             .param("firstName","")
	             .param("lastName", "testlastname")
	             .param("email", "testemail@email.com")
	             .param("password", "testpassword")
	             .param("confirmation", "testpassword"))
	     		.andExpect(status().isFound())
	     		.andExpect(model().hasNoErrors())
	     		.andExpect(view().name("redirect:/register?&firstName=&lastName=testlastname&email=testemail@email.com&password=testpassword&confirmation=testpassword&errorMessage=Non alphanumeric names not accepted"));
 }

 // ********************************************************************


	@DisplayName("REGISTER POST request empty Lastname input Response - 302 - "
			+ "GIVEN POST request url /register empty lastname"
			+ "WHEN Requested POST /register "
			+ "THEN returns expected 302 redirect /register http response - Non alphanumeric names not accepted") 
	@Test
	public void testAddNewUserPostRequestEmptyLastname() throws Exception {
	     mockMvc.perform(post("/register")
	             .param("firstName","firstname")
	             .param("lastName", "")
	             .param("email", "testemail@email.com")
	             .param("password", "testpassword")
	             .param("confirmation", "testpassword"))
	     		.andExpect(status().isFound())
	     		.andExpect(model().hasNoErrors())
	     		.andExpect(view().name("redirect:/register?&firstName=firstname&lastName=&email=testemail@email.com&password=testpassword&confirmation=testpassword&errorMessage=Non alphanumeric names not accepted"));
 }

 // ********************************************************************
	

	@DisplayName("REGISTER POST request empty Password input Response - 302 - Password Mismatch "
			+ "GIVEN POST request url /register empty Password"
			+ "WHEN Requested POST /register "
			+ "THEN returns expected 302 redirect /register http response -  Password Mismatch") 
	@Test
	public void testAddNewUserPostRequestEmptyPassword() throws Exception {
	     mockMvc.perform(post("/register")
	             .param("firstName","testFirstname")
	             .param("lastName", "testLastname")
	             .param("email", "testemail@email.com")
	             .param("password", "")
	             .param("confirmation", "testpassword"))
	     		.andExpect(status().isFound())
	     		.andExpect(model().hasNoErrors())
	     		.andExpect(view().name("redirect:/register?&firstName=testFirstname&lastName=testLastname&email=testemail@email.com&password=&confirmation=testpassword&errorMessage=Password MISMATCH"));
 }
	
	
	
	
}
