package com.paymybuddy.webapp.unitTest.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@DisplayName("BANK TRANSFER CONTROLLER - H2 DB TEST ")
@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
class BankTransferControllerTest {


    @Autowired
    private MockMvc mockMvc;

    
    
	// ********************************************************************

	@Test
	public void testBankTransferUrlWithoutLoginRedirectToLoginUrl() throws Exception {

		mockMvc.perform(get("/transfer"))
		.andExpect(status().is(302))
		.andExpect(redirectedUrlPattern("**/login"))
		.andExpect(redirectedUrl("http://localhost/login"));
	}

}
