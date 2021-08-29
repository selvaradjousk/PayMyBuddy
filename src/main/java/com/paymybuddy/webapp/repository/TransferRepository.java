package com.paymybuddy.webapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.paymybuddy.webapp.model.Transfer;
import com.paymybuddy.webapp.model.User;

public interface TransferRepository extends JpaRepository<Transfer, Integer> {


	// **************************** TODOs LIST ***********************************
	
	// Method: c
	// --> find all transfer by user
	// --> find all transaction by user - Type credit ---> TODO have to change id presence in constructor of model
	// --> find all transaction by user - Type debit ---> TODO have to change id presence in constructor of model
	// --> find all transaction by user - Pageable ---> TODO
	// --> https://www.baeldung.com/spring-data-jpa-query (Pageable) 
	// https://www.petrikainulainen.net/programming/spring-framework/spring-data-jpa-tutorial-part-seven-pagination/
	// https://www.javacodegeeks.com/2019/02/pagination-sorting-spring-data-jpa.html
	
	
	List<Transfer> findAllByUser(User user);

	@Query("Select t from Transfer t where t.user = :user and t.type='CREDIT'")
	List<Transfer> findAllByUserTypeCredit(User user);
	
	@Query("Select t from Transfer t where t.user = :user and t.type='DEBIT'")
	List<Transfer> findAllByUserTypeDebit(User user);
	
}
