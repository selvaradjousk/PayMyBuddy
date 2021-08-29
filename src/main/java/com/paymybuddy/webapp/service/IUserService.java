package com.paymybuddy.webapp.service;

import java.util.List;

import com.paymybuddy.webapp.model.User;

public interface IUserService {

	List<User> findAllUsers();

	User findUserByEmail(String email);

}
