package com.paymybuddy.webapp.IT.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.paymybuddy.webapp.dto.ContactDTO;
import com.paymybuddy.webapp.dto.UserDTO;
import com.paymybuddy.webapp.service.IContactService;
import com.paymybuddy.webapp.service.IUserService;
import com.paymybuddy.webapp.util.UserMapper;

@DisplayName("CONTACT SERVICE - IT MySQL DB TEST ")
@SpringBootTest
@ActiveProfiles("integration")
class ServiceContact_IT {

    @Autowired
    IContactService contactService;

    @Autowired
    private IUserService userService;

    public UserMapper userMapper = new UserMapper();

    
    // ************************************************************************
	
    @DisplayName("Find All Contacts By User(Service) - "
			+ "GIVEN Contacts list "
			+ "WHEN Requested find all contacts list by user"
			+ "THEN returns expected user contacts list size")
    @Test
    public void testFindContactByPayer(){

        UserDTO userDTO = userService.findUserByEmail("testemail2@email.com");
        List<ContactDTO> listContactDTO = contactService.findContactByPayer(userDTO);
        assertNotNull(listContactDTO);
        assertEquals(3, listContactDTO.size());
    }
    
    
    
    // ************************************************************************
	
    @DisplayName("Add New Contact - "
			+ "GIVEN new Contact "
			+ "WHEN Requested add new contact"
			+ "THEN returns expected contact added")
    @Test
    public void testAddContact(){

    	//GIVEN
        UserDTO userDTO = userService
        		.findUserByEmail("testemail2@email.com");
        
        List<ContactDTO> listContactDTO = contactService
        		.findContactByPayer(userDTO);
        
        UserDTO userContactDTO = userService
        		.findUserById(2);
 
        //WHEN
        ContactDTO contactDTO = new ContactDTO(
        		LocalDate.now(),
        		userMapper.toUserDO(userContactDTO),
        		userMapper.toUserDO(userDTO));
        
        contactService.addContact(contactDTO);
        
        List<ContactDTO> listContactDTOAfterAdd = contactService
        		.findContactByPayer(userDTO);
 
        //THEN
        assertNotEquals(listContactDTO.size(),listContactDTOAfterAdd.size());
        assertEquals(listContactDTO.size()+1,listContactDTOAfterAdd.size());
    }
    
    
    // ************************************************************************
	
    @DisplayName("Delete Contact - "
			+ "GIVEN Contact "
			+ "WHEN Requested delete contact"
			+ "THEN returns expected contact deleted")
    @Test
    public void testDeleteContact(){
    	
        //GIVEN
        UserDTO userDTO = userService
        		.findUserByEmail("testemail1@email.com");
        
        List<ContactDTO> listContactDTO = contactService
        		.findContactByPayer(userDTO);
        
        //WHEN
        contactService.deleteById(listContactDTO.get(1)
        		.getIdContact());
        
        List<ContactDTO> listContactDTOAfterDelete = contactService
        		.findContactByPayer(userDTO);
        
        //THEN
        assertNotEquals(listContactDTO.size(),listContactDTOAfterDelete.size());
        assertEquals(listContactDTO.size()-1,listContactDTOAfterDelete.size());
    }
    // ************************************************************************ 

    
    
}
