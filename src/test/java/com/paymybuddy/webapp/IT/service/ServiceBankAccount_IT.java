package com.paymybuddy.webapp.IT.service;

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

@DisplayName("BANK ACCOUNT SERVICE IT - DB TEST ")
@SpringBootTest
@ActiveProfiles("integration")
public class ServiceBankAccount_IT {

	@Autowired
	private IBankAccountService bankAccountService;

	@Autowired
	private IUserService userService;

	public UserMapper userMapper = new UserMapper();
	public BankAccountMapper bankAccountMapper = new BankAccountMapper();

	//******************************************************************
	
    @DisplayName("Lists of BankAccounts - "
			+ "GIVEN BankAccount list"
			+ "WHEN Requested list BankAccounts"
			+ "THEN returns expected list of bankaccounts")
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
	
    @DisplayName("Add new BankAccount - "
			+ "GIVEN BankAccount "
			+ "WHEN Requested Add new BankAccount"
			+ "THEN returns expected BankAccount added")
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
	
    @DisplayName("Delete BankAccount - "
			+ "GIVEN BankAccount "
			+ "WHEN Requested delete BankAccount"
			+ "THEN returns expected BankAccount deleted")
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