package com.paymybuddy.webapp.unitTest.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
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
import com.paymybuddy.webapp.controller.BankAccountController;
import com.paymybuddy.webapp.repository.UserRepository;
import com.paymybuddy.webapp.service.IBankAccountService;

@DisplayName("BANK ACCOUNT CONTROLLER - H2 DB TEST ")
@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
class BankAccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    UserRepository userRepository;
    
    @Autowired
    IBankAccountService bankAccountService;
    
    @Autowired
    BankAccountController bankAccountController;

    @Autowired
    private ObjectMapper objectMapper;
 
  
    
    //********************************************************************
    
    @DisplayName("Bank Account Url load request Without Authentication - "
			+ "GIVEN home url /bankAccounts "
			+ "WHEN Requested GET /bankAccounts page without authentication"
			+ "THEN returns expected redirect to (/login")
    @Test
    public void testGetBankAccountPageWithoutAuthentication() throws Exception {
        mockMvc.perform(get("/bankAccounts"))
                .andExpect(status().is(302))
                .andExpect(redirectedUrlPattern("**/login"));

    }
    
  //********************************************************************

    @DisplayName("Bank Account invalid Url load request With Authentication - "
			+ "GIVEN home url /bankAccounts "
			+ "WHEN Requested GET /bankAccounts page with authentication"
			+ "THEN returns expected 404 Not found response")
    @WithMockUser(username="testemail1@email.com", roles={"ADMIN"} )
    @Test
    public void testGetBankAccountPageWithAuthenticationTest() throws Exception {
        mockMvc.perform(get("/bankAccounts"))
                .andExpect(status().is(404));
    }
    
    //********************************************************************
    
    
    
}
