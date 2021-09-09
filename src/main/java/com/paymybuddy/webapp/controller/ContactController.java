package com.paymybuddy.webapp.controller;

import java.time.LocalDate;
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
import com.paymybuddy.webapp.model.User;
import com.paymybuddy.webapp.service.IContactService;
import com.paymybuddy.webapp.service.IUserService;
import com.paymybuddy.webapp.util.UserMapper;

import lombok.extern.log4j.Log4j2;

/**
 * The Class ContactController.
 * @author Senthil
 */
@Log4j2
@Controller
public class ContactController {

    /** The contact service. */
	@Autowired
    private IContactService contactService;

	/** The user service. */
    @Autowired
    private IUserService userService;

    /** The user mapper. */
    private UserMapper userMapper = new UserMapper();

    /** The Constant NUMBER_OF_ELEMENTS_PER_PAGE. */
    private static final int NUMBER_OF_ELEMENTS_PER_PAGE = 3;

    /**
     * Instantiates a new contact controller.
     *
     * @param contactServicee the contact servicee
     * @param userServicee the user servicee
     * @param userMapperr the user mapperr
     */
    public ContactController(
    		final IContactService contactServicee,
    		final IUserService userServicee,
    		final UserMapper userMapperr) {

		this.contactService = contactServicee;
		this.userService = userServicee;
		this.userMapper = userMapperr;
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
    		final Model model,
    		@RequestParam(name = "page", defaultValue = "0")
    		final int page,
            @RequestParam(name = "motCle", defaultValue = "")
    		final String keyword,
            @RequestParam(name = "errorMessage", defaultValue = "")
    		final String errorMessage) {

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
        				"%" + keyword + "%",
        				PageRequest.of(
        						page,
        						NUMBER_OF_ELEMENTS_PER_PAGE));

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
     * Adds the.
     *
     * @param idContact the id contact
     * @return the string
     */
    @GetMapping("/addContact")
    public String add(final Integer idContact) {

        log.info(" ====> ADD CONTACT FOR: "
        + idContact + " requested <==== ");

        UserDTO userLog = fetchUserLog();
        log.info(" ====> userLog " + userLog
        		+ "  <==== ");

        UserDTO uContact = userService
        		.findUserById(idContact);

        log.info(" ====> uContact " + uContact + "  <==== ");

        LocalDate date = LocalDate.now();

        User payer = userMapper.toUserDO(userLog);

        ContactDTO newContactDTO = new ContactDTO(
        		date,
        		payer,
        		userMapper.toUserDO(uContact));

        log.info(" ====> newContactDTO "
        + newContactDTO + "  <==== ");

        contactService.addContact(newContactDTO);

        log.info(" ====> ADD CONTACT FOR: "
        + idContact + " SUCCESS <==== ");

        return "redirect:/contact";
    }


    //******************************************************************
    /**
     * Delete.
     *
     * @param idContact the id contact
     * @return the string
     */
    @GetMapping("/deleteContact")
    public String delete(final Integer idContact) {

    	log.info(" ====> DELETE CONTACT FOR: "
    	+ idContact + " requested <==== ");

        contactService.deleteById(idContact);

        log.info(" ====> DELETE CONTACT FOR: "
        + idContact + " SUCCESS <==== ");

        return "redirect:/contact";
    }

    // ************************************************************************

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
    		final Model model,
    		final int page,
    		final String keyword,
    		final String errorMessage,
			final List<ContactDTO> pageBuddies,
			final Page<UserDTO> pageUsersNotBuddyYet,
			final String role) {

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
    private String isUserRoleAdminCheck(final UserDTO userLog) {

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
