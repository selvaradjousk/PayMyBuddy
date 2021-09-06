package com.paymybuddy.webapp.unitTest.config;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@DisplayName("SECURITY CONTROLLER - H2 DB TEST ")
@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
class SecurityControllerTest {

	
    @Autowired
    private MockMvc mockMvc;
    
	@Autowired
	ObjectMapper mapper;
	
	// ********************************************************************

	@DisplayName("SECURITY Url request Without Authentication - "
		+ "GIVEN SECURITY url /notAuthorized "
		+ "WHEN Requested GET /notAuthorized page without authentication"
		+ "THEN returns expected redirect to (http://localhost/login)")
	@Test
	public void testHomeUrlWithoutAuthentication() throws Exception {

		mockMvc.perform(get("/notAuthorized"))
		.andExpect(redirectedUrl("http://localhost/login"));
	}
	
	
	// ********************************************************************

	@DisplayName("SECURITY Url request With Authentication - "
		+ "GIVEN SECURITY url /notAuthorized "
		+ "WHEN Requested GET /notAuthorized page with authentication"
		+ "THEN returns expected Response OK 200")
	@WithMockUser("testemail1@email.com")
	@Test
	public void testHomeUrlWithAuthentication() throws Exception {

		mockMvc.perform(get("/notAuthorized"))
		.andExpect(status().isOk());
	}
	// ********************************************************************
}
