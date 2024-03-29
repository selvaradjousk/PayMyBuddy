package com.paymybuddy.webapp.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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


	/**
	 * List user not buddy.
	 *
	 * @param userDTO the user DTO
	 * @param mc the mc
	 * @param pageable the pageable
	 * @return the page
	 */
	Page<UserDTO> listUserNotBuddy(
			UserDTO userDTO,
			String mc,
			Pageable pageable);

//	UserDTO updateUser(UserDTO userDTO);

}
