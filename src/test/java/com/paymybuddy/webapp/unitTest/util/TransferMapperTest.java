package com.paymybuddy.webapp.unitTest.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

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
	            LocalDate.parse("2019-12-31"),
	            LocalDate.parse("2019-12-31"),
	            "admin",
	            true,
	            1000.0);

		@Test
	    public void testToEntity() {
		TransferDTO dto = new TransferDTO(
				10,
				"testRib",
				LocalDate.parse("2019-12-31"),
				1000.0,
				"CREDIT",
				user);
		
		TransferMapper mapper = new TransferMapper();
		Transfer entity = mapper.toTransferDO(dto);
		
		assertEquals(entity.getUser(), dto.getUser());
		}
		
		@Test
	    public void testToDTO() {
			
		Transfer entity = new Transfer(
				"testRib",
				LocalDate.parse("2019-12-31"),
				1000.0,
				"CREDIT",
				user);
		
		TransferMapper mapper = new TransferMapper();
		TransferDTO dto = mapper.toTransferDTO(entity);
		
		assertEquals(dto.getUser(), entity.getUser());
//		assertEquals(dto.getType(), entity.getRib());
		assertEquals(dto.getType(), entity.getType());
		}

}
