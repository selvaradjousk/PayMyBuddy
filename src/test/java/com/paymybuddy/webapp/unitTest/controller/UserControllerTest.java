package com.paymybuddy.webapp.unitTest.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.web.context.WebApplicationContext;

import com.paymybuddy.webapp.controller.UserController;
import com.paymybuddy.webapp.repository.UserRepository;
import com.paymybuddy.webapp.service.UserServiceImpl;

@DisplayName("USER CONTROLLER - H2 DB TEST ")
@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext context;

	@Autowired
	UserRepository userRepository;

	@InjectMocks
	UserController userController;

	@Mock
	UserServiceImpl userService;

	// ********************************************************************
	   
	@DisplayName("REGISTER page Url request Response - 200 OK - "
			+ "GIVEN Register url /register "
			+ "WHEN Requested GET /register page"
			+ "THEN returns expected 200 OK http response") 
	@Test
	public void testRegisterAttributesExist() throws Exception {

		mockMvc.perform(get("/register"))
				.andExpect(view().name("register")).
				andDo(MockMvcResultHandlers.print())
				.andExpect(model().hasNoErrors())
				.andExpect(status().isOk());
	}

}
