package com.paymybuddy.webapp.util;

import org.springframework.stereotype.Component;

import com.paymybuddy.webapp.dto.BankAccountDTO;
import com.paymybuddy.webapp.model.BankAccount;

/**
 * The Class BankAccountMapper.
 */
@Component
public class BankAccountMapper {

	  /**
  	 * To bank account DO.
  	 *
  	 * @param bankAccountDTO the bank account DTO
  	 * @return the bank account
  	 */
  	public BankAccount toBankAccountDO(BankAccountDTO bankAccountDTO) {
	        BankAccount bankAccount = new BankAccount();

	        bankAccount.setIdBankAccount(bankAccountDTO.getIdBankAccount());
	        bankAccount.setUser(bankAccountDTO.getUser());
	        bankAccount.setRib(bankAccountDTO.getRib());
	        return bankAccount;
	    }

	    /**
    	 * To bank account DTO.
    	 *
    	 * @param bankAccount the bank account
    	 * @return the bank account DTO
    	 */
    	public BankAccountDTO toBankAccountDTO(BankAccount bankAccount) {
	        BankAccountDTO bankAccountDTO = new BankAccountDTO();

	        bankAccountDTO.setIdBankAccount(bankAccount.getIdBankAccount());
	        bankAccountDTO.setUser(bankAccount.getUser());
	        bankAccountDTO.setRib(bankAccount.getRib());
	        return bankAccountDTO;
	    }
}
