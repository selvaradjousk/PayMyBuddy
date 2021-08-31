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


	// **************************** TODOs LIST ***********************************
	
	// Method: c
	// --> list all contacts
	// --> list all contacts connected to a user
	// --> list all contacts not yet connected to a user
	// --> Add a contact
	// --> Delete a contact
	
	/**
	 * Find list contact by payer.
	 *
	 * @param payer the payer
	 * @return the list
	 */
	List<Contact> findListContactByPayer(User payer);
	
	/**
	 * Find list contact by contact.
	 *
	 * @param contact the contact
	 * @return the list
	 */
	List<Contact> findListContactByContact(User contact);
	
	/**
	 * Find contact by payer.
	 *
	 * @param Payer the payer
	 * @param pageable the pageable
	 * @return the page
	 */
	Page<Contact> findContactByPayer(User Payer, Pageable pageable);
	
	
	
	
	
	
	
}
