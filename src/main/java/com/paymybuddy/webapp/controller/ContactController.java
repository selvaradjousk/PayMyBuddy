package com.paymybuddy.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.paymybuddy.webapp.service.IContactService;
import com.paymybuddy.webapp.service.IUserService;
import com.paymybuddy.webapp.util.UserMapper;

import lombok.extern.log4j.Log4j2;

/**
 * The Class ContactController.
 */
@Log4j2
@Controller
public class ContactController {



    /** The contact service. */
	@Autowired
    IContactService contactService;

	/** The user service. */
    @Autowired
    IUserService userService;

    /** The user mapper. */
    public UserMapper userMapper = new UserMapper();


    /**
     * Instantiates a new contact controller.
     *
     * @param contactService the contact service
     * @param userService the user service
     * @param userMapper the user mapper
     */
    public ContactController(
    		IContactService contactService,
    		IUserService userService,
    		UserMapper userMapper) {

		this.contactService = contactService;
		this.userService = userService;
		this.userMapper = userMapper;
	}
    
    //******************************************************************
    
    
}
