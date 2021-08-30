package com.paymybuddy.webapp.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.paymybuddy.webapp.model.Transaction;
import com.paymybuddy.webapp.model.User;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {


	// **************************** TODOs LIST ***********************************
	
	// Method: c
	// --> Find list of Transactions By Payer


	List<Transaction> findAllTransactionByPayer(User payer);

	Page<Transaction> findAllTransactionByPayer(User payer, Pageable pageable);

	 @Query("SELECT t FROM Transaction t WHERE t.payer= :user  ORDER BY date desc")
	Page<Transaction> lastThreeTransactions(User user, Pageable pageable);
	
}
