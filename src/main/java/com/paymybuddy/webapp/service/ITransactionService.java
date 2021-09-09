package com.paymybuddy.webapp.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.paymybuddy.webapp.dto.TransactionDTO;
import com.paymybuddy.webapp.dto.UserDTO;
import com.paymybuddy.webapp.model.Transaction;
import com.paymybuddy.webapp.model.User;

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
	 * Do save new transaction.
	 *
	 * @param page the page
	 * @param amount the amount
	 * @param contactEmail the contact email
	 * @param description the description
	 * @param beneficiary the beneficiary
	 * @param payer the payer
	 * @return the string
	 */
	String doSaveNewTransaction(
			int page,
			Double amount,
			String contactEmail,
			String description,
			User beneficiary,
			User payer);


	/**
	 * Delete by id.
	 *
	 * @param idTransactionDTO the id transaction DTO
	 * @return the transaction
	 */
	Transaction deleteById(int idTransactionDTO);


	/**
	 * Last three transactions beneficiary.
	 *
	 * @param userDTO the user DTO
	 * @param pageable the pageable
	 * @return the page
	 */
	Page<TransactionDTO> lastThreeTransactionsBeneficiary(
			UserDTO userDTO,
			Pageable pageable);

}
