package com.paymybuddy.webapp.unitTest.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

// WebMvcTest(controllers = HelloController.class) - load only the controller by listing here
@DisplayName("Home CONTROLLER - H2 DB TEST ")
@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
class HomeControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;
    
	@Autowired
	ObjectMapper mapper;
    
   
    
	  
		// ********************************************************************

		@DisplayName("HOME Url request Without Authentication - "
			+ "GIVEN home url /home "
			+ "WHEN Requested GET /home page without authentication"
			+ "THEN returns expected redirect to (http://localhost/login)")
		@Test
		public void testHomeUrlWithoutAuthentication() throws Exception {

			mockMvc.perform(get("/home"))
			.andExpect(redirectedUrl("http://localhost/login"));
		}

		// ********************************************************************

		@DisplayName("HOME Url request With Authentication - "
			+ "GIVEN home url /home "
			+ "WHEN Requested GET /home page with authentication"
			+ "THEN returns expected Http Response : 200 OK")
		@WithMockUser("testemail1@email.com")
		@Test
		public void testHomeUrlWithLoginStatusOK() throws Exception {

			mockMvc.perform(get("/home"))
			 .andExpect(status().isOk());
			
		}
	    
		// ********************************************************************		

		@DisplayName("HOME Url request check Attributes Exists - "
			+ "GIVEN home url /home "
			+ "WHEN Requested GET /home page & check for attributes"
			+ "THEN returns expected reponse on attibutes exists")
		@WithMockUser("testemail1@email.com")
		@Test
		public void testHomeAttributesExists() throws Exception {
	        mockMvc.perform(get("/home"))
	        .andExpect(status().isOk())
	        .andExpect(model().attributeExists(
	        		"currentPage",
	        		"firstName",
	        		"wallet",
	                "transfers",
	        		"transactions",
	        		"contacts"));
		}

		// ********************************************************************
		
		@DisplayName("LOGOUT Url request without authentication redirect /login - "
				+ "GIVEN home url /logout "
				+ "WHEN Requested GET /logout page"
				+ "THEN returns expected reponse redirect to / login")
		@Test
		public void testLogoutUrlWithoutLoginRedirectToLoginUrl() throws Exception {

			mockMvc.perform(get("/logout"))
				.andExpect(status().is(302))
				.andExpect(redirectedUrl("/login"));
		}

		// ********************************************************************
		
		
		@DisplayName("LOGOUT Url request with authentication redirect /login - "
				+ "GIVEN home url /logout "
				+ "WHEN Requested GET /logout page"
				+ "THEN returns expected reponse redirect to / login")
		@WithMockUser(username="testemail1@email.com", roles={"ADMIN"} )
		@Test
		public void testLogoutUrlWithLoginStatus() throws Exception {

			mockMvc.perform(get("/logout"))
				.andExpect(status().is(302))
				.andExpect(redirectedUrl("/login"))
				.andExpect(status().isFound());
			
		}
	    
		// ********************************************************************
		   
		@DisplayName("INDEX page Url request without authentication redirect /login - "
				+ "GIVEN home url /index "
				+ "WHEN Requested GET /index page"
				+ "THEN returns expected reponse redirect to / login")   
		@Test
		public void testIndexUrlWithoutLoginRedirectToLoginUrl() throws Exception {

			mockMvc.perform(get("/index"))
			.andExpect(status().is(302))
			.andExpect(redirectedUrl("http://localhost/login"));
		}

	
		// ********************************************************************
		
		@DisplayName("INDEX page Url request with authentication redirect /login - "
				+ "GIVEN home url /index "
				+ "WHEN Requested GET /index page"
				+ "THEN returns expected reponse redirect to / login")   
		@WithMockUser(username="testemail1@email.com", roles={"ADMIN"} )
		@Test
		public void testIndexUrlWithLoginStatusOK() throws Exception {

			mockMvc.perform(get("/index"))
			.andExpect(status().isOk())
	        .andExpect(model().hasNoErrors())
	        .andExpect(view().name("login"));
			
		}
	    
		// ********************************************************************
			   		

		@DisplayName("LOGIN page Url request without authentication 200 OK - "
				+ "GIVEN home url /login "
				+ "WHEN Requested GET /login page"
				+ "THEN returns expected reponse 200 OK http response")  
		@Test
		public void testLoginUrlWithoutLoginRedirectToLoginUrl() throws Exception {

			mockMvc.perform(get("/login"))
			.andExpect(view().name("login"))
			.andExpect(model().hasNoErrors())
			.andExpect(status().isOk());
		}

		// ********************************************************************
    
		@DisplayName("LOGIN page Url request with authentication 200 OK - "
				+ "GIVEN home url /login "
				+ "WHEN Requested GET login page"
				+ "THEN returns expected reponse 200 OK http response")  
		@WithMockUser("testemail1@email.com")
		@Test
		public void testLoginUrlWithLoginStatusOK() throws Exception {

			mockMvc.perform(get("/login"))
			.andExpect(view().name("login"))
			.andExpect(model().hasNoErrors())
			 .andExpect(status().isOk());
			
		}


		// ********************************************************************

			@DisplayName("SAVE login data Url request without authentication - redirectedUrl(http://localhost/login) - "
					+ "GIVEN home url /save "
					+ "WHEN Requested GET /save page"
					+ "THEN returns expected reponse - redirectedUrl(http://localhost/login)")  
		@Test
		public void testSaveUrlWithoutLoginRedirectToLoginUrl() throws Exception {

			mockMvc.perform(get("/save"))
			.andExpect(redirectedUrl("http://localhost/login"));
		}

		// ********************************************************************
		

			@DisplayName("SAVE login data Url request with authentication but no admin status Response 403 FORBIDDEN-  - "
					+ "GIVEN home url /save "
					+ "WHEN Requested GET /save "
					+ "THEN returns expected reponse - 403 FORBIDDEN ")
			@WithMockUser("testemail1@email.com")
			@Test
			public void testSaveUrlWithLoginStatusOK() throws Exception {

				mockMvc.perform(get("/save"))
				.andExpect(status().is(HttpStatus.FORBIDDEN.value()));

			}
			
			// ********************************************************************
			
			@DisplayName("SAVE login data Url request with authentication + admin status Response 200 OK -  - "
					+ "GIVEN home url /save "
					+ "WHEN Requested GET /save with auth admin"
					+ "THEN returns expected Response 200 OK ")
		    @WithMockUser(username="testemail1@email.com", roles={"ADMIN"})
		    @Test
		    public void saveTest() throws Exception {
		        mockMvc.perform(get("/save"))
		                .andExpect(status().isOk())
		                .andExpect(model().hasNoErrors())
		                .andExpect(view().name("login"));
		    }
			
			// ********************************************************************
}
