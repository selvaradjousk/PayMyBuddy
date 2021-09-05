package com.paymybuddy.webapp.unitTest.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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

		@Test
		public void testHomeUrlWithoutAuthentication() throws Exception {

			mockMvc.perform(get("/home"))
			.andExpect(redirectedUrl("http://localhost/login"));
		}

		
		
    
    
    
}
