package com.paymybuddy.webapp.service;

import java.util.List;

import com.paymybuddy.webapp.dto.BankAccountDTO;
import com.paymybuddy.webapp.dto.UserDTO;

/**
 * The Interface IBankAccountService.
 */
public interface IBankAccountService {

	/**
	 * Find bank account by user.
	 *
	 * @param userDTO the user DTO
	 * @return the list
	 */
	List<BankAccountDTO> findBankAccountByUser(UserDTO userDTO);

	/**
	 * Adds the bank account.
	 *
	 * @param rib the rib
	 * @param userDTO the user DTO
	 * @return the bank account DTO
	 */
	BankAccountDTO addBankAccount(String rib, UserDTO userDTO);

	/**
	 * Delete bank account.
	 *
	 * @param id the id
	 */
	void deleteBankAccount(Integer id);
}
