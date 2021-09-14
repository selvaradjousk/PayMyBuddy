package com.paymybuddy.webapp.unitTest.controller;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
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

import com.paymybuddy.webapp.controller.BankTransferController;
import com.paymybuddy.webapp.dto.UserDTO;

@DisplayName("BANK TRANSFER CONTROLLER - H2 DB TEST ")
@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
class BankTransferControllerTest {


    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private BankTransferController bankTransferController;

    
    
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

	@DisplayName("POST /addTransfer page Url request DEBIT type WITH AUTH amount null 400 BAD REQUEST "
			+ "GIVEN home url /addTransfer "
			+ "WHEN Requested POST /addTransfer page"
			+ "THEN returns expected reponse 400 BAD REQUEST") 
    @WithMockUser(username="testemail1@email.com", roles={"ADMIN"})
    @Test
    public void testaddTransferDebitTypeWithouAmount() throws Exception {
        mockMvc.perform(post("/addTransfer")
                .param("rib", "fr 1111 2222 3333 44444")
                .param("amount", "")
                .param("type", "DEBIT"))
                .andExpect(status().isBadRequest())
                .andExpect(status().is(400));
    }
          
	
    // ********************************************************************

	// ZERO AMOUNT CHECK TO BE DONE IN VALIDATION
	@DisplayName("POST /addTransfer page Url request Zero Amount Value "
			+ "GIVEN home url /addTransfer "
			+ "WHEN Requested POST /addTransfer page"
			+ "THEN returns expected reponse 302 redirect /transfer") 
    @WithMockUser(username="testemail1@email.com", roles={"ADMIN"})
    @Test
    public void testaddTransferCreditTypeZeroAmountValue() throws Exception {
        mockMvc.perform(post("/addTransfer")
                .param("rib", "fr 1111 2222 3333 44444")
                .param("amount", "0")
                .param("type", "CREDIT"))
        		.andExpect(view().name("redirect:/transfer?page=0&errorMessage=Transfer saved"))
				.andExpect(redirectedUrlPattern("/transfer?page=0&errorMessage=Transfer saved"))
				.andExpect(redirectedUrl("/transfer?page=0&errorMessage=Transfer saved"))
                .andExpect(status().is(302));
    }
      
    // ********************************************************************
    
    // WRONG TRANSFER TYPE CHECK TO BE DONE IN VALIDATION
	@DisplayName("POST /addTransfer page Url request Wrong Transfer type Value "
			+ "GIVEN home url /addTransfer "
			+ "WHEN Requested POST /addTransfer page"
			+ "THEN returns expected reponse 302 redirect /transfer") 
       @WithMockUser(username="testemail1@email.com", roles={"ADMIN"})
       @Test
       public void testaddTransferCreditTypeWithWrongTransferType() throws Exception {
           mockMvc.perform(post("/addTransfer")
                   .param("rib", "fr 1111 2222 3333 44444")
                   .param("amount", "100.0")
                   .param("type", "CREDITTTINH"))
           		.andExpect(status().isFound())
                   .andExpect(status().is3xxRedirection())
                   .andExpect(view().name("redirect:/transfer?page=0&errorMessage=Transfer saved"))
   				.andExpect(redirectedUrlPattern("/transfer?page=0&errorMessage=Transfer saved"))
   				.andExpect(redirectedUrl("/transfer?page=0&errorMessage=Transfer saved"));
       }
       
       
       // ********************************************************************
	
	
	@DisplayName("POST /addTransfer page Url request DEBIT type Value "
			+ "GIVEN home url /addTransfer "
			+ "WHEN Requested POST /addTransfer page"
			+ "THEN returns expected reponse 302 redirect /transfer") 
	    @WithMockUser(username="testemail1@email.com", roles={"ADMIN"})
	    @Test
	    public void testAddTransferDeditOk() throws Exception {
	        mockMvc.perform(post("/addTransfer")
	                .param("rib", "fr 1111 2222 3333 44444")
	                .param("amount", "20.0")
	                .param("type", "DEBIT"))
	                .andExpect(status().isFound());
	    }
	    
	   
    // ********************************************************************

	@DisplayName("POST /addTransfer page Url request DEBIT type Value Invalid Amount"
			+ "GIVEN home url /addTransfer "
			+ "WHEN Requested POST /addTransfer page"
			+ "THEN returns expected reponse 400 BAD REQUEST") 
    @WithMockUser(username="testemail1@email.com", roles={"ADMIN"})
    @Test
    public void testAddTransferBadAmountValues() throws Exception {
        mockMvc.perform(post("/addTransfer")
                .param("rib", "fr 1111 2222 3333 44444")
                .param("amount", "100xxx0.0")
                .param("type", "DEBIT"))
                .andExpect(status().isBadRequest())
                .andExpect(status().is(400));
    }
    
