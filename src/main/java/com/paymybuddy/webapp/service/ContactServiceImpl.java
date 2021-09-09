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

import lombok.extern.log4j.Log4j2;


/**
 * The Class ContactServiceImpl.
 */
@Log4j2
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
	 * Instantiates a new contact service impl.
	 *
	 * @param contactRepository the contact repository
	 * @param userMapper the user mapper
	 * @param contactMapper the contact mapper
	 */
	public ContactServiceImpl(
			ContactRepository contactRepository,
			UserMapper userMapper,
			ContactMapper contactMapper) {

		this.contactRepository = contactRepository;
		this.userMapper = userMapper;
		this.contactMapper = contactMapper;
	}


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

		log.info("contactList :" + contactsList);

		if (contactsList != null) {

			for (Contact contact : contactsList) {

				ContactDTO contactDTO = new ContactDTO();

				log.info("contactDTO instance initiated" + contactDTO);

				contactDTO = contactMapper
						.toContactDTO(contact);
				contactsDTOList.add(contactDTO);
			}

			log.info("contactDTOList :" + contactsDTOList);

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

		log.info("add Contact -> contact :" + contact);

        contactDTO = contactMapper
        		.toContactDTO(contactRepository.save(contact));

		log.info("add Contact -> contactDTO :" + contactDTO);

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

        log.info("delete by Id SUCCESS");

        return null;
    }

}
