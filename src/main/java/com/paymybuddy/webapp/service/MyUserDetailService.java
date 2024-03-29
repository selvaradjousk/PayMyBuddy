package com.paymybuddy.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.paymybuddy.webapp.config.MyUserDetails;
import com.paymybuddy.webapp.repository.UserRepository;

import lombok.extern.log4j.Log4j2;

/**
 * The Class MyUserDetailService custom implementation of UserDetailsService
 * interface.
 * 
 * @author Senthil
 */
@Log4j2
@Service
public class MyUserDetailService implements UserDetailsService {

	/** The user repository. */
	@Autowired
	private final UserRepository userRepository;

	/**
	 * Constructor of class MyUserDetailsService Instantiates a new
	 *  my user detail service.
	 *
	 * @param userRepositoryy the user repositoryy
	 */
	public MyUserDetailService(final UserRepository userRepositoryy) {
		this.userRepository = userRepositoryy;
	}

    // *******************************************************************
	/**
	 * Loads user detail by username.
	 *
	 * @param email the user email
	 * @return the user details
	 * @throws UsernameNotFoundException the username not found exception
	 * @throws DataAccessException the data access exception
	 */
	@Override
	public UserDetails loadUserByUsername(final String email)
			throws UsernameNotFoundException, DataAccessException {

		// https://stackoverflow.com/questions/59352914
		// /custom-spring-boot-login-form

		log.debug("Fetching user - MyUserDetailsService.loadUserByUsername");

//		Optional<User> user = userRepository.findByEmail(email);
//		log.info("Transforming " + user + " into UserDetails object");
//		user.orElseThrow(() -> new UsernameNotFoundException(
//				"Username: " + email + " not found"));
//		  log.info("About to return " + user.map(MyUserDetails::new).get());
//		return user.map(MyUserDetails::new).get();

		// short form of the above codes
		return userRepository.findByEmail(email)
			    .map(MyUserDetails::new)
			    .orElseThrow(() -> new UsernameNotFoundException(
			    		"Username: " + email + " not found"));
	}
    // *******************************************************************
	// try for testing https://stackoverflow.com/questions/38330597
	// /inject-authenticationprincipal-when-unit-testing
	// -a-spring-rest-controller
    // *******************************************************************
}
