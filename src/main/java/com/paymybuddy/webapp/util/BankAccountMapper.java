package com.paymybuddy.webapp.util;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import com.paymybuddy.webapp.dto.BankAccountDTO;
import com.paymybuddy.webapp.model.BankAccount;

/**
 * The Class BankAccountMapper.
 */
@Component
public class BankAccountMapper {

//    LocalDate createDate = LocalDate.now();
	  /**
  	 * To bank account DO.
  	 *
  	 * @param bankAccountDTO the bank account DTO
  	 * @return the bank account
  	 */
  	public BankAccount toBankAccountDO(@Validated @NotNull BankAccountDTO bankAccountDTO) {
	        BankAccount bankAccount = new BankAccount();

	        if (bankAccountDTO != null) {
	        bankAccount.setIdBankAccount(bankAccountDTO.getIdBankAccount());
	        bankAccount.setUser(bankAccountDTO.getUser());
	        bankAccount.setRib(bankAccountDTO.getRib());
	        return bankAccount;

	        } else {
	            return null;
	        }
	    }

	    /**
    	 * To bank account DTO.
    	 *
    	 * @param bankAccount the bank account
    	 * @return the bank account DTO
    	 */
    	public BankAccountDTO toBankAccountDTO(@Validated @NotNull BankAccount bankAccount) {
	        BankAccountDTO bankAccountDTO = new BankAccountDTO();

	        if (bankAccount != null) {
	        bankAccountDTO.setIdBankAccount(bankAccount.getIdBankAccount());
	        bankAccountDTO.setUser(bankAccount.getUser());
	        bankAccountDTO.setRib(bankAccount.getRib());
	        return bankAccountDTO;

	        } else {
	        	return null;
	        }
    	}
}
