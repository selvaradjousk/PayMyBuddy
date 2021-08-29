package com.paymybuddy.webapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.webapp.dto.UserDTO;
import com.paymybuddy.webapp.exception.DataNotFoundException;
import com.paymybuddy.webapp.model.User;
import com.paymybuddy.webapp.repository.UserRepository;
import com.paymybuddy.webapp.util.UserMapper;

import lombok.extern.log4j.Log4j2;

// **************************** TODOs LIST ***********************************

// Method: 
// --> getAllUsers(String email) served by userRepository.findAll()
// --> addNewUser(userDTO) 
// 			check for user exist already by userRepository.findUserByEmail(email)
//			add served by userMapper.toUserDTO(user)
// --> check for email validity
// --> check password match on confirm password
// --> input fields validation done in the respective DO with annotations

@Log4j2	
@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepository userRepository;

    public UserMapper userMapper = new UserMapper();


    // *******************************************************************
    /**
     * Find all users.
     *
     * @return the list
     */
	@Override
	public List<UserDTO> findAllUsers() {

		List<User> listOfUsers = userRepository.findAll();

		log.info(" ====> FIND All USER requested <==== ");

		List<UserDTO> listOfUsersDTO = new ArrayList<UserDTO>();

        for (User user: listOfUsers) {
        	listOfUsersDTO.add(userMapper.toUserDTO(user));
        }
        log.info(" ====> FIND All USER Successfull <==== ");
        return listOfUsersDTO;        
	}


	// *******************************************************************
	/**
	 * Find user by email.
	 *
	 * @param email the email
	 * @return the user DTO
	 */
	@Override
	public UserDTO findUserByEmail(String email) {

		log.info(" ====> FIND USER by EMAIL requested <==== ");

		User user = userRepository.findUserByEmail(email);

		log.info(" ====> FIND USER by EMAIL Sucessfull <==== ");

		return userMapper.toUserDTO(user);
	}
	// *******************************************************************


	@Override
	public UserDTO findUserById(Integer id) {

		log.info(" ====> FIND USER by ID requested <==== ");

		User user = userRepository.findUserById(id);

        if (user == null) {
            log.info(" ====> FIND USER by ID - user NOT FOUND<==== ");
            throw new DataNotFoundException(
            		"ERROR: User By ID - NOT FOUND");
        }

        return userMapper.toUserDTO(user);
	}

	// *******************************************************************


}
