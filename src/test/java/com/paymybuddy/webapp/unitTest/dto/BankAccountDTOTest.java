package com.paymybuddy.webapp.unitTest.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.paymybuddy.webapp.dto.BankAccountDTO;
import com.paymybuddy.webapp.model.BankAccount;
import com.paymybuddy.webapp.model.User;

class BankAccountDTOTest {


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

	BankAccountDTO toTest1 = new BankAccountDTO(
			testUser1,
			"AAA AAA AAA AAA X");

	BankAccountDTO toTest2 = new BankAccountDTO(
			testUser1,
			"AAA AAA AAA AAA X");
	
	BankAccountDTO toTest3 = new BankAccountDTO(
			testUser1,
			"AAA AAA AAA AAA X");

	BankAccountDTO toTest4 = new BankAccountDTO(
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

//	@Test
//	final void testSetIdBankAccount() {
//		BankAccount bankAccount= new BankAccount();
//		bankAccount.setIdBankAccount(1);
//		assertEquals(bankAccount.getIdBankAccount(), toTest1.getIdBankAccount());
//	}
	
	@Test
	final void testUser() {
		assertEquals(toTest1.getUser(), toTest2.getUser());
	}
	
	@Test
	final void testSetUser() {
		BankAccountDTO bankAccount = new BankAccountDTO();
		bankAccount.setUser(testUser1);
		assertEquals(bankAccount.getUser(), toTest1.getUser());
		assertNotNull(toTest1.getUser());
		assertNotNull(bankAccount.getUser());
	}
	
	@Test
	final void testRib() {
		assertEquals(toTest1.getRib(), toTest2.getRib());
	}
	
	@Test
	final void testSetRib() {
		BankAccountDTO bankAccount = new BankAccountDTO();
		bankAccount.setRib("AAA AAA AAA AAA X");
		assertEquals(bankAccount.getRib(), toTest1.getRib());
		assertNotNull(bankAccount.getRib());
		assertNotNull(toTest1.getRib());
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
