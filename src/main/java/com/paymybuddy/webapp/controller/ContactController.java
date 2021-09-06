package com.paymybuddy.webapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.paymybuddy.webapp.dto.ContactDTO;
import com.paymybuddy.webapp.dto.UserDTO;
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
  

    
    /**
     * Home.
     *
     * @param model the model
     * @param page the page
     * @param keyword the keyword
     * @param errorMessage the error message
     * @return the string
     */
//    @RequestMapping(value = { "/contact" }, method = RequestMethod.GET)
    @GetMapping({ "/contact" })
    public String home(
    		Model model,
    		@RequestParam(name="page", defaultValue = "0")
    		int page,
            @RequestParam(name="motCle", defaultValue = "")
    		String keyword,
            @RequestParam(name="errorMessage", defaultValue = "")
    		String errorMessage)
    {

        //-- Fetch the user log of the buddy
    	log.info(" ====> GET CONTACT for USER requested"
    			+ " - Get /contact<==== ");
        UserDTO userLog = fetchUserLog();
        
        //-- fetch list of buddy
        List<ContactDTO> pageBuddies = contactService
        		.findContactByPayer(userLog);

        Page<UserDTO> pageUsersNotBuddyYet = userService
        		.listUserNotBuddy(
        				userLog,
        				"%"+keyword+"%",
        				PageRequest.of(page, 3));

        String role = isUserRoleAdminCheck(userLog);

        addDataToUserModel(
        		model,
        		page,
        		keyword,
        		errorMessage,
        		pageBuddies,
        		pageUsersNotBuddyYet,
        		role);

        log.info(" ====> GET CONTACT for USER - Get /contact SUCCESS<==== ");
        log.info(" ====> pageUsersNotBuddyYetS<==== "
        + pageUsersNotBuddyYet.getContent());
        log.info(" ====> pageBuddies<==== " + pageBuddies.toString());

        return "contact";
    }

    //******************************************************************


	/**
     * Adds the data to user model.
     *
     * @param model the model
     * @param page the page
     * @param keyword the keyword
     * @param errorMessage the error message
     * @param pageBuddies the page buddies
     * @param pageUsersNotBuddyYet the page users not buddy yet
     * @param role the role
     * @return the string
     */
    private String addDataToUserModel(
    		Model model,
    		int page,
    		String keyword,
    		String errorMessage,
			List<ContactDTO> pageBuddies,
			Page<UserDTO> pageUsersNotBuddyYet,
			String role) {

    	model.addAttribute("admin", role);
        model.addAttribute("contacts", pageBuddies);
        model.addAttribute("notContacts", pageUsersNotBuddyYet
        		.getContent());
        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("currentPage", page);
        model.addAttribute("pages", new int[pageUsersNotBuddyYet
                                            .getTotalPages()]);
        model.addAttribute("motCle", keyword);

        log.info(" ====>  pageUsersNotBuddyYet.getContent()<==== "
        +  pageUsersNotBuddyYet.getContent());

        log.info(" ====> model.pages<==== " + page);
        
        return "contact";
    }

    // ************************************************************************

	/**
     * Checks if is user role admin check.
     *
     * @param userLog the user log
     * @return the string
     */
    private String isUserRoleAdminCheck(UserDTO userLog) {

    	String role = null;
        String authorisation = userLog.getRoles();

        if (authorisation.equals("ROLE_ADMIN")) {
            role = "admin";
        }
		return role;
	}

    // ************************************************************************	
	/**
     * Fetch user log.
     *
     * @return the user DTO
     */
    public UserDTO fetchUserLog() {

    	String emailSession = SecurityContextHolder
    			.getContext()
    			.getAuthentication()
    			.getName();
 
    	UserDTO userLog = userService.findUserByEmail(emailSession);

    	return userLog;
	}
    // ************************************************************************	  



}
