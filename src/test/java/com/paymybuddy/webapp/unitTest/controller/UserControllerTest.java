package com.paymybuddy.webapp.unitTest.controller;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
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
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
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
	
	
	// ********************************************************************
	

		@DisplayName("REGISTER POST request empty Confirmation Password input Response - 302 - Password Mismatch "
				+ "GIVEN POST request url /register empty Confirmation Password"
				+ "WHEN Requested POST /register "
				+ "THEN returns expected 302 redirect /register http response -  Password Mismatch") 
		@Test
		public void testAddNewUserPostRequestEmptyConfirmationPassword() throws Exception {
		     mockMvc.perform(post("/register")
		             .param("firstName","testFirstname")
		             .param("lastName", "testLastname")
		             .param("email", "testemail@email.com")
		             .param("password", "testpassword")
		             .param("confirmation", ""))
		     		.andExpect(status().isFound())
		     		.andExpect(model().hasNoErrors())
		     		.andExpect(view().name("redirect:/register?&firstName=testFirstname&lastName=testLastname&email=testemail@email.com&password=testpassword&confirmation=&errorMessage=Password MISMATCH"));
	 }
		
		
		// ********************************************************************
	

		@DisplayName("REGISTER POST request empty Passwords input Response - 302 - Password NEEDED "
				+ "GIVEN POST request url /register empty Passwords"
				+ "WHEN Requested POST /register "
				+ "THEN returns expected 302 redirect /register http response -  Password NEEDED") 
		@Test
		public void testAddNewUserPostRequestEmptyPasswords() throws Exception {
		     mockMvc.perform(post("/register")
		             .param("firstName","testFirstname")
		             .param("lastName", "testLastname")
		             .param("email", "testemail@email.com")
		             .param("password", "")
		             .param("confirmation", ""))
		     		.andExpect(status().isFound())
		     		.andExpect(model().hasNoErrors())
		     		.andExpect(view().name("redirect:/register?&firstName=testFirstname&lastName=testLastname&email=testemail@email.com&password=&confirmation=&errorMessage=Password NEEDED"));
	 }
		
		// ********************************************************************
	

		@DisplayName("REGISTER POST request all empty input Response - 302 - Password NEEDED "
				+ "GIVEN POST request url /register all empty input"
				+ "WHEN Requested POST /register "
				+ "THEN returns expected 302 redirect /register http response -  Password NEEDED") 
		@Test
		public void testAddNewUserPostRequestAllEmpty() throws Exception {
		     mockMvc.perform(post("/register")
		             .param("firstName","")
		             .param("lastName", "")
		             .param("email", "testemail@email.com")
		             .param("password", "")
		             .param("confirmation", ""))
		     		.andExpect(status().isFound())
		     		.andExpect(model().hasNoErrors())
		     		.andExpect(view().name("redirect:/register?&firstName=&lastName=&email=testemail@email.com&password=&confirmation=&errorMessage=Password NEEDED"));
	 }				

		// ********************************************************************
		

		@DisplayName("REGISTER POST request Wrong email Format input Response - 302 - ENTER EMAIL ID "
				+ "GIVEN POST request url /register Wrong email Format input"
				+ "WHEN Requested POST /register "
				+ "THEN returns expected 302 redirect /register http response -  Please Enter Email ID") 
		@Test
		public void testAddNewUserPostRequestemailFormatWrong() throws Exception {
		     mockMvc.perform(post("/register")
		             .param("firstName","testFirstname")
		             .param("lastName", "testLastname")
		             .param("email", "testemailemail.com")
		             .param("password", "testpassword")
		             .param("confirmation", "testpassword"))
		     		.andExpect(status().isFound())
		     		.andExpect(model().hasNoErrors())
		     		.andExpect(view().name("redirect:/register?&firstName=testFirstname&lastName=testLastname&email=testemailemail.com&password=testpassword&confirmation=testpassword&errorMessage=Please enter EMAIL ID"));
	 }
		
		// ********************************************************************
		

		@DisplayName("REGISTER POST request Wrong email Format no domain name input Response - 302 - ENTER EMAIL ID "
				+ "GIVEN POST request url /register Wrong email Format no domain name input"
				+ "WHEN Requested POST /register "
				+ "THEN returns expected 302 redirect /register http response -  Please Enter Email ID") 
		@Test
		public void testAddNewUserPostRequestemailFormatWrongwithomDomainName() throws Exception {
		     mockMvc.perform(post("/register")
		             .param("firstName","testFirstname")
		             .param("lastName", "testLastname")
		             .param("email", "testemail@email")
		             .param("password", "testpassword")
		             .param("confirmation", "testpassword"))
		     		.andExpect(status().isFound())
		     		.andExpect(model().hasNoErrors())
		     		.andExpect(view().name("redirect:/register?&firstName=testFirstname&lastName=testLastname&email=testemail@email&password=testpassword&confirmation=testpassword&errorMessage=Please enter EMAIL ID"));
	 }
		
		
		// ********************************************************************
	    
		@DisplayName("AUTHorisation request check "
				+ "GIVEN GET request url /register auth request private service"
				+ "WHEN Requested GET /register "
				+ "THEN returns expected 200 OK")
	    @WithMockUser("testemail1@email.com")
	    @Test
	    public void givenAuthRequestOnPrivateService_shouldSucceedWith200() throws Exception {
	    	
	    	mockMvc = MockMvcBuilders
	                .webAppContextSetup(context)
	                .apply(springSecurity())
	                .build();

	    	
	    	mockMvc.perform(get("/register")
	    			.contentType(MediaType.APPLICATION_JSON))
	          .andExpect(status().isOk());
	    }		

		
	// ********************************************************************

}
