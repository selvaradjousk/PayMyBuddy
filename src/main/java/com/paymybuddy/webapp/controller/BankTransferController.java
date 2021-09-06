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

import com.paymybuddy.webapp.dto.BankAccountDTO;
import com.paymybuddy.webapp.dto.TransferDTO;
import com.paymybuddy.webapp.dto.UserDTO;
import com.paymybuddy.webapp.model.User;
import com.paymybuddy.webapp.service.IBankAccountService;
import com.paymybuddy.webapp.service.ITransferService;
import com.paymybuddy.webapp.service.IUserService;
import com.paymybuddy.webapp.util.UserMapper;

import lombok.extern.log4j.Log4j2;

/**
 * The Class TransferController.
 */
@Log4j2
@Controller
public class BankTransferController {


    /** The user service. */
    @Autowired
    IUserService userService;

	/** The bank account service. */
    @Autowired
    IBankAccountService bankAccountService;

    /** The transfer service. */
    @Autowired
    ITransferService transferService;

    /** The user mapper. */
    public UserMapper userMapper = new UserMapper();


    /**
     * Instantiates a new bank transfer controller.
     *
     * @param userService the user service
     * @param bankAccountService the bank account service
     * @param transferService the transfer service
     * @param userMapper the user mapper
     */
    public BankTransferController(
    		IUserService userService,
    		IBankAccountService bankAccountService,
			ITransferService transferService,
			UserMapper userMapper) {
		this.userService = userService;
		this.bankAccountService = bankAccountService;
		this.transferService = transferService;
		this.userMapper = userMapper;
	}

    
    // ************************************************************************

    /**
     * Transfer.
     *
     * @param model the model
     * @param page the page
     * @param mc the mc
     * @param errorMessage the error message
     * @return the string
     */

//    @RequestMapping(value = { "/transfer" }, method = RequestMethod.GET)
    @GetMapping(value = { "/transfer" })
    public String transfer(
    		Model model,
            @RequestParam(name="page", defaultValue = "0")
    		int page,
            @RequestParam(name="motCle", defaultValue = "")
    		String mc,
            @RequestParam(name="errorMessage", defaultValue = "")
    		String errorMessage)
    {
    	 log.info(" ====> Loading GET request for /transfer");

        // fetch list of user logs
        String emailSession = SecurityContextHolder
        		.getContext()
        		.getAuthentication()
        		.getName();

        UserDTO userLogDTO = userService
        		.findUserByEmail(emailSession);
  
        //-- fetch the list of bankaccounts
        List<BankAccountDTO> bankAccounts = bankAccountService
        		.findBankAccountByUser(userLogDTO);

        // fetch by pages list of all users
        Page<TransferDTO> pageTransfers = transferService
        		.findAllTransferByUser(userLogDTO, PageRequest.of(page,2));

        // check and assign role admin for operation
        String role = isUserRoleAdminCheck(userLogDTO);

        // update the data to the model for Transfer page
        addDataToTransferModel(
        		model,
        		page,
        		errorMessage,
        		bankAccounts,
        		pageTransfers,
        		role);

        return "transfer";
    }

 


    // ************************************************************************

    /**
     * Delete.
     *
     * @param id the id
     * @return the string
     */
    @GetMapping("/deleteAccount")
    public String delete(Integer id){

    	log.info(" ====> Loading GET request for /deleteAccount");

    	bankAccountService.deleteBankAccount(id);

    	return"redirect:/transfer";
    }

    // ************************************************************************

    /**
     * Adds the account.
     *
     * @param model the model
     * @param page the page
     * @param rib the rib
     * @return the string
     */
    @PostMapping(value = { "/addBankAccount" })
    public String addAccount(
    		Model model,
    		@RequestParam(name="page", defaultValue = "0") int page,
    		String rib){

        log.info(" ====> Loading POST request /addBankAccount rib: " + rib);

        // fetch list of user logs
        String emailSession = SecurityContextHolder
        		.getContext()
        		.getAuthentication()
        		.getName();

        UserDTO userLog = userService
        		.findUserByEmail(emailSession);

        // add a new bank account
        bankAccountService.addBankAccount( rib ,userLog);

        return"redirect:/transfer";
    }

    // ************************************************************************
 

    /**
     * Adds the transfer.
     *
     * @param model the model
     * @param page the page
     * @param rib the rib
     * @param amount the amount
     * @param type the type
     * @param errorMessage the error message
     * @return the string
     */
    @PostMapping(value = { "/addTransfer" })
    public String addTransfer(
    		Model model,
    		@RequestParam(name="page", defaultValue = "0") int page,
            String rib,
            double amount,
            String type,
            String errorMessage){

        log.info(" ====> Loading POST request  /addBTransfer : "
        + rib + " - " + amount + " - " + type);

        //check null for bank account rib
        checkForBankAccountRibNotNull(page, rib);

        // fetch list of user logs
        String emailSession = SecurityContextHolder
        		.getContext()
        		.getAuthentication()
        		.getName();

        UserDTO userLog = userService.findUserByEmail(emailSession);

        User user = userMapper.toUserDO(userLog);

        transferService.doAddTransfer(page, rib, amount, type, user);

        errorMessage = "Transfer saved";

        return"redirect:/transfer?page="+page+
                "&errorMessage="+errorMessage;
    }


    // ************************************************************************  

    /**
     * Adds the data to transfer model.
     *
     * @param model the model
     * @param page the page
     * @param errorMessage the error message
     * @param bankAccounts the bank accounts
     * @param pageTransfers the page transfers
     * @param role the role
     */
    private void addDataToTransferModel(
    		Model model,
    		int page,
    		String errorMessage,
    		List<BankAccountDTO> bankAccounts,
			Page<TransferDTO> pageTransfers,
			String role) {

    	model.addAttribute("admin", role);
        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("currentPage", page);
        model.addAttribute("bankAccounts", bankAccounts);
        model.addAttribute("transfers", pageTransfers);
        model.addAttribute("pages", new int[pageTransfers.getTotalPages()]);
	}
    

 // ************************************************************************

	/**
  * Check for bank account rib not null.
  *
  * @param page the page
  * @param rib the rib
  * @return the string
  */
 private String checkForBankAccountRibNotNull(int page, String rib) {
		String errorMessage;
		if(rib==null){
            errorMessage = "You must created an account";
            return"redirect:/transfer?page="+page+
                    "&errorMessage="+errorMessage;

        }
		else {
			errorMessage = "OK";
		}
		return errorMessage;
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
