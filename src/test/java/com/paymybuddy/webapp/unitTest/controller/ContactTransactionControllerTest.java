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

@DisplayName("TRANSACTION CONTROLLER - H2 DB TEST ")
@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
class ContactTransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // ********************************************************************

	   
	@DisplayName("/transaction page Url request without authentication redirect /login - "
			+ "GIVEN home url /transaction "
			+ "WHEN Requested GET /transaction page"
			+ "THEN returns expected reponse redirect to / login")   
	@Test
	public void testTransactionUrlWithoutAuthentication() throws Exception {

		mockMvc.perform(get("/transaction"))
		.andExpect(status().is(302))
		.andExpect(redirectedUrl("http://localhost/login"));
	}


}
