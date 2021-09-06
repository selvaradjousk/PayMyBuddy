package com.paymybuddy.webapp.unitTest.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

	   
	@DisplayName("GET /transaction page Url request without authentication redirect /login - "
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

	@DisplayName("GET /transaction page Url request with authentication 200 OK - "
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
	
	
	@DisplayName("GET /transaction page Url request page attributes exists 200 OK - "
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

	@DisplayName("POST /transaction page Url request no Authetication 302 redirect /login - "
			+ "GIVEN home url /transaction "
			+ "WHEN Requested POST /transaction"
			+ "THEN returns expected reponse 302 redirect /login") 
    @Test
    public void testAddTransactionWithoutAuthetication () throws Exception {
        mockMvc.perform(post("/transaction")
                .param("contactEmail","testemail2@email.com")
                .param("amount", "10.0")
                .param("description", "something"))
        		.andExpect(status().is(302))
				.andExpect(redirectedUrl("http://localhost/login"));
    }
	
	// ********************************************************************

	@DisplayName("POST /transaction page Url request + Authetication TRANSACTION SAVED- "
			+ "GIVEN home url /transaction "
			+ "WHEN Requested POST /transaction "
			+ "THEN returns expected reponse TRANSACTION SAVED") 
	@WithMockUser(username="testemail1@email.com", roles={"ADMIN"})
    @Test
    public void testAddTransaction () throws Exception {
        mockMvc.perform(post("/transaction")
                .param("contactEmail","testemail2@email.com")
                .param("amount", "10.0")
                .param("description", "something"))
        		.andExpect(model().hasNoErrors())
        		.andExpect(view().name("redirect:/transaction?page=0&errorMessage=Transaction saved&contactEmail=testemail2@email.com&amount=10.0&description=something"))
                .andExpect(status().isFound());
    }
	
	// ********************************************************************

	@DisplayName("POST /transaction page Url request + Auth Empty Email 302 Message = You must choose an emailD- "
			+ "GIVEN home url /transaction "
			+ "WHEN Requested POST /transaction no email"
			+ "THEN returns expected reponse - You must choose an email") 
	@WithMockUser(username="testemail1@email.com", roles={"ADMIN"})
    @Test
    public void testAddTransactionWithoutContactEmail() throws Exception {
        mockMvc.perform(post("/transaction")
                .param("contactEmail","")
                .param("amount", "10.0")
                .param("description", "something"))
        		.andExpect(status().is(302))
        		.andExpect(view().name("redirect:/transaction?page=0&errorMessage=You must choose an email! &contactEmail=&amount=10.0&description=something"));
    }
    
	// ********************************************************************
	
	// NEEDS VALIDATION CHECK AT SERVICE LAYER OR CONTROLLER !!!!!
	@DisplayName("POST /transaction page Url request + Auth NEGATIVE AMOUNT VALUE 302 Message = Trasaction Saved - "
			+ "GIVEN home url /transaction "
			+ "WHEN Requested POST /transaction negative amount value"
			+ "THEN returns expected reponse - Transaction Saved") 
	@WithMockUser(username="testemail1@email.com", roles={"ADMIN"})
    @Test
    public void testAddTransactionWithNegativeAmount() throws Exception {
        mockMvc.perform(post("/transaction")
                .param("contactEmail","testemail2@email.com")
                .param("amount", "-1")
                .param("description", "something"))
        		.andExpect(status().is(302))
        		.andExpect(view().name("redirect:/transaction?page=0&errorMessage=Transaction saved&contactEmail=testemail2@email.com&amount=-1.0&description=something"))
//        		.andExpect(model().hasErrors())
//        		.andExpect(model().attributeHasFieldErrorCode("transaction", "amount", ""))
        		;
    }

    
	// ********************************************************************
	
	@DisplayName("POST /transaction page Url request + Auth INVALID AMOUNT VALUE 400 BAD REQUEST - "
			+ "GIVEN home url /transaction "
			+ "WHEN Requested POST /transaction invalid amount value"
			+ "THEN returns expected reponse - 400 BAD REQUEST") 
	@WithMockUser(username="testemail1@email.com", roles={"ADMIN"})
    @Test
    public void testAddTransactionWithWrongTypingAmount() throws Exception {
        mockMvc.perform(post("/transaction")
                .param("contactEmail","testemail2@email.com")
                .param("amount", "^^^^")
                .param("description", "something"))
        		.andExpect(status().is(400))
        		.andExpect(status().isBadRequest());
    }
       
	// ********************************************************************
    
	// NEEDS VALIDATION CHECK AT SERVICE LAYER OR CONTROLLER !!!!!
	@DisplayName("POST /transaction page Url request + Auth ZERO AMOUNT VALUE 302 Message = Trasaction Saved - "
			+ "GIVEN home url /transaction "
			+ "WHEN Requested POST /transaction ZERO amount value"
			+ "THEN returns expected reponse - Transaction Saved") 
	@WithMockUser(username="testemail1@email.com", roles={"ADMIN"})
    @Test
    public void testAddTransactionWithAmountZero() throws Exception {
        mockMvc.perform(post("/transaction")
                .param("contactEmail","testemail2@email.com")
                .param("amount", "0")
                .param("description", "something"))
        		.andExpect(status().is(302))
        		.andExpect(view().name("redirect:/transaction?page=0&errorMessage=Transaction saved&contactEmail=testemail2@email.com&amount=0.0&description=something"))
//        		.andExpect(model().hasErrors())
//        		.andExpect(model().attributeHasFieldErrorCode("transaction", "amount", ""))
        		;
    }

	// ********************************************************************  


}
