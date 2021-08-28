package com.paymybuddy.webapp.unitTest.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.paymybuddy.webapp.model.BankAccount;
import com.paymybuddy.webapp.model.User;

class BankAccountTest {


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

	BankAccount toTest1 = new BankAccount(
			1,
			testUser1,
			"AAA AAA AAA AAA X");

	BankAccount toTest2 = new BankAccount(
			1,
			testUser1,
			"AAA AAA AAA AAA X");
	
	BankAccount toTest3 = new BankAccount(
			testUser1,
			"AAA AAA AAA AAA X");

	BankAccount toTest4 = new BankAccount(
			testUser1,
			"AAA AAA AAA AAA X");
	
	
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
	final void testIdBankAccount() {
		assertEquals(toTest1.getIdBankAccount(), toTest2.getIdBankAccount());
	}	

	@Test
	final void testSetIdBankAccount() {
		BankAccount bankAccount= new BankAccount();
		bankAccount.setIdBankAccount(1);
		assertEquals(bankAccount.getIdBankAccount(), toTest1.getIdBankAccount());
	}
	
	@Test
	final void testUser() {
		assertEquals(toTest1.getUser(), toTest2.getUser());
	}
	
	@Test
	final void testSetUser() {
		BankAccount bankAccount = new BankAccount();
		bankAccount.setUser(testUser1);
		assertEquals(bankAccount.getUser(), toTest1.getUser());
	}
	
	@Test
	final void testRib() {
		assertEquals(toTest1.getRib(), toTest2.getRib());
	}
	
	@Test
	final void testSetRib() {
		BankAccount bankAccount = new BankAccount();
		bankAccount.setRib("AAA AAA AAA AAA X");
		assertEquals(bankAccount.getRib(), toTest1.getRib());
	}
	
	@Test
	final void testBankAccountCompleteWithId() {
		assertEquals(toTest1.toString(), toTest2.toString());
	}
	
	@Test
	final void testBankAccountCWithoutId() {
		assertEquals(toTest3.toString(), toTest4.toString());
	}	
	
	
}
