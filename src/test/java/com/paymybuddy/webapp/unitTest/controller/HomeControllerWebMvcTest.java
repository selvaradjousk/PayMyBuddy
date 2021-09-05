package com.paymybuddy.webapp.unitTest.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
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

// WebMvcTest(controllers = HelloController.class) - load only the controller by listing here
@DisplayName("Home CONTROLLER - H2 DB TEST ")
@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
class HomeControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;
    
	@Autowired
	ObjectMapper mapper;
    
    

//    @Test
//    public void testHomeShouldReturnMessage() throws Exception {
//    mockMvc.perform(MockMvcRequestBuilders.get("/home"))
//    .andExpect(MockMvcResultMatchers.status().is(200))
//    .andExpect(MockMvcResultMatchers.content().string("home"))
//    .andExpect(MockMvcResultMatchers.header().string("Content-Type", "text/plain;charset=UTF-8"))
//    .andExpect(MockMvcResultMatchers.header().string("Content-Length", "4")); 
////    .andExpect(model().attribute("msg", equalTo("SUCCESS LODAING HOME PAGE")))
//    }
//
//    @Test
//    public void testLoginShouldReturnMessage() throws Exception {
//    mockMvc.perform(MockMvcRequestBuilders.get("/login"))
//    .andExpect(MockMvcResultMatchers.status().is(200))
//    .andExpect(MockMvcResultMatchers.content().string("login"))
//    .andExpect(MockMvcResultMatchers.header().string("Content-Type", "text/plain;charset=UTF-8"))
//    .andExpect(MockMvcResultMatchers.header().string("Content-Length", "5")); 
//    }
//    
//    @Test
//    public void testLogoutShouldReturnMessage() throws Exception {
//    mockMvc.perform(MockMvcRequestBuilders.get("/logout"))
//    .andExpect(MockMvcResultMatchers.status().is(302)); 
//    }
//    
    
    
	  
		// ********************************************************************

		@DisplayName("HOME Url request Without Authentication - "
			+ "GIVEN home url /home "
			+ "WHEN Requested GET /home page without authentication"
			+ "THEN returns expected redirect to (http://localhost/login)")
		@Test
		public void testHomeUrlWithoutAuthentication() throws Exception {

			mockMvc.perform(get("/home"))
			.andExpect(redirectedUrl("http://localhost/login"));
		}

		// ********************************************************************

		@DisplayName("HOME Url request With Authentication - "
			+ "GIVEN home url /home "
			+ "WHEN Requested GET /home page with authentication"
			+ "THEN returns expected Http Response : 200 OK")
		@WithMockUser("testemail1@email.com")
		@Test
		public void testHomeUrlWithLoginStatusOK() throws Exception {

			mockMvc.perform(get("/home"))
			 .andExpect(status().isOk());
			
		}
	    
		// ********************************************************************		

		@DisplayName("HOME Url request check Attributes Exists - "
			+ "GIVEN home url /home "
			+ "WHEN Requested GET /home page & check for attributes"
			+ "THEN returns expected reponse on attibutes exists")
		@WithMockUser("testemail1@email.com")
		@Test
		public void testHomeAttributesExists() throws Exception {
	        mockMvc.perform(get("/home"))
	        .andExpect(status().isOk())
	        .andExpect(model().attributeExists(
	        		"currentPage",
	        		"firstName",
	        		"wallet",
	                "transfers",
	        		"transactions",
	        		"contacts"));
		}

		// ********************************************************************
		
		
		
		
		
    
    
    
}
