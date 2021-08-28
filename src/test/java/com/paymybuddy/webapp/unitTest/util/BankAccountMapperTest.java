package com.paymybuddy.webapp.unitTest.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.paymybuddy.webapp.dto.BankAccountDTO;
import com.paymybuddy.webapp.model.BankAccount;
import com.paymybuddy.webapp.model.User;
import com.paymybuddy.webapp.util.BankAccountMapper;

class BankAccountMapperTest {


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

	@Test
    public void testToEntity() {
	BankAccountDTO dto = new BankAccountDTO(user, "testRib");
	
	BankAccountMapper mapper = new BankAccountMapper();
	BankAccount entity = mapper.toBankAccountDO(dto);
	
	assertEquals(entity.toString(), dto.toString());
	}
	
	@Test
    public void testToDTO() {
		
	BankAccount entity = new BankAccount(
			100,
            user,
            "testRib");
	
	BankAccountMapper mapper = new BankAccountMapper();
	BankAccountDTO dto = mapper.toBankAccountDTO(entity);
	
	assertEquals(dto.getUser(), entity.getUser());
	assertEquals(dto.getRib(), entity.getRib());
	}

}
