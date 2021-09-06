package com.paymybuddy.webapp.unitTest.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.paymybuddy.webapp.controller.ContactController;
import com.paymybuddy.webapp.service.IContactService;

@DisplayName("CONTACT CONTROLLER - H2 DB TEST ")
@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class ContactControllerTest {

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
			+ "THEN returns expected reponse redirect to / login")  
	@Test
	public void testContactUrlWithoutLoginRedirectToLoginUrl() throws Exception {

		mockMvc.perform(get("/contact"))
		.andExpect(status().is(302))
		.andExpect(redirectedUrl("http://localhost/login"));
	}

	// ********************************************************************
	
	
	
	
	
}
