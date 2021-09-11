package com.paymybuddy.webapp.unitTest.util;


import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.paymybuddy.webapp.dto.TransactionDTO;
import com.paymybuddy.webapp.model.Transaction;
import com.paymybuddy.webapp.model.User;
import com.paymybuddy.webapp.util.TransactionMapper;

	class TransactionMapperTest {


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

//		@Test
//	    public void testToEntity() {
//		TransactionDTO dto = new TransactionDTO(
//				user,
//				user,
//				1000.0,
//				"testDescription",
//				LocalDate.parse("2021-08-26"),
//				0.25);
		
		
		
		// *******************************************************************	
		
		@DisplayName("DTO to Entity - "
				+ "GIVEN DTO  "
				+ "WHEN Requested to DO"
				+ "THEN returns DO")		
		@Test
	    public void testToEntity() {
		TransactionDTO dto = new TransactionDTO(
				user,
				user,
				1000.0,
				"testDescription");
		
		TransactionMapper mapper = new TransactionMapper();
		Transaction entity = mapper.toTransactionDO(dto);
		
		assertEquals(entity.getPayer(), dto.getPayer());
		}
		
		
		
		// *******************************************************************	
		
		
		@DisplayName(" DTO to Entity null DTO Exception- "
				+ "GIVEN DTO null "
				+ "WHEN Requested to DO"
				+ "THEN returns Exception")
		@Test
	    public void testToEntityNullException() {
		
			TransactionMapper mapper = new TransactionMapper();
			Transaction entity = mapper.toTransactionDO(null);
		
		assertNull(entity);
	    assertThrows(NullPointerException.class, ()
	     		  -> mapper.toTransactionDO(null).getBeneficiary());
		}
		
		
		// *******************************************************************	
		
		
		@DisplayName("DO to DTO - "
				+ "GIVEN DO  "
				+ "WHEN Requested to DTO"
				+ "THEN returns DTO")
		@Test
	    public void testToDTO() {
			
		Transaction entity = new Transaction(
				10,
				user,
				user,
				1000.0,
				"testDescription",
				LocalDate.parse("2021-08-26"),
				0.25);
		
		TransactionMapper mapper = new TransactionMapper();
		TransactionDTO dto = mapper.toTransactionDTO(entity);
		
		assertEquals(dto.getPayer(), entity.getPayer());
		assertEquals(dto.getDescription(), entity.getDescription());
		assertEquals(dto.getAmount(), entity.getAmount());
		}
		
		
		
		// *******************************************************************	
		
		@DisplayName("DO to DTO null DO Exception- "
				+ "GIVEN DO null "
				+ "WHEN Requested to DTO"
				+ "THEN returns Exception")
		@Test
	    public void testToDTONullException() {
		
			
			TransactionMapper mapper = new TransactionMapper();
			TransactionDTO dto = mapper.toTransactionDTO(null);
		
		assertNull(dto);
	    assertThrows(NullPointerException.class, ()
	     		  -> mapper.toTransactionDTO(null).getPayer());
		}
		
		
		// *******************************************************************	
		
		
}

