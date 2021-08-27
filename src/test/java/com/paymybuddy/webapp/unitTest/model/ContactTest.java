package com.paymybuddy.webapp.unitTest.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.paymybuddy.webapp.model.Contact;
import com.paymybuddy.webapp.model.Transaction;
import com.paymybuddy.webapp.model.User;

class ContactTest {

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
	
	Contact toTest1 = new Contact(
			1,
            LocalDate.parse("2019-12-31"),
			testUser1,
            testUser2);

	Contact toTest2 = new Contact(
			1,
            LocalDate.parse("2019-12-31"),
			testUser1,
            testUser2);
	
	
	
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
	final void testIdContact() {
		assertEquals(toTest1.getIdContact(), toTest2.getIdContact());
	}	

	@Test
	final void testSetIdContact() {
		Contact transaction = new Contact();
		transaction.setIdContact(1);
		assertEquals(transaction.getIdContact(), toTest1.getIdContact());
	}
	
	@Test
	final void testPayer() {
		assertEquals(toTest1.getPayer(), toTest2.getPayer());
	}
	
	@Test
	final void testSetPayer() {
		Contact transaction = new Contact();
		transaction.setPayer(testUser1);
		assertEquals(transaction.getPayer(), toTest1.getPayer());
	}
	
	@Test
	final void testContact() {
		assertEquals(toTest1.getContact(), toTest2.getContact());
	}
	
	@Test
	final void testSetContact() {
		Contact transaction = new Contact();
		transaction.setContact(testUser1);
		assertEquals(transaction.getContact().toString(), toTest1.getContact().toString());
	}
	
	@Test
	final void testCreationDate() {
		assertEquals(toTest1.getCreationDate(), toTest2.getCreationDate());
	}

	@Test
	final void testSetCreationDate() {
		Transaction transaction = new Transaction();
		transaction.setCreationDate(LocalDate.parse("2019-12-31"));
		assertEquals(transaction.getCreationDate(), toTest1.getCreationDate());
	}	
	
}
