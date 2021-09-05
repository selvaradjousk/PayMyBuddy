package com.paymybuddy.webapp.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

//@RestController
public class HomeController {

	// **************************** TODOs LIST ***********************************

	// Method: @GetMapping("/home}") contains listing at home page:
	// --> account balance status
	// --> list of recent buddy operations
	// --> list of recent bank transfer operations (credited & debited)
	// --> menu access for endpoints bankAccount, transfer, buddies connection,
	// profile, logout
	//
	// --> @GetMapping("/home}") for menu access to endpoints
	// --> bankAccount (GET Mapping)
	// --> transfer (GET Mapping)
	// --> buddies connection (GET Mapping)
	// --> profile (GET Mapping)
	// --> logout (GET Mapping)

	// --> REFERENCES & GUIDES
	// --> https://www.baeldung.com/spring-new-requestmapping-shortcuts
	// --> https://www.baeldung.com/spring-security-login
	// -->
	// https://stackoverflow.com/questions/61378595/spring-boot-and-thymeleaf-switching-pages-through-nav-bar-links
	// -->
	// https://mkyong.com/spring-boot/spring-boot-spring-security-thymeleaf-example/
	// -->
	// https://stackoverflow.com/questions/53221381/uncorrect-redirect-after-login-spring-boot
	// -- >https://www.thymeleaf.org/doc/articles/layouts.html
	// -->
	// https://memorynotfound.com/spring-boot-spring-security-thymeleaf-form-login-example/
	// -->
	// https://www.petrikainulainen.net/programming/spring-framework/unit-testing-of-spring-mvc-controllers-normal-controllers/
	// -->
	// https://howtodoinjava.com/spring-boot2/testing/rest-controller-unit-test-example/

	// https://mail.codejava.net/frameworks/spring-boot/user-registration-and-login-tutorial
	// https://www.codejava.net/frameworks/spring-boot/email-verification-example
	// https://www.nexsoftsys.com/articles/spring-boot-controller-unit-testing.html
	// ***

	// ************************************************************************
	@GetMapping("/home")
	public String home(Model model) {
//		model.addAttribute("msg", "SUCCESS");
		return "home";
	}

	// ************************************************************************
	@GetMapping("/login")
	public String login(Model model) {
//		model.addAttribute("msg", "SUCCESS");
		return "login";
	}

	// ************************************************************************
	@GetMapping("/logout")
	public String logout(Model model) {
//		model.addAttribute("msg", "SUCCESS");
		return "redirect:/login?logout";
	}

}
