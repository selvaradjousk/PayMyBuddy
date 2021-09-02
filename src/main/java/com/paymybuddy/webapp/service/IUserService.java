package com.paymybuddy.webapp.service;

import java.util.List;

import com.paymybuddy.webapp.dto.UserDTO;

/**
 * The Interface IUserService.
 */
public interface IUserService {

	/**
	 * Find all users.
	 *
	 * @return the list
	 */
	List<UserDTO> findAllUsers();

	/**
	 * Find user by email.
	 *
	 * @param email the email
	 * @return the user DTO
	 */
	UserDTO findUserByEmail(String email);

	 /**
 	 * Find user by id.
 	 *
 	 * @param id the id
 	 * @return the user DTO
 	 */
 	UserDTO findUserById(Integer id);

	/**
	 * Save user.
	 *
	 * @param userDTO the user DTO
	 * @return the user DTO
	 */
	UserDTO saveUser(UserDTO userDTO);

	/**
	 * User exist by id.
	 *
	 * @param id the id
	 * @return true, if successful
	 */
	boolean userExistById(int id);

	/**
	 * Save new user.
	 *
	 * @param userDTO the user DTO
	 * @param confirmationPass the confirmation pass
	 * @return the user DTO
	 */
	UserDTO saveNewUser(
			UserDTO userDTO,
			String confirmationPass);

}
