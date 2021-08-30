package com.paymybuddy.webapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.paymybuddy.webapp.dto.BankAccountDTO;
import com.paymybuddy.webapp.dto.UserDTO;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class BankAccountServiceImpl implements IBankAccountService{


	// **************************** TODOs LIST ***********************************
	
	// Method:
	// --> getAllBankAccountByUser(UserDTO) served by bankAccountRepository.findBankAccountByUser(user) : listBankAccountDTO
	// --> addBankAccount(String rib, userDTO): bankAccountDTO served by Mapper toBankAccountDTO
	// --> deleteBankAccount(rib) served by bankAccountRepository.deleteById(id)

	@Override
	public List<BankAccountDTO> findBankAccountByUser(UserDTO userDTO) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
