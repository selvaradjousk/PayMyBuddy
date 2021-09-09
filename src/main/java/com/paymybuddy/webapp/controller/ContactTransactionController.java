package com.paymybuddy.webapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.paymybuddy.webapp.dto.ContactDTO;
import com.paymybuddy.webapp.dto.TransactionDTO;
import com.paymybuddy.webapp.dto.UserDTO;
import com.paymybuddy.webapp.exception.DataNotFoundException;
import com.paymybuddy.webapp.model.User;
import com.paymybuddy.webapp.service.IContactService;
import com.paymybuddy.webapp.service.ITransactionService;
import com.paymybuddy.webapp.service.IUserService;
import com.paymybuddy.webapp.util.UserMapper;

import lombok.extern.log4j.Log4j2;

/**
 * The Class TransactionController.
 * @author Senthil
 */
@Log4j2
@Controller
public class ContactTransactionController {



    /** The contact service. */
    @Autowired
    private IContactService contactService;


	/** The transaction service. */
    @Autowired
    private ITransactionService transactionService;

    /** The user service. */
    @Autowired
    private IUserService userService;

    /** The user mapper. */
    private UserMapper userMapper = new UserMapper();

    /** The Constant NUMBER_OF_ELEMENTS_PER_PAGE_FOUR. */
    private static final int NUMBER_OF_ELEMENTS_PER_PAGE_FOUR = 4;

    /** The Constant NUMBER_OF_ELEMENTS_PER_PAGE_TWO. */
    private static final int NUMBER_OF_ELEMENTS_PER_PAGE_TWO = 2;

    /**
     * Instantiates a new contact transaction controller.
     *
     * @param contactServicee the contact servicee
     * @param transactionServicee the transaction servicee
     * @param userServicee the user servicee
     * @param userMapperr the user mapperr
     */
    public ContactTransactionController(
    		final IContactService contactServicee,
    		final ITransactionService transactionServicee,
    		final IUserService userServicee,
    		final UserMapper userMapperr) {

		this.contactService = contactServicee;
		this.transactionService = transactionServicee;
		this.userService = userServicee;
		this.userMapper = userMapperr;
	}

    // ************************************************************************

    /**
     * Transaction.
     *
     * @param model the model
     * @param page the page
     * @param errorMessage the error message
     * @param amount the amount
     * @param contactEmail the contact email
     * @param description the description
     * @return the string
     */
    @GetMapping({"/transaction"})
    public String transaction(
    		final Model model,
    		@RequestParam(name = "page", defaultValue = "0")
    		final int page,
            @RequestParam(name = "errorMessage", defaultValue = "")
    		final String errorMessage,
            @RequestParam(name = "amount", defaultValue = "")
    		final Double amount,
            @RequestParam(name = "contactEmail", defaultValue = "")
    		final String contactEmail,
            @RequestParam(name = "description", defaultValue = "")
    		final String description) {

    	log.info(" ====>Loading transaction page requested"
    			+ " - Get /transaction<==== ");

            String emailSession = SecurityContextHolder
            		.getContext()
            		.getAuthentication()
            		.getName();

            log.info(" ====>emailSession <==== " + emailSession);

            UserDTO userLog = userService
            		.findUserByEmail(emailSession);

            log.info(" ====>userLog <==== " + userLog);

            List<ContactDTO> contacts = contactService
            		.findContactByPayer(userLog);

            log.info(" ====>contacts <==== " + contacts.toString());

            Page<TransactionDTO> pageTransactions = transactionService
            		.findAllTransactionByPayer(
            				userLog, PageRequest.of(
            						page,
            						NUMBER_OF_ELEMENTS_PER_PAGE_FOUR));

            Page<TransactionDTO> pageRefunds = transactionService
            		.lastThreeTransactionsBeneficiary(
            				userLog, PageRequest.of(
            						page,
            						NUMBER_OF_ELEMENTS_PER_PAGE_TWO));

            String role = isUserRoleAdminCheck(userLog);

            addDataToTrasactionModel(
            		model,
            		page,
            		errorMessage,
            		amount,
            		contactEmail,
            		description,
            		contacts,
					pageTransactions,
					pageRefunds,
					role);

            log.info(" ====>model <==== " + model.toString());

            return "transaction";
    }

