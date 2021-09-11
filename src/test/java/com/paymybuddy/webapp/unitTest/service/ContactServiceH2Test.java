package com.paymybuddy.webapp.unitTest.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;

import com.paymybuddy.webapp.dto.ContactDTO;
import com.paymybuddy.webapp.dto.UserDTO;
import com.paymybuddy.webapp.service.IContactService;
import com.paymybuddy.webapp.service.IUserService;
import com.paymybuddy.webapp.util.UserMapper;

@DisplayName("CONTACT SERVICE - H2 DB TEST ")
@SpringBootTest
@ActiveProfiles("test")
class ContactServiceH2Test {

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
	
    
    
    
    @DisplayName("Find All Contacts By Payer invalid - "
			+ "GIVEN Contacts list "
			+ "WHEN Requested find all contacts list by user Email invalid"
			+ "THEN returns expected  throws exception")
    @Test
    public void testFindContactByPayerEmailInvalid(){

		// GIVEN // WHEN // THEN
	    assertThrows(NullPointerException.class, ()
	     		  -> contactService.findContactByPayer(userService
	     				  .findUserByEmail("testemail2XXXXXX@email.com")));    
        
    }
    
    
    
    // ************************************************************************
	
    
    
    
    @DisplayName("Find All Contacts By Payer Null - "
			+ "GIVEN Contacts list "
			+ "WHEN Requested find all contacts list by user null"
			+ "THEN returns expected  throws exception")
    @Test
    public void testFindContactByPayerNull(){

		// GIVEN // WHEN // THEN
	    assertThrows(NullPointerException.class, ()
	     		  -> contactService.findContactByPayer(userService
	     				  .findUserByEmail(null)));    
        
    }
    
    
    
    // ************************************************************************
	
    
    
    
    @DisplayName("Find All Contacts By Payer Empty - "
			+ "GIVEN Contacts list "
			+ "WHEN Requested find all contacts list by user Empty"
			+ "THEN returns expected  throws exception")
    @Test
    public void testFindContactByPayerEmptyValue(){

		// GIVEN // WHEN // THEN
	    assertThrows(NullPointerException.class, ()
	     		  -> contactService.findContactByPayer(userService
	     				  .findUserByEmail("")));    
        
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
	
    
    
    
    @DisplayName("Add New Contact Payer Invalid- "
			+ "GIVEN new Contact Payer invalid"
			+ "WHEN Requested add new contact"
			+ "THEN returns expected throws exception")
    @Test
    public void testAddContactPayerInvalid(){

    	//GIVEN
        UserDTO userDTO = userService
        		.findUserByEmail("testemail2@email.com");
        
        contactService
        		.findContactByPayer(userDTO);
        
        userService
        		.findUserById(2);


		// GIVEN // WHEN // THEN
	    assertThrows(DataIntegrityViolationException.class, ()
	     		  -> contactService.addContact(new ContactDTO(
	     	        		LocalDate.now(),
	     	        		userMapper.toUserDO(null),
	     	        		userMapper.toUserDO(userDTO))));
    }
    
    
    
    
    
    // ************************************************************************
	
   
    
    
    @DisplayName("Add New Contact contact Invalid- "
			+ "GIVEN new Contact contact invalid"
			+ "WHEN Requested add new contact"
			+ "THEN returns expected throws exception")
    @Test
    public void testAddContactContactInvalid(){

    	//GIVEN
        userService
        		.findUserByEmail("testemail2@email.com");
        
    
        UserDTO userContactDTO = userService
        		.findUserById(2);


		// GIVEN // WHEN // THEN
	    assertThrows(DataIntegrityViolationException.class, ()
	     		  -> contactService.addContact(new ContactDTO(
	     	        		LocalDate.now(),
	     	        		userMapper.toUserDO(userContactDTO),
	     	        		userMapper.toUserDO(null))));
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

    
    
    @DisplayName("Delete Contact - Email Invalid- "
			+ "GIVEN Contact Email Invalid"
			+ "WHEN Requested delete contact"
			+ "THEN returns expected throws exception")
    @Test
    public void testDeleteContactWithEmailNonExist(){
         
		// GIVEN // WHEN // THEN
	    assertThrows(NullPointerException.class, ()
	     		  -> contactService
	        		.findContactByPayer(userService
	                		.findUserByEmail("testemailXXXXX1@email.com")));
    }
     

    
    // ************************************************************************
	

    
    
    @DisplayName("Delete Contact - Email Empty - "
			+ "GIVEN Contact Email Empty"
			+ "WHEN Requested delete contact"
			+ "THEN returns expected throws exception")
    @Test
    public void testDeleteContactWithEmailEmpty(){
         
		// GIVEN // WHEN // THEN
	    assertThrows(NullPointerException.class, ()
	     		  -> contactService
	        		.findContactByPayer(userService
	                		.findUserByEmail("")));
    }
     

    
    // ************************************************************************
	
  
    
    @DisplayName("Delete Contact - Email Null - "
			+ "GIVEN Contact Email Null"
			+ "WHEN Requested delete contact"
			+ "THEN returns expected throws exception")
    @Test
    public void testDeleteContactWithEmailNull(){
         
		// GIVEN // WHEN // THEN
	    assertThrows(NullPointerException.class, ()
	     		  -> contactService
	        		.findContactByPayer(userService
	                		.findUserByEmail(null)));
    }
     

    
    // ************************************************************************
	   
}
