package com.paymybuddy.webapp.unitTest.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
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
public class BankAccountServiceH2Test {

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

    @DisplayName("Lists of BankAccounts user invalid - "
			+ "GIVEN BankAccount list user invalid"
			+ "WHEN Requested list BankAccounts"
			+ "THEN returns expected throw exception")
	@Test
	public void testFindBankAccountByUserEmailInvalid() {


		// GIVEN // WHEN // THEN
	    assertThrows(NullPointerException.class, ()
	     		  -> bankAccountService
					.findBankAccountByUser(userService
							.findUserByEmail("testemail200@email.com")));

	}
    
    
	//******************************************************************

    @DisplayName("Lists of BankAccounts user email invalid - "
			+ "GIVEN BankAccount list user email invalid"
			+ "WHEN Requested list BankAccounts"
			+ "THEN returns expected throw exception")
	@Test
	public void testFindBankAccountByUserEmailNullInvalid() {


		// GIVEN // WHEN // THEN
	    assertThrows(NullPointerException.class, ()
	     		  -> bankAccountService
					.findBankAccountByUser(userService.findUserByEmail(null)));

	}
    
	//******************************************************************

    @DisplayName("Lists of BankAccounts user email Empty - "
			+ "GIVEN BankAccount list user invalid"
			+ "WHEN Requested list BankAccounts"
			+ "THEN returns expected throw exception")
	@Test
	public void testFindBankAccountByUserEmailEmpty() {


		// GIVEN // WHEN // THEN
	    assertThrows(NullPointerException.class, ()
	     		  -> bankAccountService
					.findBankAccountByUser(userService.findUserByEmail("")));

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

	
    @DisplayName("Add new BankAccount Rib Null - "
			+ "GIVEN BankAccount "
			+ "WHEN Requested Add new BankAccount Rib Null"
			+ "THEN returns expected throws exception")
	@Test
	public void testAddAccountRibNull() {
		
		// GIVEN
		UserDTO userDTO = userService
				.findUserByEmail("testemail2@email.com");
		

		// GIVEN // WHEN // THEN
	    assertThrows(DataIntegrityViolationException.class, ()
	     		  -> bankAccountService
					.addBankAccount(null, userDTO));
	}
    
	//******************************************************************
	

	
    @DisplayName("Add new BankAccount User Null - "
			+ "GIVEN BankAccount "
			+ "WHEN Requested Add new BankAccount User Null"
			+ "THEN returns expected throws exception")
	@Test
	public void testAddAccountUserNull() {
		

		// GIVEN // WHEN // THEN
	    assertThrows(DataIntegrityViolationException.class, ()
	     		  -> bankAccountService
					.addBankAccount("DDDDDDDDDDDDDDD", null));
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
	
	
    @DisplayName("Delete BankAccount - Email Invalid- "
			+ "GIVEN BankAccount invalid "
			+ "WHEN Requested delete BankAccount"
			+ "THEN returns expected throws exception")
	@Test
	public void testDeleteAccountWithEmailNonExist() {

		// GIVEN // WHEN // THEN
	    assertThrows(NullPointerException.class, ()
	     		  -> bankAccountService
					.findBankAccountByUser(userService
							.findUserByEmail("testemail20@email.com")));

	}
	//******************************************************************
	
	
    @DisplayName("Delete BankAccount - Email Empty - "
			+ "GIVEN BankAccount empty "
			+ "WHEN Requested delete BankAccount"
			+ "THEN returns expected throws exception")
	@Test
	public void testDeleteAccountWithEmailEmpty() {

		// GIVEN // WHEN // THEN
	    assertThrows(NullPointerException.class, ()
	     		  -> bankAccountService
					.findBankAccountByUser(userService
							.findUserByEmail("")));

	}
	//******************************************************************
     

	
    @DisplayName("Delete BankAccount - Email Null - "
			+ "GIVEN BankAccount Null "
			+ "WHEN Requested delete BankAccount"
			+ "THEN returns expected throws exception")
	@Test
	public void testDeleteAccountWithEmailNull() {

		// GIVEN // WHEN // THEN
	    assertThrows(NullPointerException.class, ()
	     		  -> bankAccountService
					.findBankAccountByUser(userService
							.findUserByEmail(null)));

	}
	//******************************************************************
      
}