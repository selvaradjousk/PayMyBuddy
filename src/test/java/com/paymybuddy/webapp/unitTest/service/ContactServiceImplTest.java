package com.paymybuddy.webapp.unitTest.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
        assertEquals(4, listContactDTO.size());
    }
    // ************************************************************************
}
