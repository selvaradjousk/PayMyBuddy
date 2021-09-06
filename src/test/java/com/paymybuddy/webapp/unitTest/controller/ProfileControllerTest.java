package com.paymybuddy.webapp.unitTest.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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
}
