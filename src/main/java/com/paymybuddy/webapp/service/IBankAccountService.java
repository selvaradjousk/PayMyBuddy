package com.paymybuddy.webapp.service;

import java.util.List;

import com.paymybuddy.webapp.dto.BankAccountDTO;
import com.paymybuddy.webapp.dto.UserDTO;

public interface IBankAccountService {




	// **************************** TODOs LIST ***********************************
	
	// Method: c
	// -->
	// --> 
	// --> 
	// --> 
	// --> 
	// --> 
	// --> 
	// --> 
	// --> 
	

	List<BankAccountDTO> findBankAccountByUser(UserDTO userDTO);
	
	BankAccountDTO addBankAccount(String rib, UserDTO userDTO );

	void deleteBankAccount(Integer id);
}
