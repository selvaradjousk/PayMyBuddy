package com.paymybuddy.webapp.unitTest.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
import com.paymybuddy.webapp.exception.BalanceNotSufficientException;
import com.paymybuddy.webapp.exception.DataNotConformException;
import com.paymybuddy.webapp.model.User;
import com.paymybuddy.webapp.service.ITransactionService;
import com.paymybuddy.webapp.service.IUserService;
import com.paymybuddy.webapp.service.TransactionServiceImpl;
import com.paymybuddy.webapp.util.UserMapper;

@DisplayName("TRANSACTION SERVICE - H2 DB TEST ")
@SpringBootTest
@ActiveProfiles("test")
class TransactionServiceH2Test {

    @Autowired
    ITransactionService transactionService;
    
    @Autowired
    IUserService userService;
    
    public UserMapper userMapper = new UserMapper();
	
	   TransactionServiceImpl transactionServiceImpl;
    
    List<UserDTO> listUserDTO = new ArrayList<UserDTO>();

    
    

	// *******************************************************************
	
    
    
    
    @DisplayName("Find All Transactions (Service) - "
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
	
    
    
    
    @DisplayName("Find All Transactions By User(Service) - "
			+ "GIVEN Transactions list "
			+ "WHEN Requested find all transactions list by user"
			+ "THEN returns expected user transactions list size")	  
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
	
     
    
    
    @DisplayName("Find All Transactions By Payer Pageable(Service) - "
			+ "GIVEN Transactions list "
			+ "WHEN Requested find all transactions By Payer Pageable"
			+ "THEN returns expected  By Payer Pageable transactions list size")
      @Test
	  public void testFindAllByTransactionsByPayerPageable(){
	      //GIVEN
	      listUserDTO = userService.findAllUsers();
	      UserDTO userDTO = listUserDTO.get(1);
	      int page = 0;
	      //WHEN
	      Page<TransactionDTO> pagesTransaction = transactionService
	    		  .findAllTransactionByPayer(userDTO,  PageRequest.of(page,2));
	      //THEN
	      assertEquals(2,pagesTransaction.getContent().size());
	  }
	  
	  
    
    
		// *******************************************************************  
	  
    
    
	
	    @DisplayName("Find Last Three Transactions By User Pageable - "
				+ "GIVEN Transactions list "
				+ "WHEN Requested find Last Three Transactions By User Pageable"
				+ "THEN returns expected Last Three Transactions By User Pageable list size")
	  @Test
	  public void testLastThreeTransactionsByUserPageable(){

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
	
	    
	    
	    
	    @DisplayName("Add a new Transactions - "
				+ "GIVEN a new Transactions "
				+ "WHEN Requested add a new Transactions"
				+ "THEN returns as expected Transaction added")	  
	   @Test
	    public void testAddTransaction(){

		   //GIVEN
	        listUserDTO = userService.findAllUsers();
	        User userPayer = userMapper.toUserDO(listUserDTO.get(1));
	        User userBeneficiary = userMapper.toUserDO(listUserDTO.get(2));
	        Double wallePayertBefore = userPayer.getWalletAmount();
	        Double walleBeneficiarytBefore = userBeneficiary.getWalletAmount();
	        
	        TransactionDTO transactionDTO = new TransactionDTO();
	        transactionDTO = new TransactionDTO( userPayer, userBeneficiary, 100.0,"justLikeThat");

	        //WHEN
	        transactionDTO = transactionService.addTransaction(transactionDTO);
	        Double wallePayertAfter = userPayer.getWalletAmount();
	        Double walleBeneficiarytAfter = userBeneficiary.getWalletAmount();

	        //THEN
	        assertNotEquals(wallePayertBefore, wallePayertAfter);
	        assertEquals(  walleBeneficiarytBefore+100,  walleBeneficiarytAfter);
	    }
	   
	    
	    
	    

		// ******************************************************************* 	 
	
	    
	    
	    
	    @DisplayName("Add a new Transactions for zero amount value BalanceNotSufficientException- "
				+ "GIVEN a new Transactions "
				+ "WHEN Requested add a new Transactions for zero amount value"
				+ "THEN returns as expected throws BalanceNotSufficientException")	 	   
	   @Test
	    public void testAddTransactionForZeroAmount(){

		   //GIVEN
	        listUserDTO = userService.findAllUsers();
	        User userPayer = userMapper.toUserDO(listUserDTO.get(1));
	        User userBeneficiary = userMapper.toUserDO(listUserDTO.get(2));
	        
	      //WHEN
	        final TransactionDTO transactionDTO= new TransactionDTO(userPayer, userBeneficiary, 0,"justLikeThat");

	        //WHEN
	        assertThrows(BalanceNotSufficientException.class, ()
					-> transactionService.addTransaction(transactionDTO));

	    }   
	   
	    
	    

		// ******************************************************************* 	 
	
	    
	    
	    
	    @DisplayName("Add a new Transactions for negative amount value throws Exception- "
				+ "GIVEN a new Transactions "
				+ "WHEN Requested add a new Transactions for negative amount value"
				+ "THEN returns as expected throws BalanceNotSufficientException")	 	   
	   @Test
	    public void testAddTransactionAmountForNegative(){

		   //GIVEN
	        listUserDTO = userService.findAllUsers();
	        User userPayer = userMapper.toUserDO(listUserDTO.get(1));
	        User userBeneficiary = userMapper.toUserDO(listUserDTO.get(2));
	        
	      //WHEN
	        final TransactionDTO transactionDTO= new TransactionDTO(userPayer, userBeneficiary, -50.0,"justLikeThat");

	        //WHEN
	        assertThrows(BalanceNotSufficientException.class, ()
					-> transactionService.addTransaction(transactionDTO));

	    }  
	   
	    
	    
	    
		// ******************************************************************* 	 
	
	    
	    
	    
	    @DisplayName("Add a new Transactions for payer null throws - DataNotConformException - "
				+ "GIVEN a new Transactions "
				+ "WHEN Requested add a new Transactions payer null"
				+ "THEN returns as expected throws DataNotConformException")	 	   
	   @Test
	    public void testAddTransactionForPayerNull(){

		   //GIVEN
	        listUserDTO = userService.findAllUsers();
	        User userBeneficiary = userMapper.toUserDO(listUserDTO.get(2));
	        
	      //WHEN
	        final TransactionDTO transactionDTO= new TransactionDTO(null, userBeneficiary, 10.0,"justLikeThat");

	        //WHEN
	        assertThrows(DataNotConformException.class, ()
					-> transactionService.addTransaction(transactionDTO));

	    }   	   
	   
	    
	    
	   
		// ******************************************************************* 	 
	 	   
	    
	    
	    
	    @DisplayName("Add a new Transactions for beneficiary null throws - DataNotConformException - "
				+ "GIVEN a new Transactions "
				+ "WHEN Requested add a new Transactions beneficiary null"
				+ "THEN returns as expected throws DataNotConformException")	   
	   @Test
	    public void testAddTransactionForBeneficiaryNull(){

		   //GIVEN
	        listUserDTO = userService.findAllUsers();
	        User userPayer = userMapper.toUserDO(listUserDTO.get(1));
	        
	      //WHEN
	        final TransactionDTO transactionDTO= new TransactionDTO(userPayer, null, 10.0,"justLikeThat");

	        //WHEN
	        assertThrows(DataNotConformException.class, ()
					-> transactionService.addTransaction(transactionDTO));

	    }   	   
	   
	   
	    
	    
		// ******************************************************************* 
	   
	    
	    
	    
    	@DisplayName("Add a new Transactions for description null throws - DataNotConformException - "
    			+ "GIVEN a new Transactions "
    			+ "WHEN Requested add a new Transaction description null"
    			+ "THEN returns as expected throws DataNotConformException")		   
	   @Test
	    public void testAddTransactionForDescriptionNull(){

		   //GIVEN
	        listUserDTO = userService.findAllUsers();
	        User userPayer = userMapper.toUserDO(listUserDTO.get(1));
	        listUserDTO = userService.findAllUsers();
	        User userBeneficiary = userMapper.toUserDO(listUserDTO.get(2));
	        
	      //WHEN
	        final TransactionDTO transactionDTO= new TransactionDTO(userPayer, userBeneficiary, 0,"");

	        //WHEN
	        assertThrows(DataNotConformException.class, ()
					-> transactionService.addTransaction(transactionDTO));

	    }   	   
	   
	   
		// ******************************************************************* 

}
