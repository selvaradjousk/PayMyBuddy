package com.paymybuddy.webapp.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.paymybuddy.webapp.dto.UserDTO;
import com.paymybuddy.webapp.model.User;
import com.paymybuddy.webapp.service.IUserService;

@RestController
public class UserController {
	
	private final IUserService userService;
	
	
	UserController(IUserService userService) {
	    this.userService = userService;
	  }
	
	  // http://localhost:9090/users
	  @GetMapping("/users")
	  List<UserDTO> all() {
	    return userService.findAllUsers();
	  }
	  
	  
	  // http://localhost:9090/userByEmail?email=testemail1@email.com
	  @GetMapping("/userByEmail")
	  public UserDTO getUser(
				@RequestParam("email")
				@Valid @NotBlank final String email) {

	    return userService.findUserByEmail(email);
	  }
	
	// http://localhost:9090/userById?id=1
	  @GetMapping("/userById")
	  public UserDTO getUser(
				@RequestParam("id")
				@Valid @NotBlank final Integer id) {

	    return userService.findUserById(id);
	  }

	  
}
