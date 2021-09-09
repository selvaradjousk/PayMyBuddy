package com.paymybuddy.webapp.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.paymybuddy.webapp.model.Transaction;
import com.paymybuddy.webapp.model.User;

/**
 * The Interface TransactionRepository.
 */
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {


	/**
	 * Find all transaction by payer.
	 *
	 * @param payer the payer
	 * @return the list
	 */
	List<Transaction> findAllTransactionByPayer(User payer);

	/**
	 * Find all transaction by payer.
	 *
	 * @param payer the payer
	 * @param pageable the pageable
	 * @return the page
	 */
	Page<Transaction> findAllTransactionByPayer(
			User payer,
			Pageable pageable);

	 /**
	 * Last three transactions.
	 *
	 * @param payer the payer
	 * @param pageable the pageable
	 * @return the page
	 */
	@Query("SELECT t FROM Transaction t"
			+ " WHERE t.payer= :payer  ORDER BY date desc")
	Page<Transaction> lastThreeTransactions(
			User payer,
			Pageable pageable);

	/**
	  * Last three transactions beneficiary.
	  *
	  * @param user the user
	  * @param pageable the pageable
	  * @return the page
	  */
	 @Query("SELECT t FROM Transaction t"
	 		+ " WHERE t.beneficiary= :user  ORDER BY date desc")
	Page<Transaction> lastThreeTransactionsBeneficiary(
			User user,
			Pageable pageable);

}
