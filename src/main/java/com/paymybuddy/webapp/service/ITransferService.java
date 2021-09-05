package com.paymybuddy.webapp.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.paymybuddy.webapp.dto.TransferDTO;
import com.paymybuddy.webapp.dto.UserDTO;
import com.paymybuddy.webapp.model.User;

/**
 * The Interface ITransferService.
 */
public interface ITransferService {

	/**
	 * Find all transfers.
	 *
	 * @return the list
	 */
	List<TransferDTO> findAllTransfers();

	/**
	 * Find all transfer by user.
	 *
	 * @param userDTO the user DTO
	 * @return the list
	 */
	List<TransferDTO> findAllTransferByUser(UserDTO userDTO);

	/**
	 * Find all transfer by user.
	 *
	 * @param userDTO the user DTO
	 * @param pageable the pageable
	 * @return the page
	 */
	Page<TransferDTO> findAllTransferByUser(
			UserDTO userDTO, Pageable pageable);

	/**
	 * Last three transfers.
	 *
	 * @param userDTO the user DTO
	 * @param pageable the pageable
	 * @return the page
	 */
	Page<TransferDTO> lastThreeTransfers(
			UserDTO userDTO, Pageable pageable);

	/**
	 * Adds the transfer.
	 *
	 * @param rib the rib
	 * @param amount the amount
	 * @param type the type
	 * @param userBeneficiary the user beneficiary
	 * @return the transfer DTO
	 */
	TransferDTO addTransfer(
			String rib,
			double amount,
			String type,
			User userBeneficiary);

	/**
	 * Find all by user type credit.
	 *
	 * @param userDTO the user DTO
	 * @return the list
	 */
	List<TransferDTO> findAllByUserTypeCredit(UserDTO userDTO);

	/**
	 * Find all by user type debit.
	 *
	 * @param userDTO the user DTO
	 * @return the list
	 */
	List<TransferDTO> findAllByUserTypeDebit(UserDTO userDTO);


	
}
