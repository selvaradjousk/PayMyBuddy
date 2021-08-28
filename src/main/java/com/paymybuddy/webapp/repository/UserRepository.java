package com.paymybuddy.webapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paymybuddy.webapp.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {


	// **************************** TODOs LIST ***********************************
	
	// Method: c
	// --> User findUserById(Integer id);
	// --> User findByEmail(String email)
	// --> User findByUserName(String userName);
	// --> User findByEmailAndPassword(String email, String password);
	// --> Optional<User> findByEmail(String email);
	// --> Page<User> listUserNotContact(User payer, @Param("x")String keyWord , Pageable pageable)
	// --> Page<User> listUserAlreadyContact(User beneficiary, @Param("x")String keyWord , Pageable pageable)
	// --> 
	
	
	/**
	 * Find by email.
	 *
	 * @param email the email
	 * @return the user
	 */
	public User findUserByEmail(String email);
	
	
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
	public User findUserById(Integer id);
	
}













// Custom JPQL Queries with @Query
// @Query("select u from UserEntity u where u.name = :name")
// UserEntity findByNameCustomQuery(@Param("name") String name);

// Native Queries with @Query
// @Query(
// value = "select * from user as u where u.name = :name", nativeQuery = true)
// UserEntity findByNameNativeQuery(@Param("name") String name);


