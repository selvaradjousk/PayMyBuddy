package com.paymybuddy.webapp.unitTest.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.paymybuddy.webapp.dto.BankAccountDTO;
import com.paymybuddy.webapp.dto.UserDTO;
import com.paymybuddy.webapp.service.IBankAccountService;
import com.paymybuddy.webapp.service.IUserService;
import com.paymybuddy.webapp.util.BankAccountMapper;
import com.paymybuddy.webapp.util.UserMapper;

@DisplayName("BANK ACCOUNT SERVICE - H2 DB TEST ")
@SpringBootTest
@ActiveProfiles("test")
public class BankAccountServiceImplTest {

	@Autowired
	private IBankAccountService bankAccountService;

	@Autowired
	private IUserService userService;

	public UserMapper userMapper = new UserMapper();
	public BankAccountMapper bankAccountMapper = new BankAccountMapper();

	//******************************************************************

	@Test
	public void testFindBankAccountByUser() {

		// GIVEN
		UserDTO userDTO = userService
				.findUserByEmail("testemail2@email.com");

		// WHEN
		List<BankAccountDTO> listBankAccount = bankAccountService
				.findBankAccountByUser(userDTO);

		// THEN
		assertNotNull(listBankAccount);
		assertEquals(1, listBankAccount.size());
	}
	//******************************************************************
	
}