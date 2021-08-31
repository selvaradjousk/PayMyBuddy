package com.paymybuddy.webapp.service;

import java.util.List;

import com.paymybuddy.webapp.dto.ContactDTO;
import com.paymybuddy.webapp.dto.UserDTO;

/**
 * The Interface IContactService.
 */
public interface IContactService {

	/**
	 * Delete by id.
	 *
	 * @param id the id
	 * @return the contact DTO
	 */
	ContactDTO deleteById(Integer id);
	
	/**
	 * Find contact by payer.
	 *
	 * @param ownerDTO the owner DTO
	 * @return the list
	 */
	List<ContactDTO> findContactByPayer(UserDTO ownerDTO);
	
	/**
	 * Adds the contact.
	 *
	 * @param contactDTO the contact DTO
	 * @return the contact DTO
	 */
	ContactDTO addContact(ContactDTO contactDTO);
	
	
}
