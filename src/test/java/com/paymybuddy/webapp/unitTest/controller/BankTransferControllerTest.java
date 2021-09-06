package com.paymybuddy.webapp.unitTest.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
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

@DisplayName("BANK TRANSFER CONTROLLER - H2 DB TEST ")
@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
class BankTransferControllerTest {


    @Autowired
    private MockMvc mockMvc;

    
    
	// ********************************************************************

	@DisplayName("GET /transfer page Url request without authentication redirect /login - "
			+ "GIVEN home url /transfer "
			+ "WHEN Requested GET /transfer page"
			+ "THEN returns expected reponse redirect to / login")   
	@Test
	public void testBankTransferUrlWithoutLoginRedirectToLoginUrl() throws Exception {

		mockMvc.perform(get("/transfer"))
		.andExpect(status().is(302))
		.andExpect(redirectedUrlPattern("**/login"))
		.andExpect(redirectedUrl("http://localhost/login"));
	}

	// ********************************************************************

	@DisplayName("GET /transfer page Url request with authentication (200) OK /transfer - "
			+ "GIVEN home url /transfer "
			+ "WHEN Requested GET /transfer page"
			+ "THEN returns expected reponse 200 OK")   
	@WithMockUser("testemail1@email.com")
	@Test
	public void testBankTransferUrlWithLoginStatusOK() throws Exception {

		mockMvc.perform(get("/transfer"))
		.andExpect(model().hasNoErrors())
		.andExpect(view().name("transfer"))
		 .andExpect(status().isOk());
		
	}
	
	
	// ********************************************************************
	
	@DisplayName("GET /transfer page Url request attribute exists - "
			+ "GIVEN home url /transfer "
			+ "WHEN Requested GET /transfer page"
			+ "THEN returns expected reponse 200 OK  attribute exists") 
	@WithMockUser("testemail1@email.com")
	@Test
	public void testBankTransferAttributesExists() throws Exception {
        mockMvc.perform(get("/transfer"))
        .andExpect(status().isOk())
        .andExpect(model().attributeExists(
        		"transfers",
        		"bankAccounts",
        		"pages",
        		"currentPage",
                "errorMessage"))
        		.andExpect(model().hasNoErrors())
        		.andExpect(view().name("transfer"));
	}
    
	// ********************************************************************

	@DisplayName("GET /transfer page Url request attribute exists 302 redirect /login- "
			+ "GIVEN home url /transfer "
			+ "WHEN Requested GET /transfer page"
			+ "THEN returns expected reponse 302 redirect /login") 
	@Test
	public void testBankTransferAttributesExistsWithoutAuthetication() throws Exception {
        mockMvc.perform(get("/transfer"))
        		.andExpect(status().is(302))
        		.andExpect(redirectedUrlPattern("**/login"))
        		.andExpect(redirectedUrl("http://localhost/login"));;
	}  	

	
	// ********************************************************************
	
	@DisplayName("POST /addTransfer page Url request NO AUTH 302 redirect /login- "
			+ "GIVEN home url /addTransfer "
			+ "WHEN Requested POST /addTransfer page"
			+ "THEN returns expected reponse 302 redirect /login") 
    @Test
    public void testaddTransferCreditTypeWithoutAuthetication() throws Exception {
        mockMvc.perform(post("/addTransfer")
                .param("rib", "fr 1111 2222 3333 44444")
                .param("amount", "200.0")
                .param("type", "CREDIT"))
                .andExpect(status().isFound())
//				.andExpect(redirectedUrlPattern("http://localhost/login"))
				.andExpect(redirectedUrl("http://localhost/login"));
    }
	
	   
    // ********************************************************************
	
	@DisplayName("POST /addTransfer page Url request WITH AUTH 302 /transfer?page=0&errorMessage=Transfer saved "
			+ "GIVEN home url /addTransfer "
			+ "WHEN Requested POST /addTransfer page"
			+ "THEN returns expected reponse 302 /transfer?page=0&errorMessage=Transfer saved") 
    @WithMockUser(username="testemail1@email.com", roles={"ADMIN"})
    @Test
    public void testaddTransferCreditTypeWithAuthetication() throws Exception {
        mockMvc.perform(post("/addTransfer")
                .param("rib", "fr 1111 2222 3333 44444")
                .param("amount", "200.0")
                .param("type", "CREDIT"))
                .andExpect(status().isFound())
				.andExpect(redirectedUrlPattern("/transfer?page=0&errorMessage=Transfer saved"))
				.andExpect(redirectedUrl("/transfer?page=0&errorMessage=Transfer saved"));
    }
    
    // ********************************************************************   

	@DisplayName("POST /addTransfer page Url request WITH AUTH  400 BAD REQUEST "
			+ "GIVEN home url /addTransfer "
			+ "WHEN Requested POST /addTransfer page"
			+ "THEN returns expected reponse 400 BAD REQUEST") 
    @WithMockUser(username="testemail1@email.com", roles={"ADMIN"})
    @Test
    public void testaddTransferCreditTypeWithouAmount() throws Exception {
        mockMvc.perform(post("/addTransfer")
                .param("rib", "fr 1111 2222 3333 44444")
                .param("amount", "")
                .param("type", "CREDIT"))
                .andExpect(status().isBadRequest())
                .andExpect(status().is(400));
    }
          
    // ********************************************************************
	
	
	
}
