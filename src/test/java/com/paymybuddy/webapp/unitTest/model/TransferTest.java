package com.paymybuddy.webapp.unitTest.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.paymybuddy.webapp.model.Transfer;
import com.paymybuddy.webapp.model.User;

class TransferTest {
	

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

	
	Transfer toTest1 = new Transfer(
            "test Rib",
            LocalDate.parse("2021-08-26"),
            1000.0,
            "CREDIT",
			testUser1);
	
	Transfer toTest2 = new Transfer(
            "test Rib",
            LocalDate.parse("2021-08-26"),
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
	
}
