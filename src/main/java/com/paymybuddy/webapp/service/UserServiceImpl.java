package com.paymybuddy.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.webapp.dto.UserDTO;
import com.paymybuddy.webapp.model.User;
import com.paymybuddy.webapp.repository.UserRepository;
import com.paymybuddy.webapp.util.UserMapper;

// **************************** TODOs LIST ***********************************

// Method: 
// --> getAllUsers(String email) served by userRepository.findAll()
// --> addNewUser(userDTO) 
// 			check for user exist already by userRepository.findUserByEmail(email)
//			add served by userMapper.toUserDTO(user)
// --> check for email validity
// --> check password match on confirm password
// --> input fields validation done in the respective DO with annotations

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepository userRepository;

    public UserMapper userMapper = new UserMapper();
	
	
	@Override
	public List<User> findAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public UserDTO findUserByEmail(String email) {
		User user = userRepository.findUserByEmail(email);
		return userMapper.toUserDTO(user);
	}

}
