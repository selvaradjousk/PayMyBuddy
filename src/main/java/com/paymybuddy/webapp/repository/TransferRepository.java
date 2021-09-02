package com.paymybuddy.webapp.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.paymybuddy.webapp.model.Transfer;
import com.paymybuddy.webapp.model.User;

/**
 * The Interface TransferRepository.
 */
public interface TransferRepository extends JpaRepository<Transfer, Integer> {

	// **************************** TODOs LIST ******************************

	// Method: c
	// --> find all transfer by user
	// --> find all transaction by user - Type credit ---> TODO have to
	//                  change id presence in constructor of model
	// --> find all transaction by user - Type debit ---> TODO
	//                   have to change id presence in constructor of model
	// --> find all transaction by user - Pageable ---> TODO
	// --> https://www.baeldung.com/spring-data-jpa-query (Pageable)
	// https://www.petrikainulainen.net/programming/
	// spring-framework/spring-data-jpa-tutorial-part-seven-pagination/
	// https://www.javacodegeeks.com/2019/02/
	// pagination-sorting-spring-data-jpa.html

	/**
	 * Find all by user.
	 *
	 * @param user the user
	 * @return the list
	 */
	List<Transfer> findAllByUser(User user);

	/**
	 * Find all by user type credit.
	 *
	 * @param user the user
	 * @return the list
	 */
	@Query("Select t from Transfer t"
			+ " where t.user = :user and t.type='CREDIT'")
	List<Transfer> findAllByUserTypeCredit(User user);

	/**
	 * Find all by user type debit.
	 *
	 * @param user the user
	 * @return the list
	 */
	@Query("Select t from Transfer t"
			+ " where t.user = :user and t.type='DEBIT'")
	List<Transfer> findAllByUserTypeDebit(User user);

	/**
	 * Find all transfer by user.
	 *
	 * @param user the user
	 * @param pageable the pageable
	 * @return the page
	 */
	Page<Transfer> findAllTransferByUser(User user, Pageable pageable);

	/**
	 * Last three transfers.
	 *
	 * @param user the user
	 * @param pageable the pageable
	 * @return the page
	 */
	@Query("SELECT t FROM Transfer t WHERE t.user= :user  ORDER BY date desc")
	Page<Transfer> lastThreeTransfers(User user, Pageable pageable);
}
