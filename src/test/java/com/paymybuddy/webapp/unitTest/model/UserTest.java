package com.paymybuddy.webapp.unitTest.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.paymybuddy.webapp.dto.UserDTO;

@DisplayName("User Entity / DO - Unit Tests")
class UserTest {

	UserDTO toTest1 = new UserDTO(
			"userName",
			"firstName",
			"myEmail",
			"password");
	
	UserDTO toTest2 = new UserDTO(
			"userName",
			"firstName",
			"myEmail",
			"password");
	
//	UserDTO toTest2 = new UserDTO(
//            "testUserName",
//            "testFirstName",
//            "myEmail",
//            "myPassword");
	
	
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
	final void testUserId() {
		assertEquals(toTest1.getId(), toTest2.getId());
	}
	
	@Test
	final void testUserName() {
		assertEquals(toTest1.getUserName(), toTest2.getUserName());
	}

	@Test
	final void testFirstName() {
		assertEquals(toTest1.getFirstName(), toTest2.getFirstName());
	}

	@Test
	final void testLastName() {
		assertEquals(toTest1.getLastName(), toTest2.getLastName());
	}

	@Test
	final void testEmail() {
		assertEquals(toTest1.getEmail(), toTest2.getEmail());
	}
	

	@Test
	final void testPassword() {
		assertEquals(toTest1.getPassword(), toTest2.getPassword());
	}


	@Test
	final void testCreationDate() {
		assertEquals(toTest1.getCreationDate(), toTest2.getCreationDate());
	}
	
	@Test
	final void testModificationDate() {
		assertEquals(toTest1.getModificationDate(), toTest2.getModificationDate());
	}
	
	@Test
	final void testRoles() {
		assertEquals(toTest1.getRoles(), toTest2.getRoles());
	}
	@Test
	final void testWalletAmount() {
		assertEquals(toTest1.getWalletAmount(), toTest2.getWalletAmount());
	}

}
