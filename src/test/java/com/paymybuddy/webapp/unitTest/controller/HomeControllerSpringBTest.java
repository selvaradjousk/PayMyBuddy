package com.paymybuddy.webapp.unitTest.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

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
	
	LogoutFilter filter;

	@Test
	public void testApplicationLload_SmokeTesting() {
		assertNotNull(homeController, "HomeController is not loaded");
	}

	@Test
	public void testLoginShouldReturnMessage_HTTPRequestTest() {
		assertThat(testRestTemplate.getForObject("http://localhost:" + localPort + "/login", String.class)
				.contains("login"));
	}

	
	@Test
    public void testLogoutPage() throws Exception
    {
        MockHttpServletRequestBuilder requestBuilder = get("/logout");
        MockMvcBuilders.standaloneSetup(this.homeController)
                .build()
                .perform(requestBuilder)
                .andExpect(status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(view().name("redirect:/login?logout"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/login?logout"));
    }
	
	@Test
	public void testRequiresLogoutUrlWorksWithPathParams() {
		
		this.filter = new LogoutFilter("/success", new SecurityContextLogoutHandler());
		
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		request.setRequestURI("/context/logout;someparam=blah?param=blah");
		request.setServletPath("/logout;someparam=blah");
		request.setQueryString("otherparam=blah");
		DefaultHttpFirewall fw = new DefaultHttpFirewall();
		assertEquals("redirect:/login?logout", homeController.logout(fw.getFirewalledRequest(request), response));
	}

	@Test
	public void testRequiresLogoutUrlWorksWithQueryParams() {
		
		this.filter = new LogoutFilter("/success", new SecurityContextLogoutHandler());
		
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setContextPath("/context");
		MockHttpServletResponse response = new MockHttpServletResponse();
		request.setServletPath("/logout");
		request.setRequestURI("/context/logout?param=blah");
		request.setQueryString("otherparam=blah");
		assertEquals("redirect:/login?logout", homeController.logout(request, response));
	}
	
}
