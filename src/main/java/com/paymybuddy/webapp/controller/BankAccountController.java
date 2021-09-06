package com.paymybuddy.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paymybuddy.webapp.dto.BankAccountDTO;
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

    /**
     * Adds the bank account.
     *
     * @param bankAccountDTO the bank account DTO
     * @return the response entity
     */
    @PostMapping(value = "/bankAccount")
    public ResponseEntity<BankAccountDTO> addBankAccount(
    		@Validated @RequestBody BankAccountDTO bankAccountDTO) {

        log.debug("post /bankAccount - BankAccount Id: "
        + bankAccountDTO.getIdBankAccount()
        + ", account owner:" + bankAccountDTO.getUser() + ""
        + ", RIB:" + bankAccountDTO.getRib());

        // Bank account DTO data Mapped to DO
        BankAccount bankAccount = bankAccountMapper
        		.toBankAccountDO(bankAccountDTO);

        // Bank Account DO saved through repository interface
        BankAccount newBankAccount = bankAccountRepository
        		.save(bankAccount);

        log.info("New BankAccount Rib saved : "
        + newBankAccount.getRib()
        + "POST request SUCCESS");

        return new ResponseEntity<BankAccountDTO>(bankAccountMapper
        		.toBankAccountDTO(newBankAccount), HttpStatus.CREATED);
    }
    // ************************************************************************
 

    /**
     * Modify bank account.
     *
     * @param bankAccountDTO the bank account DTO
     * @return the response entity
     */
    @PutMapping(value= "/bankAccount")
    public ResponseEntity<BankAccountDTO> modifyBankAccount(
    		@Validated @RequestBody BankAccountDTO bankAccountDTO) {

    	log.info("update done for BankAccount on Rib : "
    	+ bankAccountDTO.getRib()
    	+ "for user: " + bankAccountDTO.getUser());

    	// Bank account DTO data Mapped to DO
    	BankAccount bankAccount = bankAccountMapper
    			.toBankAccountDO(bankAccountDTO);

    	// Bank Account DO update done through repository interface
    	BankAccount bankAccountUpdate= bankAccountRepository
    			.save(bankAccount);

        log.info("New BankAccount Rib saved : "
                + bankAccountUpdate.getRib()
                + "for user: " + bankAccountUpdate.getUser()
                + " update request SUCCESS");

        return new ResponseEntity<BankAccountDTO>(bankAccountMapper
    			.toBankAccountDTO(bankAccountUpdate), HttpStatus.CREATED);
    }
    // ************************************************************************



    /**
     * Delete bank account.
     *
     * @param bankAccountDTO the bank account DTO
     * @return the response entity
     */
    @DeleteMapping(value= "/bankAccount")
    public ResponseEntity<BankAccountDTO> deleteBankAccount(
    		@RequestBody BankAccountDTO bankAccountDTO) {

    	log.info("Delete Bank acocount requested - RIB: "
    	+ bankAccountDTO.getRib()
    	+ " for user "
    	+ bankAccountDTO.getUser());

    	// Bank account DTO data Mapped to DO
    	BankAccount bankAccount = bankAccountMapper
        		.toBankAccountDO(bankAccountDTO);

    	// Bank Account DO deleted done through repository interface
    	bankAccountRepository.delete(bankAccount);

        log.info("New BankAccount Rib saved : "
        + bankAccount.getRib()
        + "for user: " + bankAccount.getUser()
        + " delete request SUCCESS");

        return new ResponseEntity<>(bankAccountDTO, HttpStatus.OK);
    }
    // ************************************************************************


}
