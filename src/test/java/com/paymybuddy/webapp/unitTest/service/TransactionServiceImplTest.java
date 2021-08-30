package com.paymybuddy.webapp.unitTest.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.paymybuddy.webapp.dto.TransactionDTO;
import com.paymybuddy.webapp.dto.UserDTO;
import com.paymybuddy.webapp.service.ITransactionService;
import com.paymybuddy.webapp.service.IUserService;
import com.paymybuddy.webapp.util.UserMapper;

@DisplayName("TRANSACTION SERVICE - H2 DB TEST ")
@SpringBootTest
@ActiveProfiles("test")
class TransactionServiceImplTest {

    @Autowired
    ITransactionService transactionService;
    
    @Autowired
    IUserService userService;
    
    public UserMapper userMapper = new UserMapper();
	
    
    List<UserDTO> listUserDTO = new ArrayList<UserDTO>();
	
	// *******************************************************************
	@DisplayName("Find Transaction All Transactions (Service) - "
			+ "GIVEN Transactions list "
			+ "WHEN Requested find all transactions list"
			+ "THEN returns expected transactions list size")
    @Test
    public void testFindAllTransactions(){

		//GIVEN
		// list of transactions populated in the H2 DB dataset
		
        //WHEN
        List<TransactionDTO> listTransactionDTO = transactionService
        		.findAllTransactions();

        //THEN
        assertEquals(10, listTransactionDTO.size());
    }
	
	// *************This method is a step for pagable template*************	
	  @Test
	  public void testfindAllTransactionByUser(){

	  	//GIVEN
	      listUserDTO = userService.findAllUsers();
	      UserDTO userDTO = listUserDTO.get(1);

	      //WHEN
	      List<TransactionDTO> listTransactions = transactionService
	    		  .findAllTransactionByPayer(userDTO);
	      //THEN
	      assertEquals(3,listTransactions.size());
	  }
	  	

}
