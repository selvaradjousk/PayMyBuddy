package com.paymybuddy.webapp.service;

import java.util.List;

import com.paymybuddy.webapp.dto.ContactDTO;
import com.paymybuddy.webapp.dto.UserDTO;

public interface IContactService {

	ContactDTO deleteById(Integer id);
	
	List<ContactDTO> findContactByPayer(UserDTO ownerDTO);
	
	ContactDTO addContact(ContactDTO contactDTO);
	
	
}
