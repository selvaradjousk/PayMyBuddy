package com.paymybuddy.webapp.service;

import java.util.List;

import com.paymybuddy.webapp.dto.UserDTO;

public interface IUserService {

	/**
	 * Find all users.
	 *
	 * @return the list
	 */
	public List<UserDTO> findAllUsers();

	/**
	 * Find user by email.
	 *
	 * @param email the email
	 * @return the user DTO
	 */
	public UserDTO findUserByEmail(String email);
	
	
	 /**
 	 * Find user by id.
 	 *
 	 * @param id the id
 	 * @return the user DTO
 	 */
 	public UserDTO findUserById(Integer id);

//	public UserDTO saveUser(UserDTO userDTO);
 
 
 	
}
