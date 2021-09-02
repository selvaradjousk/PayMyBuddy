package com.paymybuddy.webapp.unitTest.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.paymybuddy.webapp.dto.TransactionDTO;
import com.paymybuddy.webapp.model.User;

class TransactionDTOTest {

	User testUser1 = new User(
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
	
	User testUser2 = new User(
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

	
//	TransactionDTO toTest1 = new TransactionDTO(
//			testUser1,
//            testUser2,
//            1000.0,
//            "description",
//            LocalDate.parse("2021-08-26"),
//            0.25);
//	
//	TransactionDTO toTest2 = new TransactionDTO(
//			testUser1,
//            testUser2,
//            1000.0,
//            "description",
//            LocalDate.parse("2021-08-26"),
//            0.25);
//	
	TransactionDTO toTest1 = new TransactionDTO(
			testUser1,
            testUser2,
            1000.0,
            "description");
	
	TransactionDTO toTest2 = new TransactionDTO(
			testUser1,
            testUser2,
            1000.0,
            "description");


	
	@Test
	final void testTransfer() {
		assertNotNull(toTest1.toString());
	}
		
	
	@Test
	void testHashCode() {
		assertEquals((toTest1.toString()).hashCode(), (toTest2.toString()).hashCode());
	}
	
	
	@Test
	final void testEqualsObject() {
		assertEquals(toTest1.toString(), toTest2.toString());
	}
	
	@Test
	final void testCanEqual() {
		assertEquals(toTest1.toString(), toTest2.toString());
	}
	
	@Test
	final void testPersonStringStringStringStringIntStringString() {
		assertEquals(toTest1.toString(), toTest2.toString());
	}

	@Test
	final void testIdTransaction() {
		assertEquals(toTest1.getIdTransaction(), toTest2.getIdTransaction());
	}	
	
	@Test
	final void testPayer() {
		assertEquals(toTest1.getPayer(), toTest2.getPayer());
	}
	
	@Test
	final void testSetPayer() {
		TransactionDTO transaction = new TransactionDTO();
		transaction.setPayer(testUser1);
		assertEquals(transaction.getPayer(), toTest1.getPayer());
	}
		
	@Test
	final void testBeneficiary() {
		assertEquals(toTest1.getBeneficiary(), toTest2.getBeneficiary());
	}	
	
	
	@Test
	final void testSetBeneficiary() {
		TransactionDTO transaction = new TransactionDTO();
		transaction.setBeneficiary(testUser1);
		assertEquals((transaction.getBeneficiary()).toString(), toTest1.getBeneficiary().toString());
		assertNotNull(transaction.getBeneficiary());
	}
	
	@Test
	final void testCreationDate() {
		assertEquals(toTest1.getCreationDate(), toTest2.getCreationDate());
	}

//	@Test
//	final void testSetCreationDate() {
//		TransactionDTO transaction = new TransactionDTO();
//		transaction.setCreationDate(LocalDate.parse("2021-08-26"));
//		assertEquals(transaction.getCreationDate(), toTest1.getCreationDate());
//		
//	}
	
	@Test
	final void testAmount() {
		assertEquals(toTest1.getAmount(), toTest2.getAmount());
	}	
	
	
	@Test
	final void testSetAmount() {
		TransactionDTO transaction = new TransactionDTO();
		transaction.setAmount(1000.0);
		assertEquals(transaction.getAmount(), toTest1.getAmount());
	}	
	
	@Test
	final void testDescription() {
		assertEquals(toTest1.getDescription(), toTest2.getDescription());
	}	
	
	
	@Test
	final void testSetDescription() {
		TransactionDTO transaction = new TransactionDTO();
		transaction.setDescription("description");
		assertEquals(transaction.getDescription(), toTest1.getDescription());
	}	
	
	@Test
	final void testCommision() {
		assertEquals(toTest1.getCommision(), toTest2.getCommision());
	}	
	
	
//	@Test
//	final void testSetCommision() {
//		TransactionDTO transaction = new TransactionDTO();
//		transaction.setCommision(0.25);
//		assertEquals(transaction.getCommision(), toTest1.getCommision());
//	}
	
}
