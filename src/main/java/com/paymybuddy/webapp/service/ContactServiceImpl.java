package com.paymybuddy.webapp.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.webapp.dto.ContactDTO;
import com.paymybuddy.webapp.dto.UserDTO;
import com.paymybuddy.webapp.model.Contact;
import com.paymybuddy.webapp.model.User;
import com.paymybuddy.webapp.repository.ContactRepository;
import com.paymybuddy.webapp.util.ContactMapper;
import com.paymybuddy.webapp.util.UserMapper;


/**
 * The Class ContactServiceImpl.
 */
@Service
public class ContactServiceImpl implements IContactService {

	/** The contact repository. */
	@Autowired
	ContactRepository contactRepository;

	/** The user mapper. */
	private UserMapper userMapper = new UserMapper();
	
	/** The contact mapper. */
	private ContactMapper contactMapper = new ContactMapper();

	  /**
  	 * Find contact by payer.
  	 *
  	 * @param payerDTO the payer DTO
  	 * @return the list
  	 */
  	// ************************************************************************
	@Override
	public List<ContactDTO> findContactByPayer(UserDTO payerDTO) {

		List<ContactDTO> contactsDTOList = new ArrayList<>();
		User payer = userMapper.toUserDO(payerDTO);
		List<Contact> contactsList = contactRepository
				.findListContactByPayer(payer);

		if (contactsList != null) {

			for (Contact contact : contactsList) {

				ContactDTO contactDTO = new ContactDTO();
				contactDTO = contactMapper
						.toContactDTO(contact);
				contactsDTOList.add(contactDTO);
			}
			return contactsDTOList;
		} else {
			return Collections.emptyList();
		}
	}


    /**
     * Adds the contact.
     *
     * @param contactDTO the contact DTO
     * @return the contact DTO
     */
    // ************************************************************************
    @Override
    public ContactDTO addContact(ContactDTO contactDTO) {

        Contact contact = contactMapper
        		.toContactDO(contactDTO);
        contactDTO = contactMapper
        		.toContactDTO(contactRepository.save(contact));

        return contactDTO;
    }


    // ************************************************************************


    /**
     * Delete by id.
     *
     * @param id the id
     * @return the contact DTO
     */
    @Override
    public ContactDTO deleteById(Integer id) {

        contactRepository.deleteById(id);

        return null;
    }

}
