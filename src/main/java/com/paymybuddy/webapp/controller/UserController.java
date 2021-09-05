package com.paymybuddy.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.paymybuddy.webapp.dto.UserDTO;
import com.paymybuddy.webapp.exception.DataAlreadyExistException;
import com.paymybuddy.webapp.exception.DataNotConformException;
import com.paymybuddy.webapp.service.IUserService;

import lombok.extern.log4j.Log4j2;

/**
 * The Class UserController.
 */
@Log4j2
@Controller
public class UserController {

	/** The user service. */
	@Autowired
    IUserService userService;


    /**
     * Instantiates a new user controller.
     *
     * @param userService the user service
     */
    public UserController(IUserService userService) {
		this.userService = userService;
	}

    // ************************************************************************

    /**
     * Register.
     *
     * @param model the model
     * @param password the password
     * @param confirmation the confirmation
     * @param firstName the first name
     * @param lastName the last name
     * @param email the email
     * @param errorMessage the error message
     * @return the string
     */
    @GetMapping({ "/register" })
    public String register(
    		Model model,
    		String password,
    		String confirmation,
    		String firstName,
    		String lastName,
    		String email,
    		String errorMessage)
    {
		log.info(" ====>Loading login requested - Get /register <==== ");

		model.addAttribute("firstName", firstName);
        model.addAttribute("lastName", lastName);
        model.addAttribute("email", email);
        model.addAttribute("password", password);
        model.addAttribute("confirmation", confirmation);
        model.addAttribute("errorMessage", errorMessage);
        return "register";
    }

    // ************************************************************************

    /**
     * Adds the new user.
     *
     * @param model the model
     * @param firstName the first name
     * @param lastName the last name
     * @param email the email
     * @param password the password
     * @param confirmation the confirmation
     * @param errorMessage the error message
     * @return the string
     */
    @PostMapping(value = { "/register" })
    public String addNewUser(Model model,
    		String firstName, String lastName, String email,
    		String password, String confirmation, String errorMessage){

    	log.info(" Login register new user requested"
				+ " - POST /register for firstname: " + firstName
				+ " lastname: " + lastName
				+" password: "+ password
				+"confirmation password: " + confirmation);

            try {
                UserDTO newUserDTO = new UserDTO(
                		firstName,
                		lastName,
                		email,
                		password);

                userService.saveNewUser(
                		newUserDTO,
                		confirmation);

                return"redirect:/login";
            }
            catch (DataNotConformException | DataAlreadyExistException e){
                errorMessage = e.getMessage();
                return "redirect:/register?" +
                        "&firstName="+firstName+
                        "&lastName="+lastName+
                        "&email="+email+
                        "&password="+password+
                        "&confirmation="+confirmation+
                        "&errorMessage="+errorMessage;
            }

    }
    // ************************************************************************
}
