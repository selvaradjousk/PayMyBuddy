package com.paymybuddy.webapp.unitTest.util;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.paymybuddy.webapp.dto.BankAccountDTO;
import com.paymybuddy.webapp.dto.ContactDTO;
import com.paymybuddy.webapp.model.BankAccount;
import com.paymybuddy.webapp.model.Contact;
import com.paymybuddy.webapp.model.User;
import com.paymybuddy.webapp.util.BankAccountMapper;
import com.paymybuddy.webapp.util.ContactMapper;

	class ContactMapperTest {


		User user = new User(
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
		
		
		// *******************************************************************	
		
		@DisplayName("DTO to Entity - "
				+ "GIVEN DTO  "
				+ "WHEN Requested to DO"
				+ "THEN returns DO")
		@Test
	    public void testToEntity() {
		ContactDTO dto = new ContactDTO(
				LocalDate.parse("2021-08-26"),
				user,
				user);
		
		ContactMapper mapper = new ContactMapper();
		Contact entity = mapper.toContactDO(dto);
		
		assertEquals(entity.getPayer(), dto.getPayer());
		}
		
		
		// *******************************************************************	
		
		@DisplayName(" DTO to Entity null DTO Exception- "
				+ "GIVEN DTO null "
				+ "WHEN Requested to DO"
				+ "THEN returns Exception")
		@Test
	    public void testToEntityNullException() {
		
			ContactMapper mapper = new ContactMapper();
			Contact entity = mapper.toContactDO(null);
		
		assertNull(entity);
	    assertThrows(NullPointerException.class, ()
	     		  -> mapper.toContactDO(null).getPayer());
		}
		
			
		
		// *******************************************************************	
				
		@DisplayName("DO to DTO - "
				+ "GIVEN DO  "
				+ "WHEN Requested to DTO"
				+ "THEN returns DTO")
		@Test
	    public void testToDTO() {
			
		Contact entity = new Contact(
				10,
				LocalDate.parse("2021-08-26"),
				user,
				user);
		
		ContactMapper mapper = new ContactMapper();
		ContactDTO dto = mapper.toContactDTO(entity);
		
		assertEquals(dto.getPayer(), entity.getPayer());
		assertEquals(dto.getCreationDate(), entity.getCreationDate());
		}
		
		
		
		// *******************************************************************	
		
		@DisplayName("DO to DTO null DO Exception- "
				+ "GIVEN DO null "
				+ "WHEN Requested to DTO"
				+ "THEN returns Exception")
		@Test
	    public void testToDTONullException() {
		
			
			ContactMapper mapper = new ContactMapper();
			ContactDTO dto = mapper.toContactDTO(null);
		
		assertNull(dto);
	    assertThrows(NullPointerException.class, ()
	     		  -> mapper.toContactDTO(null).getPayer());
		}
		
		
		// *******************************************************************	
		
					

}

