package com.paymybuddy.webapp.unitTest.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.paymybuddy.webapp.dto.TransferDTO;
import com.paymybuddy.webapp.model.User;

class TransferDTOTest {
	

	User testUser1 = new User(
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
	
	User testUser2 = new User(
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

	
	TransferDTO toTest1 = new TransferDTO(
			1,
            "test Rib",
            LocalDate.parse("2019-12-31"),
            1000.0,
            "CREDIT",
			testUser1);
	
	TransferDTO toTest2 = new TransferDTO(
			1, 
            "test Rib",
            LocalDate.parse("2019-12-31"),
            1000.0,
            "CREDIT",
			testUser1);
	
	@Test
	final void testCreationDate() {
		assertEquals(toTest1.getCreateDate(), toTest2.getCreateDate());
	}
	
	@Test
	final void testTransfer() {
		assertNotNull(toTest1.toString());
	}
	
	@Test
	final void testGetUser() {
		assertEquals(toTest1.getUser(), toTest2.getUser());
	}
	
	@Test
	final void testUser() {
		assertNotNull(toTest1.getUser());
		assertNotNull(toTest2.getUser());
	}
	
	@Test
	final void testSetPayer() {
		TransferDTO transfer = new TransferDTO();
		transfer.setUser(testUser1);
		assertEquals(transfer.getUser(), toTest1.getUser());
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
}
