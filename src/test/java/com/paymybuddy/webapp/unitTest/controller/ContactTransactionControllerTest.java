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


	// ********************************************************************

	@DisplayName("/transaction page Url request with authentication 200 OK - "
			+ "GIVEN home url /transaction "
			+ "WHEN Requested GET /transaction page"
			+ "THEN returns expected reponse 200 OK")  
	@WithMockUser("testemail1@email.com")
	@Test
	public void testTransactionUrlWithLoginStatusOK() throws Exception {
//		List<Transaction> transactionsList = new ArrayList<>();
		mockMvc.perform(get("/transaction"))
		 .andExpect(status().isOk())
		 .andExpect(view().name("transaction"))
		 .andExpect(model().hasNoErrors());
		
	}
    
	// ********************************************************************
	
	
	@DisplayName("/transaction page Url request page attributes exists 200 OK - "
			+ "GIVEN home url /transaction "
			+ "WHEN Requested GET /transaction page for attributes check"
			+ "THEN returns expected reponse page attributes exists 200 OK")  
	@WithMockUser(username="testemail1@email.com", roles={"ADMIN"})
	@Test
	public void testTransactionAttributesExists() throws Exception {
        mockMvc.perform(get("/transaction"))
        .andExpect(status().isOk())
        .andExpect(model().attributeExists(
        		"contacts",
        		"transactions",
        		"pages",
                "errorMessage",
        		"currentPage",
        		"description",
        		"contact"))
        		.andExpect(model().hasNoErrors());
	}





}
