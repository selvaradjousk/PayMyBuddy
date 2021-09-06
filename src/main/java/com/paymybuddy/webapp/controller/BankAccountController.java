package com.paymybuddy.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paymybuddy.webapp.model.BankAccount;
import com.paymybuddy.webapp.repository.BankAccountRepository;
import com.paymybuddy.webapp.service.IBankAccountService;
import com.paymybuddy.webapp.util.BankAccountMapper;
import com.paymybuddy.webapp.util.UserMapper;

import lombok.extern.log4j.Log4j2;

/**
 * The Class BankAccountController - Creates REST endpoints
 *  for operations on bank account data.
 *  @author Senthil
 */
@Log4j2
@RestController
@RequestMapping("manage")
public class BankAccountController {

	

    /** The bank account repository. */
	@Autowired
    BankAccountRepository bankAccountRepository;

	/** The bank account service. */
	@Autowired
    IBankAccountService bankAccountService;

    /** The user mapper. */
    public UserMapper userMapper= new UserMapper();

    /** The bank account mapper. */
    public BankAccountMapper bankAccountMapper = new BankAccountMapper();

    /**
     * Constructor of class BankAccountController.
     * Instantiates a new bank account controller.
     *
     * @param bankAccountRepository the bank account repository
     * @param bankAccountService the bank account service
     * @param userMapper the user mapper
     * @param bankAccountMapper the bank account mapper
     */
    public BankAccountController(
    		BankAccountRepository bankAccountRepository,
    		IBankAccountService bankAccountService,
			UserMapper userMapper,
			BankAccountMapper bankAccountMapper) {
		this.bankAccountRepository = bankAccountRepository;
		this.bankAccountService = bankAccountService;
		this.userMapper = userMapper;
		this.bankAccountMapper = bankAccountMapper;
	}


    // ************************************************************************


    /**
     * Gets the bank accounts.
     *
     * @return the bank accounts
     */
    @GetMapping("/bankAccounts")
    public Iterable<BankAccount> getbankAccounts() {
    	log.debug("get /bankAccounts - Access");

        // return the list of bank accounts for the user
        return bankAccountRepository.findAll();
    }
    
    // ************************************************************************

    



}
