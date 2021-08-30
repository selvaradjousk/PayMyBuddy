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

// **************************** TODOs LIST ***********************************

// Method: c

// --> getAllContactsByUser(String email) served by contactRepository.findAll()
// --> getContactByEmail(String email) served by userRepository.findByEmail(email)
// --> getContactNotConnected(userDTO) served by userRepository.listUserNotConnected(user)
// --> getContactConnected(userDTO) served by userRepository.listUserConnected(user)
// --> addContact(contactDTO) served by userRepository.save(contact))
// --> deleteContact(email) served by contactRepository.deleteByEmail()

@Service
public class ContactServiceImpl implements IContactService {

	@Autowired
	ContactRepository contactRepository;

	public UserMapper userMapper = new UserMapper();
	public ContactMapper contactMapper = new ContactMapper();

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


	@Override
	public ContactDTO deleteById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
