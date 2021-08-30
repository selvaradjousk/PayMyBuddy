package com.paymybuddy.webapp.unitTest.service;

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

@DisplayName("CONTACT SERVICE - H2 DB TEST ")
@SpringBootTest
@ActiveProfiles("test")
class ContactServiceImplTest {

    @Autowired
    IContactService contactService;

    @Autowired
    private IUserService userService;

    public UserMapper userMapper = new UserMapper();

    
    // ************************************************************************

    @Test
    public void testFindContactByPayer(){

        UserDTO userDTO = userService.findUserByEmail("testemail2@email.com");
        List<ContactDTO> listContactDTO = contactService.findContactByPayer(userDTO);
        assertNotNull(listContactDTO);
        assertEquals(3, listContactDTO.size());
    }
    
    
    
    // ************************************************************************

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
