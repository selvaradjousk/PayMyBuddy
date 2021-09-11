package com.paymybuddy.webapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
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
 * @author Senthil
 */
@Log4j2
@Controller
public class HomeController {

    /** The contact service. */
    @Autowired
    private IContactService contactService;

	/** The user service. */
    @Autowired
    private IUserService userService;

    /** The transaction service. */
    @Autowired
    private ITransactionService transactionService;

    /** The transfer service. */
    @Autowired
    private ITransferService transferService;

    /** The Constant NUMBER_OF_ELEMENTS_PER_PAGE_TWO. */
    private static final int NUMBER_OF_ELEMENTS_PER_PAGE_TWO = 2;

    /**
     * Instantiates a new home controller.
     *
     * @param contactServicee the contact servicee
     * @param userServicee the user servicee
     * @param transactionServicee the transaction servicee
     * @param transferServicee the transfer servicee
     */
    public HomeController(
    		final IContactService contactServicee,
    		final IUserService userServicee,
    		final ITransactionService transactionServicee,
    		final ITransferService transferServicee) {

		this.contactService = contactServicee;
		this.userService = userServicee;
		this.transactionService = transactionServicee;
		this.transferService = transferServicee;
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
    		final Model model,
    		@RequestParam(name = "page", defaultValue = "0")
    		final int page,
    		final Double amount,
    		final String contactEmail,
    		final String description,
    		final String errorMessage) {

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
        		.lastThreeTransactions(userLogDTO, PageRequest.of(
        				page,
        				NUMBER_OF_ELEMENTS_PER_PAGE_TWO));

        Page<TransferDTO> pageTransfers = transferService
        		.lastThreeTransfers(userLogDTO, PageRequest.of(
        				page,
        				NUMBER_OF_ELEMENTS_PER_PAGE_TWO));

        Page<TransactionDTO> pageRefunds = transactionService
        		.lastThreeTransactionsBeneficiary(
        				userLogDTO, PageRequest.of(
        						page,
        						NUMBER_OF_ELEMENTS_PER_PAGE_TWO));

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


    // **********************************************************************

	/**
     * Login.
     *
     * @param model the model
     * @return the string
     */
    @GetMapping({ "/login" })
    public String login(final Model model) {
		log.info(" ====>Loading login requested - Get /login <==== ");

		// this section prevents user logged in to return to login page
        Authentication authentication = SecurityContextHolder
        		.getContext().getAuthentication();
        if (authentication == null
        		|| authentication instanceof AnonymousAuthenticationToken) {

        return "login";
        }
        return "redirect:/home";
    }

	// *********************************************************************

	/**
	 * Save.
	 *
	 * @param model the model
	 * @return the string
	 */
	@GetMapping({ "/save" })
    public String save(final Model model) {
        return "login";
    }
    // **********************************************************************

	/**
     * Index.
     *
     * @param model the model
     * @return the string
     */
    @GetMapping({ "/index" })
	public String index(final Model model) {
        return "login";
    }

	// ********************************************************************

	/**
	 * Logout.
	 *
	 * @param request the request
	 * @param response the response
	 * @return the string
	 */
	@GetMapping({ "/logout" })
	public String logout(
			final HttpServletRequest request,
			final HttpServletResponse response) {

		log.info(" ====> logout requested - Get /logout<==== ");

    	Authentication auth = SecurityContextHolder
        		.getContext().getAuthentication();

    	if (auth != null) {
            new SecurityContextLogoutHandler()
            .logout(request, response, auth);
        }
        log.info(" ====> logout request"
        		+ " - Get /logout -> SUCCESS<==== ");

        return "redirect:/login?logout";
    }


	// *******************************************************************

	/**
	 * Adds the data to user model.
	 *
	 * @param model the model
	 * @param page the page
	 * @param errorMessage the error message
	 * @param firstName the first name
	 * @param walletAmount the wallet
	 * @param contacts the contacts
	 * @param pageTransactions the page transactions
	 * @param pageTransfers the page transfers
	 * @param pageRefunds the page refunds
	 * @param role the role
	 * @return the string
	 */
	private String addDataToUserModel(
			final Model model,
			final int page,
			final String errorMessage,
			final String firstName,
			final Double walletAmount,
			final List<ContactDTO> contacts,
			final Page<TransactionDTO> pageTransactions,
			final Page<TransferDTO> pageTransfers,
			final Page<TransactionDTO> pageRefunds,
			final String role) {

		model.addAttribute("admin", role);
        model.addAttribute("contacts", contacts);
        model.addAttribute("transactions", pageTransactions);
        model.addAttribute("transfers", pageTransfers);
        model.addAttribute("refunds", pageRefunds);
        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("firstName", firstName);
        model.addAttribute("wallet", walletAmount);
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
	private String isUserRoleAdminCheck(final UserDTO userLogDTO) {
		String role = null;
        String authorisation = userLogDTO.getRoles();

        if (authorisation.equals("ROLE_ADMIN")) {
            role = "admin";
        }
		return role;
	}

	//******************************************************************

}
