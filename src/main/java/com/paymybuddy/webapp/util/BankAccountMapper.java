package com.paymybuddy.webapp.util;

import org.springframework.stereotype.Component;

import com.paymybuddy.webapp.dto.BankAccountDTO;
import com.paymybuddy.webapp.model.BankAccount;

@Component
public class BankAccountMapper {

	  public BankAccount toBankAccountDO(BankAccountDTO bankAccountDTO) {
	        BankAccount bankAccount = new BankAccount();

	        bankAccount.setIdBankAccount(bankAccountDTO.getIdBankAccount());
	        bankAccount.setUser(bankAccountDTO.getUser());
	        bankAccount.setRib(bankAccountDTO.getRib());
	        return bankAccount;
	    }

	    public BankAccountDTO toBankAccountDTO(BankAccount bankAccount) {
	        BankAccountDTO bankAccountDTO = new BankAccountDTO();

	        bankAccountDTO.setIdBankAccount(bankAccount.getIdBankAccount());
	        bankAccountDTO.setUser(bankAccount.getUser());
	        bankAccountDTO.setRib(bankAccount.getRib());
	        return bankAccountDTO;
	    }
}
