package com.paymybuddy.webapp.unitTest.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paymybuddy.webapp.controller.BankAccountController;
import com.paymybuddy.webapp.dto.BankAccountDTO;
import com.paymybuddy.webapp.model.BankAccount;
import com.paymybuddy.webapp.model.User;
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
    
    @DisplayName("Bank Account /manage/bankAccounts Url load request With Authentication - "
			+ "GIVEN home url /manage/bankAccounts "
			+ "WHEN Requested GET /manage/bankAccounts page with authentication"
			+ "THEN returns expected 200 OK response")
    @WithMockUser(username="testemail1@email.com", roles={"ADMIN"} )
    @Test
    public void testFindAllBankAccountOK() throws Exception {
        mockMvc.perform(get("/manage/bankAccounts"))
                .andExpect(status().isOk());
    }
    
    
    // ********************************************************************

    
    @DisplayName("Bank Account /manage/bankAccounts Url load request Without Authentication - "
			+ "GIVEN home url /manage/bankAccounts "
			+ "WHEN Requested GET /manage/bankAccounts page without authentication"
			+ "THEN returns expected 302 REDIRECT: http://localhost/login response")
        @Test
    public void testFindAllBankAccountWithoutAuth() throws Exception {
        mockMvc.perform(get("/manage/bankAccounts"))
                .andExpect(status().is(302))
                .andExpect(redirectedUrlPattern("**/login"))
                .andExpect(redirectedUrl("http://localhost/login"));
    }
    
    
    // ********************************************************************

//    @DisplayName("Bank Account POST /addBankAccount wrong Url load request With Authentication - "
//			+ "GIVEN home wrong url /addBankAccount "
//			+ "WHEN Requested POST /addBankAccount page with authentication"
//			+ "THEN returns expected 302 REDIRECT: http://localhost/login response")
//    @WithMockUser(username="testemail1@email.com", roles={"admin"})
//    @Test
//    public void testAddBankAccountWithWrongURL() throws Exception {
//    	
//   	
//        mockMvc.perform(post("/addBankAccount")
//                .param("rib", "1111 1111 1111 1111"))
//        		.andExpect(model().hasNoErrors())
//                .andExpect(status().isFound())
//                .andExpect(redirectedUrl("/transfer"))
//                .andExpect(view().name("redirect:/transfer"));
//    }
//    
 
    //********************************************************************
    

    @DisplayName("Bank Account POST /manage/addBankAccount Url load request With Authentication - "
 			+ "GIVEN home url /manage/addBankAccount "
 			+ "WHEN Requested POST /manage/addBankAccount page with authentication"
 			+ "THEN returns expected 201 CREATED response")
     @WithMockUser(username="testemail1@email.com")
     @Test
     public void testAddBankTestWithManageMapping() throws Exception {
         String emailSession = SecurityContextHolder.getContext().getAuthentication().getName();
         User user = userRepository.findUserByEmail(emailSession);
         BankAccountDTO bankToCreateDTO = new BankAccountDTO(user, "fr 1111 1111 1111");

        mockMvc.perform(post("/manage/bankAccount")
                 .contentType("application/json")
                 .accept(MediaType.APPLICATION_JSON)
                 .content(objectMapper.writeValueAsString(bankToCreateDTO)))
                 .andExpect(status().isCreated());

     }
     
       //******************************************************************** 
   
    @DisplayName("Bank Account POST /manage/addBankAccount Url Account null 400 BAD REQUEST- "
 			+ "GIVEN home url /manage/addBankAccount "
 			+ "WHEN Requested POST /manage/addBankAccount account null"
 			+ "THEN returns expected 400 BAD REQUEST response")
    @WithMockUser(username = "testemail1@email.com")
    @Test
    public void testAddBankNAccountNull() throws Exception {

        BankAccount bankNull = null;

        mockMvc.perform(post("/manage/bankAccount")
                .contentType("application/json")
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bankNull)))
                .andExpect(status().isBadRequest());

    }
    
    //********************************************************************
    

    @DisplayName("Bank Account PUT /manage/addBankAccount request With Authentication - "
 			+ "GIVEN url /manage/addBankAccount "
 			+ "WHEN Requested PUT /manage/addBankAccount page with authentication"
 			+ "THEN returns expected 201 CREATED response")
    @WithMockUser(username = "testemail1@email.com")
    @Test
    public void testUpdateBankAccount() throws Exception {
        String emailSession = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findUserByEmail(emailSession);
        BankAccount bankToCreate = new BankAccount(1,user,"fr 1111 1111 1111");

        mockMvc.perform(put("/manage/bankAccount")
                .contentType("application/json")
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bankToCreate)))
                .andExpect(status().isCreated());
    }
    
    //********************************************************************
    
    @DisplayName("Bank Account PUT /manage/addBankAccount account null 400 BAD REQUEST - "
 			+ "GIVEN url /manage/addBankAccount account null"
 			+ "WHEN Requested PUT /manage/addBankAccount "
 			+ "THEN returns expected 400 BAD_REQUEST response")
    @WithMockUser(username = "testemail1@email.com")
    @Test
    public void testUpdateBankAccountNullAccount() throws Exception {
        String emailSession = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findUserByEmail(emailSession);
        BankAccount bankToCreate = null;

        mockMvc.perform(put("/manage/bankAccount")
                .contentType("application/json")
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bankToCreate)))
                .andExpect(status().isBadRequest());
    }
    
    //********************************************************************
    
    
     //******************************************************************** 
    
    
}
