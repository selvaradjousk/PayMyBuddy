package com.paymybuddy.webapp.util;


import javax.annotation.Nonnull;

import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import com.paymybuddy.webapp.dto.ContactDTO;
import com.paymybuddy.webapp.model.Contact;

/**
 * The Class ContactMapper.
 */
@Component
public class ContactMapper {

//	LocalDate creationDate = LocalDate.now();
	
    /**
 * To contact DO.
 *
 * @param contactDTO the contact DTO
 * @return the contact
 */
public Contact toContactDO(@Validated @Nonnull ContactDTO contactDTO) {

        Contact contact = new Contact();

//        if (contactDTO != null){
        contact.setIdContact(contactDTO.getIdContact());
        contact.setCreationDate(contactDTO.getCreationDate());
        contact.setPayer(contactDTO.getPayer());
        contact.setContact(contactDTO.getContact());

        return contact;
//        } else {
//        return null;
//        }
     }

    /**
     * To contact DTO.
     *
     * @param contact the contact
     * @return the contact DTO
     */
    public ContactDTO toContactDTO(@Validated @Nonnull Contact contact) {

        ContactDTO contactDTO = new ContactDTO();

//        if (contact != null) {
        contactDTO.setIdContact(contact.getIdContact());
        contactDTO.setCreationDate(contact.getCreationDate());
        contactDTO.setPayer(contact.getPayer());
        contactDTO.setContact(contact.getContact());

        return contactDTO;

//        } else {
//        return null;
//        }
    }
}
