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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;


@DisplayName("PROFILE CONTROLLER - H2 DB TEST ")
@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
class ProfileControllerTest {

	@Autowired
	private MockMvc mockMvc;

	// ********************************************************************

	@DisplayName("PROFILE page Url request without authentication Response - 302 redirect /login - "
			+ "GIVEN Profile url /profile without authentication"
			+ "WHEN Requested GET /profile page"
			+ "THEN returns expected 302 http response - redirect /login") 
	@Test
	public void testProfileUrlWithoutLoginRedirectToLoginUrl() throws Exception {

		mockMvc.perform(get("/profile"))
		.andExpect(status().is(302))
		.andExpect(redirectedUrl("http://localhost/login"));
	}
	
	
	// ********************************************************************

	@DisplayName("PROFILE page Url request with authentication Response - 200 OK - "
		+ "GIVEN Profile url /profile with authentication"
		+ "WHEN Requested GET /profile page"
		+ "THEN returns expected 200 OK http response") 
	@WithMockUser(username="testemail1@email.com", roles={"ADMIN"} )	
	@Test
	public void testProfileUrlWithLoginStatusOK() throws Exception {

		mockMvc.perform(get("/profile"))
		 .andExpect(status().isOk())
		 .andExpect(model().hasNoErrors());
		
	}
	
	// ********************************************************************
	
	@DisplayName("PROFILE page Url request with authentication Attributes exists - "
			+ "GIVEN Profile url /profile with authentication attributes exists"
			+ "WHEN Requested GET /profile page"
			+ "THEN returns expected 200 OK http response") 
	@WithMockUser(username="testemail1@email.com", roles={"ADMIN"} )
	@Test
	public void testProfileAttributesExists() throws Exception {
        mockMvc.perform(get("/profile"))
        .andExpect(status().isOk())
        .andExpect(model().attributeExists(
        		"firstName",
        		"lastName",
        		"email",
        		"password",
                "confirmation"))
        		.andExpect(view().name("profile"))
        		.andExpect(model().hasNoErrors());
	}

	// ********************************************************************	
	
}