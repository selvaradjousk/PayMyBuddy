package com.paymybuddy.webapp.unitTest.util;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.paymybuddy.webapp.dto.TransferDTO;
import com.paymybuddy.webapp.model.Transfer;
import com.paymybuddy.webapp.model.User;
import com.paymybuddy.webapp.util.TransferMapper;

	class TransferMapperTest {


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
		TransferDTO dto = new TransferDTO(
				10,
				"testRib",
				LocalDate.parse("2021-08-26"),
				1000.0,
				"CREDIT",
				user);
		
		TransferMapper mapper = new TransferMapper();
		Transfer entity = mapper.toTransferDO(dto);
		
		assertEquals(entity.getUser(), dto.getUser());
		}
		
		

		// *******************************************************************	
		
		@DisplayName(" DTO to Entity null DTO Exception- "
				+ "GIVEN DTO null "
				+ "WHEN Requested to DO"
				+ "THEN returns Exception")
		@Test
	    public void testToDONullDTO() {
		
		TransferMapper mapper = new TransferMapper();
		Transfer entity = mapper.toTransferDO(null);
		
		assertNull(entity);
	    assertThrows(NullPointerException.class, ()
	     		  -> mapper.toTransferDO(null).getUser());
	    

		}
			

		// *******************************************************************	
		
		@DisplayName("DO to DTO - "
				+ "GIVEN DO  "
				+ "WHEN Requested to DTO"
				+ "THEN returns DTO")
		@Test
	    public void testToDTO() {
			
		Transfer entity = new Transfer(
				"testRib",
				LocalDate.parse("2021-08-26"),
				1000.0,
				"CREDIT",
				user);
		
		TransferMapper mapper = new TransferMapper();
		TransferDTO dto = mapper.toTransferDTO(entity);
		
		assertEquals(dto.getUser(), entity.getUser());
		assertEquals(dto.getType(), entity.getType());
		}
		
		
		// *******************************************************************	
		
		@DisplayName("DO to DTO null DO Exception- "
				+ "GIVEN DO null "
				+ "WHEN Requested to DTO"
				+ "THEN returns Exception")
		@Test
	    public void testToDTONullDO() {
		
			TransferMapper mapper = new TransferMapper();

	    assertThrows(NullPointerException.class, ()
	     		  -> mapper.toTransferDTO(null).getUser());
	    

		}
			

		// *******************************************************************	
		
}
