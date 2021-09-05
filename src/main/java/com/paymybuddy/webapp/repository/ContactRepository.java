package com.paymybuddy.webapp.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paymybuddy.webapp.model.Contact;
import com.paymybuddy.webapp.model.User;

/**
 * The Interface ContactRepository.
 */
@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {

	/**
	 * Find list contact by payer.
	 *
	 * @param payer the payer
	 * @return the list
	 */
//    @Query(value = "SELECT c FROM Contact c WHERE c.payer = :payer", nativeQuery = true)
	List<Contact> findListContactByPayer(User payer);
	
	/**
	 * Find list contact by contact.
	 *
	 * @param contact the contact
	 * @return the list
	 */
//    @Query(value = "SELECT c FROM Contact c  WHERE c.contact = :contact", nativeQuery = true)
	List<Contact> findListContactByContact(User contact);
	
	/**
	 * Find contact by payer.
	 *
	 * @param Payer the payer
	 * @param pageable the pageable
	 * @return the page
	 */
//    @Query(value = "SELECT c FROM Contact c WHERE c.payer = :payer", nativeQuery = true)
	Page<Contact> findContactByPayer(User Payer, Pageable pageable);
    
    

}
