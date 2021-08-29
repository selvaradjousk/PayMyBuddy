package com.paymybuddy.webapp.unitTest.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.paymybuddy.webapp.controller.HomeController;

//@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = PayMyBuddyApplication.class)
@DisplayName("HomeController TEST IT")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class HomeControllerSpringBTest {

	@LocalServerPort
	private int localPort;

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Autowired
	private HomeController homeController;

	@Test
	public void testApplicationLload_SmokeTesting() {
		assertNotNull(homeController, "HomeController is not loaded");
	}

	@Test
	public void testLoginShouldReturnMessage_HTTPRequestTest() {
		assertThat(testRestTemplate.getForObject("http://localhost:" + localPort + "/login", String.class)
				.contains("login"));
	}

	
	
	
}
