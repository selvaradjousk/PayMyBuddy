package com.paymybuddy.webapp.IT.service;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.paymybuddy.webapp.controller.ContactController;
import com.paymybuddy.webapp.service.IContactService;

@DisplayName("CONTACT CONTROLLER - IT DB TEST ")
@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("integration")
@ExtendWith(MockitoExtension.class)
class ControllerContact_IT {

	@Autowired
    private MockMvc mockMvc;
	
	@Autowired
	IContactService contactService;
	
	@InjectMocks
	ContactController contactController;
	
	

    // ********************************************************************
	
	@DisplayName("GET /contact page Url request without authentication redirect /login - "
			+ "GIVEN home url /contact "
			+ "WHEN Requested GET /contact page"
			+ "THEN returns expected reponse redirect to /login")  
	@Test
	public void testContactUrlWithoutLoginRedirectToLoginUrl() throws Exception {

		mockMvc.perform(get("/contact"))
		.andExpect(status().is(302))
		.andExpect(redirectedUrl("http://localhost/login"));
	}

	// ********************************************************************
	@DisplayName("GET /contact page Url request with authentication 200 OK /contact - "
			+ "GIVEN home url /contact "
			+ "WHEN Requested GET /contact page"
			+ "THEN returns expected reponse 200 OK to /contact") 
	@WithMockUser("testemail1@email.com")
	@Test
	public void testContactUrlWithLoginStatusOK() throws Exception {

		mockMvc.perform(get("/contact"))
		 .andExpect(status().isOk())
         .andExpect(view().name("contact"))
         .andExpect(model().attribute("errorMessage", ""));
		
	}
	
	// ********************************************************************
	

	@DisplayName("GET /contact page Url request with AUTH page attributes exists 200 OK - "
			+ "GIVEN home url /contact "
			+ "WHEN Requested GET /contact page"
			+ "THEN returns expected reponse 200 OK page attributes exists") 
    @WithMockUser(username="testemail1@email.com")
    @Test
    public void testContactAttributesExist() throws Exception {

        mockMvc.perform(get("/contact"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists(
                		"contacts",
                		"notContacts",
                		"errorMessage",
                		"currentPage"))
                .andExpect(view().name("contact"))
                .andExpect(model().attribute("errorMessage", ""));
    }
    
    // ********************************************************************  
	
	@DisplayName("GET /addContact page Url request without AUTH - 302 redirect /login - "
			+ "GIVEN home url /addContact "
			+ "WHEN Requested GET /addContact page"
			+ "THEN returns expected reponse 302 redirect /login ") 
	@Test
	public void testAddContactUrlWithoutLoginRedirectToLoginUrl() throws Exception {

		mockMvc.perform(get("/addContact"))
		.andExpect(status().is(302))
		.andExpect(redirectedUrl("http://localhost/login"));
	}

	// ********************************************************************
	
	@DisplayName("GET /addContact page Url request with AUTH Login Status not found - "
			+ "GIVEN home url /addContact "
			+ "WHEN Requested GET /addContact page"
			+ "THEN returns expected reponse NOT FOUND") 
	@WithMockUser(username= "testemail1@email.com")
	@Test
	public void testAddContactUrlWithLoginStatus() throws Exception {

		mockMvc.perform(get("/addContact/{idContact}", 1))
		.andExpect(status().is(404))
		 .andExpect(status().isNotFound());
		
	}
	
	// ********************************************************************
	@DisplayName("GET /addContact page Url request with AUTH Login Status FOUND - "
			+ "GIVEN home url /addContact "
			+ "WHEN Requested GET /addContact page"
			+ "THEN returns expected reponse FOUND") 
	 @WithMockUser(username="testemail1@email.com")
	    @Test
	    public void addContactTest() throws Exception {
	        mockMvc.perform(get("/addContact")
	                .param("idContact", "1"))
	                .andExpect(status().isFound());
	    }
	
	
	// ********************************************************************

	@DisplayName("GET /deleteContact page Url request without AUTH 302 redirect /login - "
			+ "GIVEN home url /deleteContact "
			+ "WHEN Requested GET /deleteContact page"
			+ "THEN returns expected reponse 302 redirect /login") 
	@Test
	public void testDeleteContactUrlWithoutLoginRedirectToLoginUrl() throws Exception {

		mockMvc.perform(get("/deleteContact"))
		.andExpect(status().is(302))
		.andExpect(redirectedUrl("http://localhost/login"));
	}
	
	// ******************************************************************** 
	
	@DisplayName("GET /deleteContact page Url request without AUTH 302 redirect /login - "
			+ "GIVEN home url /deleteContact "
			+ "WHEN Requested GET /deleteContact page"
			+ "THEN returns expected reponse 302 redirect /login")
	@WithMockUser(username="testemail1@email.com", roles={"ADMIN"})
	    @Test
	    public void testDeleteContact() throws Exception {
	        mockMvc.perform(get("/deleteContact")
	                .param("idContact", "1"))
	        		.andExpect(status().is(302))
	                .andExpect(status().isFound())
	                .andExpect(redirectedUrl("/contact"));
	    }
    	

	// ******************************************************************** 

	
}
