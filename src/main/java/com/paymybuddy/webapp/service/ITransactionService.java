package com.paymybuddy.webapp.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.paymybuddy.webapp.dto.TransactionDTO;
import com.paymybuddy.webapp.dto.UserDTO;

/**
 * The Interface ITransactionService.
 */
public interface ITransactionService {

	/**
	 * Find all transactions.
	 *
	 * @return the list
	 */
	List<TransactionDTO> findAllTransactions();

	/**
	 * Find all transaction by payer.
	 *
	 * @param userDTO the user DTO
	 * @return the list
	 */
	List<TransactionDTO> findAllTransactionByPayer(
			UserDTO userDTO);

	/**
	 * Find all transaction by payer.
	 *
	 * @param userDTO the user DTO
	 * @param pageable the pageable
	 * @return the page
	 */
	Page<TransactionDTO> findAllTransactionByPayer(
			UserDTO userDTO, Pageable pageable);

	/**
	 * Last three transactions.
	 *
	 * @param userDTO the user DTO
	 * @param pageable the pageable
	 * @return the page
	 */
	Page<TransactionDTO> lastThreeTransactions(
			UserDTO userDTO, Pageable pageable);

	/**
	 * Adds the transaction.
	 *
	 * @param transactionDTO the transaction DTO
	 * @return the transaction DTO
	 */
	TransactionDTO addTransaction(TransactionDTO transactionDTO);
	
	
	/**
	 * Last three transactions beneficiary.
	 *
	 * @param userDTO the user DTO
	 * @param pageable the pageable
	 * @return the page
	 */
	Page<TransactionDTO> lastThreeTransactionsBeneficiary(UserDTO userDTO, Pageable pageable);

}