    // ************************************************************************

    /**
     * Adds the transaction.
     *
     * @param model the model
     * @param page the page
     * @param amount the amount
     * @param contactEmail the contact email
     * @param description the description
     * @param errorMessage the error message
     * @return the string
     */
    @PostMapping(value = { "/transaction" })
    public String addTransaction(
    		final Model model,
    		@RequestParam(name = "page", defaultValue = "0")
    		final int page,
    		final Double amount,
    		final String contactEmail,
    		final String description,
    		String errorMessage) {

    	log.info(" ====>POST transaction requested - POST /transaction<==== ");

    	String emailSession = SecurityContextHolder.getContext()
    			.getAuthentication().getName();

        UserDTO userLog = fetchUserByEmail(emailSession);

        if (contactEmail.equals("")) {
            errorMessage = "You must choose an email! ";
        } else {

            log.info("  ====> Gather info on Payer & Beneficiary");

            UserDTO beneficiaryDTO = userService
            		.findUserByEmail(contactEmail);

            User beneficiary = userMapper.toUserDO(beneficiaryDTO);

            User payer = userMapper.toUserDO(userLog);


            transactionService.doSaveNewTransaction(
            		page,
            		amount,
            		contactEmail,
            		description,
            		beneficiary,
            		payer);

            errorMessage = "Transaction saved";
        }
            return "redirect:/transaction?page=" + page
                    + "&errorMessage=" + errorMessage
                    + "&contactEmail=" + contactEmail
                    + "&amount=" + amount
                    + "&description=" + description;

    }

    // ************************************************************************


    /**
     * Delete.
     *
     * @param id the id
     * @param page the page
     * @return the string
     */
    @GetMapping({"/deleteTransaction"})
    public String delete(final Integer id, final int page) {

    	log.info(" ====>Loading  '/'deleteTransaction"
    			+ " transactionID: " + id + " <==== ");

    	transactionService.deleteById(id);

        return "redirect:/transaction?page=" + page;
    }

    // ************************************************************************

	/**
     * Adds the data to transaction model.
     *
     * @param model the model
     * @param page the page
     * @param errorMessage the error message
     * @param amount the amount
     * @param contactEmail the contact email
     * @param description the description
     * @param contacts the contacts
     * @param pageTransactions the page transactions
     * @param pageRefunds the page refunds
     * @param role the role
     * @return the string
     */
	private String addDataToTrasactionModel(
			final Model model,
			final int page,
			final String errorMessage,
			final Double amount,
			final String contactEmail,
			final String description,
			final List<ContactDTO> contacts,
			final Page<TransactionDTO> pageTransactions,
			final Page<TransactionDTO> pageRefunds,
			final String role) {

		model.addAttribute("admin", role);
		model.addAttribute("contacts", contacts);
		model.addAttribute("transactions", pageTransactions
				.getContent());
		model.addAttribute("refunds", pageRefunds);
		model.addAttribute("pages", new int[pageTransactions
		                                    .getTotalPages()]);
		model.addAttribute("errorMessage", errorMessage);
		model.addAttribute("currentPage", page);
		model.addAttribute("description", description);
		model.addAttribute("contact", contactEmail);
		model.addAttribute("amount", amount);

        return "transaction";
	}


    // ************************************************************************

    /**
	 * Fetch user by email.
	 *
	 * @param emailSession the email session
	 * @return the user DTO
	 */
	private UserDTO fetchUserByEmail(final String emailSession) {

		UserDTO userLog = new UserDTO();

		log.info("  ====> user log initiated instance" + userLog);

        try {
            userLog = userService.findUserByEmail(emailSession);
            } catch (Exception e) {
            throw new DataNotFoundException("Problem with the database");
            }

    	log.info("  ====> user log" + userLog);

        return userLog;
	}

    // ***********************************************************************

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
