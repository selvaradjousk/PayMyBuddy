package com.paymybuddy.webapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.paymybuddy.webapp.dto.ContactDTO;
import com.paymybuddy.webapp.dto.TransactionDTO;
import com.paymybuddy.webapp.dto.TransferDTO;
import com.paymybuddy.webapp.dto.UserDTO;
import com.paymybuddy.webapp.service.IContactService;
import com.paymybuddy.webapp.service.ITransactionService;
import com.paymybuddy.webapp.service.ITransferService;
import com.paymybuddy.webapp.service.IUserService;

import lombok.extern.log4j.Log4j2;

/**
 * The Class HomeController.
 */
@Log4j2
@Controller
public class HomeController {

	// **************************** TODOs LIST ***********************************

	// Method: @GetMapping("/home}") contains listing at home page:
	// --> account balance status
	// --> list of recent buddy operations
	// --> list of recent bank transfer operations (credited & debited)
	// --> menu access for endpoints bankAccount, transfer, buddies connection,
	// profile, logout
	//
	// --> @GetMapping("/home}") for menu access to endpoints
	// --> bankAccount (GET Mapping)
	// --> transfer (GET Mapping)
	// --> buddies connection (GET Mapping)
	// --> profile (GET Mapping)
	// --> logout (GET Mapping)

	// --> REFERENCES & GUIDES
	// --> https://www.baeldung.com/spring-new-requestmapping-shortcuts
	// --> https://www.baeldung.com/spring-security-login
	// -->
	// https://stackoverflow.com/questions/61378595/spring-boot-and-thymeleaf-switching-pages-through-nav-bar-links
	// -->
	// https://mkyong.com/spring-boot/spring-boot-spring-security-thymeleaf-example/
	// -->
	// https://stackoverflow.com/questions/53221381/uncorrect-redirect-after-login-spring-boot
	// -- >https://www.thymeleaf.org/doc/articles/layouts.html
	// -->
	// https://memorynotfound.com/spring-boot-spring-security-thymeleaf-form-login-example/
	// -->
	// https://www.petrikainulainen.net/programming/spring-framework/unit-testing-of-spring-mvc-controllers-normal-controllers/
	// -->
	// https://howtodoinjava.com/spring-boot2/testing/rest-controller-unit-test-example/

	// https://mail.codejava.net/frameworks/spring-boot/user-registration-and-login-tutorial
	// https://www.codejava.net/frameworks/spring-boot/email-verification-example
	// https://www.nexsoftsys.com/articles/spring-boot-controller-unit-testing.html
	// ***


    /** The contact service. */
    @Autowired
    IContactService contactService;

	/** The user service. */
    @Autowired
    IUserService userService;

    /** The transaction service. */
    @Autowired
    ITransactionService transactionService;

    /** The transfer service. */
    @Autowired
    ITransferService transferService;



    /**
     * Instantiates a new home controller.
     *
     * @param contactService the contact service
     * @param userService the user service
     * @param transactionService the transaction service
     * @param transferService the transfer service
     */
    public HomeController(
    		IContactService contactService,
    		IUserService userService,
			ITransactionService transactionService,
			ITransferService transferService) {

		this.contactService = contactService;
		this.userService = userService;
		this.transactionService = transactionService;
		this.transferService = transferService;
	}    

    // ************************************************************************

