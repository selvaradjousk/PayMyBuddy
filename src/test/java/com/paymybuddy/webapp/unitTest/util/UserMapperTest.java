package com.paymybuddy.webapp.unitTest.util;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.paymybuddy.webapp.dto.UserDTO;
import com.paymybuddy.webapp.exception.DataNotConformException;
import com.paymybuddy.webapp.model.User;
import com.paymybuddy.webapp.util.UserMapper;

class UserMapperTest {

	
	// *******************************************************************	
	
	@DisplayName("DTO to Entity - "
			+ "GIVEN DTO  "
			+ "WHEN Requested to DO"
			+ "THEN returns DO")
	@Test
    public void testToEntity() {
	UserDTO dto = new UserDTO(
			"testUserName",
	            "testFirstName",
				"myEmail",
				"password");
	
	UserMapper mapper = new UserMapper();
	User entity = mapper.toUserDO(dto);
	
//	assertEquals(entity.toString(), dto.toString());
	assertEquals(entity.getEmail(), dto.getEmail());
	}
	
	
	
	// *******************************************************************	
	
	@DisplayName(" DTO to Entity null DTO Exception- "
			+ "GIVEN DTO null "
			+ "WHEN Requested to DO"
			+ "THEN returns Exception")
	@Test
    public void testToEntityNullException() {
	
		UserMapper mapper = new UserMapper();
		User entity = mapper.toUserDO(null);
	
	assertNull(entity);
    assertThrows(NullPointerException.class, ()
     		  -> mapper.toUserDO(null).getEmail());
	}
	
		
	
	// *******************************************************************	
	
	
	@DisplayName("DO to DTO - "
			+ "GIVEN DO  "
			+ "WHEN Requested to DTO"
			+ "THEN returns DTO")
	@Test
    public void testToDTO() {
	User entity = new User(
			100,
	           "testUserName",
	            "testFirstName",
	            "testLastName",
			"myEmail",
			"password",
			LocalDate.parse("2021-08-28"),
			LocalDate.parse("2021-08-28"),
			"admin",
			true,
			1000.0);
	
	UserMapper mapper = new UserMapper();
	UserDTO dto = mapper.toUserDTO(entity);
	
	assertEquals(dto.getFirstName(), entity.getFirstName());
	assertEquals(dto.getEmail(), entity.getEmail());
	}
	
	
	// *******************************************************************	
	
	@DisplayName("DO to DTO null DO Exception- "
			+ "GIVEN DO null "
			+ "WHEN Requested to DTO"
			+ "THEN returns Exception")
	@Test
    public void testToDTONullException() {
	
		
		UserMapper mapper = new UserMapper();
	
    assertThrows(DataNotConformException.class, ()
     		  -> mapper.toUserDTO(null).getId());
	}
	
	
	// *******************************************************************	
		
	
}
