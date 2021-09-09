package com.paymybuddy.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.paymybuddy.webapp.dto.UserDTO;
import com.paymybuddy.webapp.service.IUserService;

import lombok.extern.log4j.Log4j2;

/**
 * The Class ProfileController.
 * @author Senthil
 */
@Log4j2
@Controller
public class ProfileController {

    /** The user service. */
	@Autowired
    private IUserService userService;

    /**
     * Instantiates a new profile controller.
     *
     * @param userServicee the user servicee
     */
    public ProfileController(final IUserService userServicee) {
		this.userService = userServicee;
	}

	// ********************************************************************

    /**
	 * Login.
	 *
	 * @param model the model
	 * @param errorMessage the error message
	 * @param firstName the first name
	 * @param lastName the last name
	 * @param email the email
	 * @param password the password
	 * @param confirmation the confirmation
	 * @return the string
	 */
	@GetMapping({ "/profile" })
    public String login(
    		final Model model,
    		final String errorMessage,
    		String firstName,
    		String lastName,
    		String email,
    		String password,
    		String confirmation) {

		log.info(" Request - Get /profile ");

    	String emailSession = SecurityContextHolder
    			.getContext()
    			.getAuthentication()
    			.getName();

    	log.info(" Session email by SecurityContextHolder: " + emailSession);

    	UserDTO userLogDTO = userService
    			.findUserByEmail(emailSession);

    	log.info(" userService.findUserByEmail: "
    	+ userLogDTO.getUserName());

    	firstName = userLogDTO.getFirstName();
        lastName = userLogDTO.getUserName();
        email = userLogDTO.getEmail();
        password = userLogDTO.getPassword();
        confirmation = password;

    	log.info(" fetch userInfo from userLogDTO "
    	+ " firstname" + userLogDTO.getFirstName()
    	+ " username" + userLogDTO.getUserName()
    	+ " email" + userLogDTO.getEmail()
    	+ " password" + userLogDTO.getPassword()
    	+ " confirmation password" + userLogDTO.getPassword());

        String role = isUserRoleAdminCheck(userLogDTO);

        log.info(" ====> GATHER DATA MODEL FOR PROFILE PAGE LOADING<==== ");

        addDataToUserModel(
        		model,
        		errorMessage,
        		firstName,
        		lastName,
        		email,
        		password,
        		confirmation,
        		role);

        log.info(" ====> GET REQUEST TO LAUNCH PROFILE PAGE"
        		+ " - Get /profile SUCCESS<==== ");

        return "profile";
    }
    // ************************************************************************

	/**
     * Adds the data to user model.
     *
     * @param model the model
     * @param errorMessage the error message
     * @param firstName the first name
     * @param lastName the last name
     * @param email the email
     * @param password the password
     * @param confirmation the confirmation
     * @param role the role
     * @return the string
     */
    private String addDataToUserModel(
    		final Model model,
    		final String errorMessage,
    		final String firstName,
    		final String lastName,
    		final String email,
    		final String password,
    		final String confirmation,
    		final String role) {

    	model.addAttribute("firstName", firstName);
        model.addAttribute("lastName", lastName);
        model.addAttribute("email", email);
        model.addAttribute("password", password);
        model.addAttribute("confirmation", confirmation);
        model.addAttribute("admin", role);
        model.addAttribute("errorMessage", errorMessage);

        return "profile";
	}

    // ************************************************************

	/**
     * Checks if is user role admin check.
     *
     * @param userLogDTO the user log DTO
     * @return the string
     */
    private String isUserRoleAdminCheck(final UserDTO userLogDTO) {
		String role = null;
        String authorisation = userLogDTO
        		.getRoles();
        if (authorisation.equals("ROLE_ADMIN")) {
            role = "admin";
        }
		return role;
	}

	// ******************************************************************
}
