package com.paymybuddy.webapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paymybuddy.webapp.model.Transfer;
import com.paymybuddy.webapp.model.User;

public interface TransferRepository extends JpaRepository<Transfer, Integer> {


	// **************************** TODOs LIST ***********************************
	
	// Method: c
	// --> find all transaction by user
	// --> find all transaction by user - Type credit
	// --> find all transaction by user - Type debit 
	// --> find all transaction by user - Pageable ---> TODO
	// --> 
	
	
	List<Transfer> findAllByUser(User payer);
	
}
