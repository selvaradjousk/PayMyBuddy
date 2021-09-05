package com.paymybuddy.webapp.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.paymybuddy.webapp.model.User;

/**
 * The Interface UserRepository.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	/**
	 * Find by email.
	 *
	 * @param email the email
	 * @return the user
	 */
	User findUserByEmail(String email);

	/**
	 * Find by email.
	 *
	 * @param email the email
	 * @return the optional
	 */
	Optional<User> findByEmail(String email);

	/**
	 * Find user by id.
	 *
	 * @param id the id
	 * @return the user
	 */
	User findUserById(Integer id);

	/**
	 * Find by user name.
	 *
	 * @param userName the user name
	 * @return the user
	 */
	User findByUserName(String userName);

	   /**
   	 * List user not buddy.
   	 *
   	 * @param payer the payer
   	 * @param mc the mc
   	 * @param pageable the pageable
   	 * @return the page
   	 */
   	@Query("select u from User  u "
	            + "WHERE u not in (Select contact from Contact f where f.payer= :payer)"
	            + " AND u.email like :x")
	public Page<User> listUserNotBuddy(User payer, @Param("x") String mc, Pageable pageable);

}
