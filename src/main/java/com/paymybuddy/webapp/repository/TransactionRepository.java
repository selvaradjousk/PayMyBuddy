package com.paymybuddy.webapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paymybuddy.webapp.model.Transaction;
import com.paymybuddy.webapp.model.User;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {


	// **************************** TODOs LIST ***********************************
	
	// Method: c
	// --> Find list of Transactions By Payer
	// --> 
	// --> 
	// --> 
	// --> 
	// --> 
	// --> 
	// --> 
	// --> 

	List<Transaction> findAllByPayer(User payer);
	
}