    // ********************************************************************
 

	@DisplayName("POST /addTransfer page Url request CREDIT type Value Invalid Amount"
			+ "GIVEN home url /addTransfer "
			+ "WHEN Requested POST /addTransfer page"
			+ "THEN returns expected reponse 400 BAD REQUEST") 
    @WithMockUser(username="testemail1@email.com", roles={"ADMIN"})
    @Test
    public void testAddTransferCreditTypeBadAmountValues() throws Exception {
        mockMvc.perform(post("/addTransfer")
                .param("rib", "fr 1111 2222 3333 44444")
                .param("amount", "100xxx0.0")
                .param("type", "CREDIT"))
                .andExpect(status().isBadRequest())
                .andExpect(status().is(400));
    }
 

	   // ********************************************************************
	
		@DisplayName("POST /addBankAccount page Url request IS FOUND"
			+ "GIVEN home url /addBankAccount "
			+ "WHEN Requested POST /addBankAccount page"
			+ "THEN returns expected reponse 302 SAVED") 
		@WithMockUser(username="testemail1@email.com", roles={"ADMIN"})
	    @Test
	    public void addBankAccountTest() throws Exception {
	        mockMvc.perform(post("/addBankAccount")
	                .param("rib", "fr 1111 1111 1111"))
	                .andExpect(status().isFound())
	                .andExpect(redirectedUrl("/transfer"));
	                
	    }

	   // ********************************************************************	  
	

	    @DisplayName("checkForBankAccountRibNotNull - Rib Null - "
				+ "GIVEN checkForBankAccountRibNotNull Rib null "
				+ "WHEN Requested checkForBankAccountRibNotNull BankAccount"
				+ "THEN returns expected message redirect:/transfer?page=1&errorMessage=You must created an account")
		@Test
		public void testcheckForBankAccountRibNotNullWhenNull() {

			// GIVEN
	    	String testRib = null;
	    	
	    	// WHEN
	    	String testErrorMessage = bankTransferController.checkForBankAccountRibNotNull(1, testRib);
	    	
	    	// THEN
//	        assertThrows(NullPointerException.class, ()
//	        		-> bankTransferController.checkForBankAccountRibNotNull(1, testRib));

	    	assertEquals("redirect:/transfer?page=1&errorMessage=You must created an account", testErrorMessage);
		}
		//******************************************************************
	      
		

	    @DisplayName("checkForBankAccountRibNotNull - Rib Not Null - "
				+ "GIVEN checkForBankAccountRibNotNull Rib not null "
				+ "WHEN Requested checkForBankAccountRibNotNull BankAccount"
				+ "THEN returns expected message OK")
		@Test
		public void testcheckForBankAccountRibNotNull() {

			// GIVEN
	    	String testRib = "ribValue";
	    	
	    	// WHEN
	    	String testErrorMessage = bankTransferController.checkForBankAccountRibNotNull(1, testRib);
	    	
	    	// THEN
//	        assertThrows(NullPointerException.class, ()
//	        		-> bankTransferController.checkForBankAccountRibNotNull(1, testRib));

	    	assertEquals("OK", testErrorMessage);
		}
		//******************************************************************
	      
		

	    @DisplayName("isUserRoleAdminCheck - role admin - "
				+ "GIVEN isUserRoleAdminCheck role admin "
				+ "WHEN Requested isUserRoleAdminCheck "
				+ "THEN returns expected value admin")
		@Test
		public void testIsUserRoleAdminCheck() {

			// GIVEN
	    	String role = "ROLE_ADMIN";
	    	UserDTO userLogDTO = new UserDTO();
	    	userLogDTO.setRoles(role);
	    	
	    	// WHEN
	    	String testRole = bankTransferController.isUserRoleAdminCheck(userLogDTO);
	    	
	    	// THEN
	    	assertEquals("admin", testRole);
		}
		//******************************************************************
	      
		

	    @DisplayName("isUserRoleAdminCheck - role not admin - "
				+ "GIVEN isUserRoleAdminCheck role not admin "
				+ "WHEN Requested isUserRoleAdminCheck"
				+ "THEN returns expected value user")
		@Test
		public void testIsUserRoleAdminCheckNotAdmin() {

			// GIVEN
	    	String role = "ROLE_USER";
	    	UserDTO userLogDTO = new UserDTO();
	    	userLogDTO.setRoles(role);
	    	
	    	// WHEN
	    	String testRole = bankTransferController.isUserRoleAdminCheck(userLogDTO);
	    	
	    	// THEN

	    	assertNull(testRole);
		}
		//******************************************************************
	      
	
	
}