    /**
     * Home.
     *
     * @param model the model
     * @param page the page
     * @param amount the amount
     * @param contactEmail the contact email
     * @param description the description
     * @param errorMessage the error message
     * @return the string
     */
    @GetMapping({ "/home" })
    public String home(
    		Model model,
    		@RequestParam(name="page", defaultValue = "0")
    		int page, 
    		Double amount,
    		String contactEmail,
    		String description,
    		String errorMessage)
    {
        String emailSession = SecurityContextHolder
        		.getContext()
        		.getAuthentication()
        		.getName();

        UserDTO userLogDTO = userService
        		.findUserByEmail(emailSession);

        String firstName = userLogDTO.getFirstName();

        Double wallet = userLogDTO.getWalletAmount();

        List<ContactDTO> contacts = contactService
        		.findContactByPayer(userLogDTO);

        Page<TransactionDTO> pageTransactions = transactionService
        		.lastThreeTransactions(userLogDTO, PageRequest.of(page,2));

        Page<TransferDTO> pageTransfers = transferService
        		.lastThreeTransfers(userLogDTO, PageRequest.of(page,2));

        Page<TransactionDTO> pageRefunds = transactionService
        		.lastThreeTransactionsBeneficiary(
        				userLogDTO, PageRequest.of(page,2));

        String role = isUserRoleAdminCheck(userLogDTO);

        log.info(" ====> GATHER DATA MODEL FOR HOME PAGE LOADING<==== ");

        addDataToUserModel(
        		model,
        		page,
        		errorMessage,
        		firstName,
        		wallet,
        		contacts,
        		pageTransactions,
        		pageTransfers,
				pageRefunds,
				role);
        
        log.info(" ====> GET REQUEST TO LAUNCH HOME PAGE"
        		+ " - Get /home SUCCESS<==== ");

        return "home";
    }


    // ************************************************************************

	/**
     * Login.
     *
     * @param model the model
     * @return the string
     */
    @GetMapping({ "/login" })
    public String login(Model model)
    {
		log.info(" ====>Loading login requested - Get /login <==== ");
        return "login";
    }

	// ************************************************************************

	/**
	 * Save.
	 *
	 * @param model the model
	 * @return the string
	 */
	@GetMapping({ "/save" })
    public String save(Model model)
    {
        return "login";
    }
    // ************************************************************************

	/**
     * Index.
     *
     * @param model the model
     * @return the string
     */
    @GetMapping({ "/index" })
	public String index(Model model)
    {
        return "login";
    }

	// ************************************************************************
 
	/**
	 * Logout.
	 *
	 * @param request the request
	 * @param response the response
	 * @return the string
	 */
	@GetMapping({ "/logout" })
	public String logout(
			HttpServletRequest request,
			HttpServletResponse response)
    {
    	log.info(" ====> logout requested - Get /logout<==== ");

    	Authentication auth = SecurityContextHolder
        		.getContext().getAuthentication();

    	if (auth != null){
            new SecurityContextLogoutHandler()
            .logout(request, response, auth);
        }
        log.info(" ====> logout request - Get /logout -> SUCCESS<==== ");
        return "redirect:/login?logout";
    }


	// ************************************************************************


	/**
	 * Adds the data to user model.
	 *
	 * @param model the model
	 * @param page the page
	 * @param errorMessage the error message
	 * @param firstName the first name
	 * @param wallet the wallet
	 * @param contacts the contacts
	 * @param pageTransactions the page transactions
	 * @param pageTransfers the page transfers
	 * @param pageRefunds the page refunds
	 * @param role the role
	 * @return the string
	 */
	private String addDataToUserModel(
			Model model,
			int page,
			String errorMessage,
			String firstName,
			Double wallet,
			List<ContactDTO> contacts,
			Page<TransactionDTO> pageTransactions,
			Page<TransferDTO> pageTransfers,
			Page<TransactionDTO> pageRefunds,
			String role) {

		model.addAttribute("admin", role);
        model.addAttribute("contacts", contacts);
        model.addAttribute("transactions", pageTransactions);
        model.addAttribute("transfers", pageTransfers);
        model.addAttribute("refunds", pageRefunds);
        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("firstName", firstName);
        model.addAttribute("wallet", wallet);
        model.addAttribute("currentPage", page);

        return "home";
	}

    // ************************************************************************

	/**
	 * Checks if is user role admin check.
	 *
	 * @param userLogDTO the user log DTO
	 * @return the string
	 */
	private String isUserRoleAdminCheck(UserDTO userLogDTO) {
		String role = null;
        String authorisation = userLogDTO.getRoles();

        if ( authorisation.equals("ROLE_ADMIN") ) {
            role = "admin";
        }
		return role;
	}

	//****************************************************************** 


}
