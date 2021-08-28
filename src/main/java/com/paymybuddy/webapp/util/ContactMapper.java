package com.paymybuddy.webapp.util;


import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.paymybuddy.webapp.dto.ContactDTO;
import com.paymybuddy.webapp.model.Contact;

@Component
public class ContactMapper {

//	LocalDate creationDate = LocalDate.now();	
	
    public Contact toContactDO(ContactDTO contactDTO) {

        Contact contact = new Contact();

        contact.setIdContact(contactDTO.getIdContact());
        contact.setCreationDate(contactDTO.getCreationDate());
        contact.setPayer(contactDTO.getPayer());
        contact.setContact(contactDTO.getContact());



        return contact;
    }

    public ContactDTO toContactDTO(Contact contact) {

        ContactDTO contactDTO = new ContactDTO();

        contactDTO.setIdContact(contact.getIdContact());
        contactDTO.setCreationDate(contact.getCreationDate());
        contactDTO.setPayer(contact.getPayer());
        contactDTO.setContact(contact.getContact());

        return contactDTO;
    }
}
