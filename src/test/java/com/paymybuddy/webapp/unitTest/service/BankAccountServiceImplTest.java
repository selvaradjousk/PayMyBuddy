package com.paymybuddy.webapp.unitTest.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.paymybuddy.webapp.dto.BankAccountDTO;
import com.paymybuddy.webapp.dto.UserDTO;
import com.paymybuddy.webapp.model.BankAccount;
import com.paymybuddy.webapp.model.User;
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
		assertEquals(2, listBankAccount.size());
	}
	//******************************************************************

	@Test
	public void testAddAccount() {
		
		// GIVEN
		UserDTO userDTO = userService
				.findUserByEmail("testemail2@email.com");
		
		User user = userMapper.toUserDO(userDTO);
		String rib = "fr 1111 1111 1111 1111";
		
		// WHEN
		BankAccount newBankAccount = new BankAccount(user, rib);
		
		BankAccountDTO newBankAccountDTO = bankAccountMapper
				.toBankAccountDTO(newBankAccount);
		
		BankAccountDTO bankAccountAddDTO = bankAccountService
				.addBankAccount(rib, userDTO);
		
		// THEN
		
		assertNotNull(bankAccountAddDTO);
		assertEquals(newBankAccountDTO.getRib(), bankAccountAddDTO.getRib());

	}
	//******************************************************************

	@Test
	public void testDeleteAccount() {

		// GIVEN
		UserDTO userDTO = userService
				.findUserByEmail("testemail2@email.com");
		
		// WHEN
		List<BankAccountDTO> listBankAccountDTOBefore = bankAccountService
				.findBankAccountByUser(userDTO);
		
		bankAccountService.deleteBankAccount(listBankAccountDTOBefore
				.get(1).getIdBankAccount());
		
		List<BankAccountDTO> listBankAccountDTOAfter = bankAccountService
				.findBankAccountByUser(userDTO);
		
		// THEN
		assertNotEquals(listBankAccountDTOBefore.size(), listBankAccountDTOAfter.size());
		assertEquals(listBankAccountDTOAfter.size() + 1, listBankAccountDTOBefore.size());
	}
	//******************************************************************
	
}