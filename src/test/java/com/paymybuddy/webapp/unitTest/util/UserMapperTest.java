package com.paymybuddy.webapp.unitTest.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.paymybuddy.webapp.dto.UserDTO;
import com.paymybuddy.webapp.model.User;
import com.paymybuddy.webapp.util.UserMapper;

class UserMapperTest {

	@Test
    public void testToEntity() {
	UserDTO dto = new UserDTO("userName", "firstName", "email", "password");
	
	UserMapper mapper = new UserMapper();
	User entity = mapper.toUserDO(dto);
	
	assertEquals(entity.toString(), dto.toString());
	}
	
	@Test
    public void testToDTO() {
	User entity = new User(
    		100,
            "testUserName",
            "testFirstName",
            "testLastName",
            "myEmail",
            "myPassword",
            LocalDate.parse("2021-08-26"),
            LocalDate.parse("2021-08-26"),
            "admin",
            true,
            1000.0);
	
	UserMapper mapper = new UserMapper();
	UserDTO dto = mapper.toUserDTO(entity);
	
	assertEquals(dto.getFirstName(), entity.getFirstName());
	assertEquals(dto.getEmail(), entity.getEmail());
	}
}
