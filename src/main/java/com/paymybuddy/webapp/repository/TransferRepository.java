package com.paymybuddy.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paymybuddy.webapp.model.Transfer;
import com.paymybuddy.webapp.model.User;

public interface TransferRepository extends JpaRepository<Transfer, Integer> {


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
	
	Transfer findByUser(User user);

}
