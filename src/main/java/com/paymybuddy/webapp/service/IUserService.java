package com.paymybuddy.webapp.service;

import java.util.List;

import com.paymybuddy.webapp.dto.UserDTO;
import com.paymybuddy.webapp.model.User;

public interface IUserService {

	List<User> findAllUsers();

	public UserDTO findUserByEmail(String email);

}
