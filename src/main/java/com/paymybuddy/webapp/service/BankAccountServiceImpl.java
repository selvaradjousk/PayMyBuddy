package com.paymybuddy.webapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.webapp.dto.BankAccountDTO;
import com.paymybuddy.webapp.dto.UserDTO;
import com.paymybuddy.webapp.model.BankAccount;
import com.paymybuddy.webapp.model.User;
import com.paymybuddy.webapp.repository.BankAccountRepository;
import com.paymybuddy.webapp.util.BankAccountMapper;
import com.paymybuddy.webapp.util.UserMapper;

import lombok.extern.log4j.Log4j2;

// TODO: Auto-generated Javadoc
/** The Constant log. */
@Log4j2
@Service
public class BankAccountServiceImpl implements IBankAccountService{


	// **************************** TODOs LIST ***********************************
	
	// Method:
	// --> getAllBankAccountByUser(UserDTO) served by bankAccountRepository.findBankAccountByUser(user) : listBankAccountDTO
	// --> addBankAccount(String rib, userDTO): bankAccountDTO served by Mapper toBankAccountDTO
	// --> deleteBankAccount(rib) served by bankAccountRepository.deleteById(id)



    /** The bank account repository. */
	@Autowired
    BankAccountRepository bankAccountRepository;

	/** The user mapper. */
	public UserMapper userMapper = new UserMapper();
    
    /** The bank account mapper. */
    public BankAccountMapper bankAccountMapper
    = new BankAccountMapper();

    /**
     * Instantiates a new bank account service impl.
     *
     * @param bankAccountRepository the bank account repository
     * @param userMapper the user mapper
     * @param bankAccountMapper the bank account mapper
     */
    public BankAccountServiceImpl(
    		BankAccountRepository bankAccountRepository,
    		UserMapper userMapper,
			BankAccountMapper bankAccountMapper) {
		super();
		this.bankAccountRepository = bankAccountRepository;
		this.userMapper = userMapper;
		this.bankAccountMapper = bankAccountMapper;
	}


    /**
     * Find bank account by user.
     *
     * @param userDTO the user DTO
     * @return the list
     */
    @Override
	public List<BankAccountDTO> findBankAccountByUser(UserDTO userDTO) {

        log.info(" ====> FIND BANK Acccount by USER requested <==== ");

        User user = userMapper.toUserDO(userDTO);

        List<BankAccount> listBankAccount = bankAccountRepository
        		.findBankAccountByUser(user);

        List<BankAccountDTO> listBankAccountDTO = new ArrayList<>();

        for (BankAccount bankAccount: listBankAccount) {
            BankAccountDTO bankAccountDTO = bankAccountMapper
            		.toBankAccountDTO(bankAccount);

            listBankAccountDTO.add(bankAccountDTO);
        }
        log.info(" ====> BANK Account by USER listing successfully <==== ");

        return listBankAccountDTO  ;
	}

    /**
     * Adds the bank account.
     *
     * @param rib the rib
     * @param userDTO the user DTO
     * @return the bank account DTO
     */
    //******************************************************************
	@Override
	public BankAccountDTO addBankAccount(String rib, UserDTO userDTO) {

    	log.info(" ====> BANK Account CREATION requested <==== ");
    	
        User user = userMapper.toUserDO(userDTO);

        BankAccount newBankAccount = new BankAccount(user,rib);

        BankAccount bankAccountAdd = bankAccountRepository
        		.save(newBankAccount);

        BankAccountDTO bankAccountDTO = bankAccountMapper
        		.toBankAccountDTO(bankAccountAdd);

        log.info(" ====> BANK Account CREATION performed"
        		+ " sucessfully <==== ");

        return bankAccountDTO;
	}

    //******************************************************************

    /**
     * Delete bank account.
     *
     * @param id the id
     */
    @Override
    public void deleteBankAccount(Integer id) {

        log.info(" ====> BANK Account DELETION requested <==== ");

        bankAccountRepository.deleteById(id);

        log.info(" ====> BANK Account DELETION sucessfull <==== ");
    }
    //*******************************************************************

}
