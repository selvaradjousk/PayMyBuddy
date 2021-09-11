package com.paymybuddy.webapp.unitTest.util;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
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

	
	// *******************************************************************	
	
	@DisplayName("DTO to Entity - "
			+ "GIVEN DTO  "
			+ "WHEN Requested to DO"
			+ "THEN returns DO")
	@Test
    public void testToEntity() {
	BankAccountDTO dto = new BankAccountDTO(user, "testRib");
	
	BankAccountMapper mapper = new BankAccountMapper();
	BankAccount entity = mapper.toBankAccountDO(dto);
	
	assertEquals(entity.toString(), dto.toString());
	}
	
	
	// *******************************************************************	
	
	@DisplayName(" DTO to Entity null DTO Exception- "
			+ "GIVEN DTO null "
			+ "WHEN Requested to DO"
			+ "THEN returns Exception")
	@Test
    public void testToEntityNullException() {
	
	BankAccountMapper mapper = new BankAccountMapper();
	BankAccount entity = mapper.toBankAccountDO(null);
	
	assertNull(entity);
    assertThrows(NullPointerException.class, ()
     		  -> mapper.toBankAccountDO(null).getUser());
	}
	
	
	// *******************************************************************	
	
	
	@DisplayName("DO to DTO - "
			+ "GIVEN DO  "
			+ "WHEN Requested to DTO"
			+ "THEN returns DTO")
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
	
	// *******************************************************************	
	
	@DisplayName("DO to DTO null DO Exception- "
			+ "GIVEN DO null "
			+ "WHEN Requested to DTO"
			+ "THEN returns Exception")
	@Test
    public void testToDTONullException() {
	
		
		BankAccountMapper mapper = new BankAccountMapper();
		BankAccountDTO dto = mapper.toBankAccountDTO(null);
	
	assertNull(dto);
    assertThrows(NullPointerException.class, ()
     		  -> mapper.toBankAccountDTO(null).getUser());
	}
	
	
	// *******************************************************************	
	
	
}
