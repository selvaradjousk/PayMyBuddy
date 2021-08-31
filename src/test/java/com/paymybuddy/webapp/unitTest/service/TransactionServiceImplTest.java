package com.paymybuddy.webapp.unitTest.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

import com.paymybuddy.webapp.dto.TransactionDTO;
import com.paymybuddy.webapp.dto.UserDTO;
import com.paymybuddy.webapp.model.User;
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
	  	

	  
	  	// *******************************************************************
	  
	  @Test
	  public void testFindAllByTransactionsByPayerPageable(){
	      //GIVEN
	      listUserDTO = userService.findAllUsers();
	      UserDTO userDTO = listUserDTO.get(1);
	      int page = 0;
	      //WHEN
	      Page<TransactionDTO> pagesTransaction = transactionService.findAllTransactionByPayer(userDTO,  PageRequest.of(page,2));
	      //THEN
	      assertEquals(2,pagesTransaction.getContent().size());
	  }
	  
	  
		// *******************************************************************  
	  
	  
	  @Test
	  public void TestLastThreeTransactionsByUserPageable(){

	      //GIVEN
	      listUserDTO = userService.findAllUsers();
	      UserDTO userDTO = listUserDTO.get(1);
	      int page = 0;

	      //WHEN
	      Page<TransactionDTO> pagesTransactionDTO = transactionService
	    		  .lastThreeTransactions(userDTO,  PageRequest.of(page,2));

	      //THEN
	      assertEquals(2, pagesTransactionDTO.getContent().size());
	      assertEquals(3, pagesTransactionDTO.getTotalElements());
	  }
	  
		// ******************************************************************* 	 
	  
	   @Test
	    public void testAddTransaction(){

		   //GIVEN
	        listUserDTO = userService.findAllUsers();
	        User userPayer = userMapper.toUserDO(listUserDTO.get(1));
	        User userBeneficiary = userMapper.toUserDO(listUserDTO.get(2));
	        Double wallePayertBefore = userPayer.getWalletAmount();
	        Double walleBeneficiarytBefore = userBeneficiary.getWalletAmount();
	        TransactionDTO transactionDTO = new TransactionDTO( userPayer, userBeneficiary, 100.0,"justLikeThat");

	        //WHEN
	        transactionDTO = transactionService.addTransaction(transactionDTO);
	        Double wallePayertAfter = userPayer.getWalletAmount();
	        Double walleBeneficiarytAfter = userBeneficiary.getWalletAmount();

	        //THEN
	        assertNotEquals(wallePayertBefore, wallePayertAfter);
	        assertEquals(  walleBeneficiarytBefore+100,  walleBeneficiarytAfter);
	    }
}
