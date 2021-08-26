package com.paymybuddy.webapp.service;

import java.util.List;

import com.paymybuddy.webapp.model.User;

public interface IUserService {

	List<User> getAllUsers();

	User getUserByEmail(String email);

}
