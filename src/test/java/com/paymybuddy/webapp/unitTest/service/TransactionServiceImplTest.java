package com.paymybuddy.webapp.unitTest.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.paymybuddy.webapp.dto.TransactionDTO;
import com.paymybuddy.webapp.dto.UserDTO;
import com.paymybuddy.webapp.exception.DataNotConformException;
import com.paymybuddy.webapp.model.Transaction;
import com.paymybuddy.webapp.model.User;
import com.paymybuddy.webapp.repository.TransactionRepository;
import com.paymybuddy.webapp.repository.UserRepository;
import com.paymybuddy.webapp.service.TransactionServiceImpl;
import com.paymybuddy.webapp.service.UserServiceImpl;
import com.paymybuddy.webapp.util.TransactionMapper;
import com.paymybuddy.webapp.util.UserMapper;

@DisplayName("TRANSACTION SERVICE - UNIT TEST ")
@ExtendWith(SpringExtension.class)
class TransactionServiceImplTest {

	@InjectMocks
    TransactionServiceImpl transactionService;
    
	@Mock
    UserServiceImpl userService;
	
	@Mock
	TransactionRepository transactionRepository;
	
    @Mock
    private TransactionMapper transactionMapper;
    
    @Mock
    UserRepository userRepository;
    
    @Mock
    private UserMapper userMapper;
    
    @Mock
    private static User testUser, testUser1;
    
    private static TransactionDTO testTransactionDTO;

    private static Transaction testTransaction, testTransaction1;
    
    private static List<Transaction> transactionList;
    
    private static UserDTO testUserDTO;
    

    
    
    @BeforeEach
    public void setUp() {
    	
	testUser = new User(
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
	
	testUser1 = new User(
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
	

    testUserDTO = new UserDTO(
			"testUserName",
            "testFirstName",
			"myEmail@email.com",
			"password");
   
	
	testTransactionDTO = new TransactionDTO(
			testUser,
			testUser1,
			1000.0,
			"testDescription");

	
	testTransaction = new Transaction(
			10,
			testUser,
			testUser1,
			1000.0,
			"testDescription",
			LocalDate.parse("2021-08-26"),
			0.25);
	
	testTransaction1 = new Transaction(
			10,
			testUser,
			testUser1,
			1000.0,
			"testDescription",
			LocalDate.parse("2021-08-26"),
			0.25);
    
	
	transactionList = Arrays.asList(testTransaction, testTransaction1);

	}
    
    
    
	
	// *******************************************************************
	
    
    
    
    @DisplayName("Find All Transactions (Service) - "
			+ "GIVEN Transactions list "
			+ "WHEN Requested find all transactions list"
			+ "THEN returns expected transactions list size")
    @Test
    public void testFindAllTransactions(){

		//GIVEN
    	when(transactionRepository
    			.findAll())
    	.thenReturn(transactionList);
    	
    	when(transactionMapper
    			.toTransactionDTO(any(Transaction.class)))
    	.thenReturn(testTransactionDTO);
 		
        //WHEN
        List<TransactionDTO> listTransactionDTO = transactionService
        		.findAllTransactions();

        //THEN
        assertEquals(2, listTransactionDTO.size());
        verify(transactionRepository, times(1)).findAll();
        verify(transactionMapper, times(2)).toTransactionDTO(any(Transaction.class));
    }
    
    
    
	
	// *************This method is a step for pagable template*************	
	
    
    
    
    @DisplayName("Find All Transactions By User(Service) - "
			+ "GIVEN Transactions list "
			+ "WHEN Requested find all transactions list by user"
			+ "THEN returns expected user transactions list size")
    @Test
	  public void testfindAllTransactionByUser(){

		//GIVEN
    	when(transactionRepository
    			.findAllTransactionByPayer(testUser1))
    	.thenReturn(transactionList);
    	
    	when(transactionMapper
    			.toTransactionDTO(any(Transaction.class)))
    	.thenReturn(testTransactionDTO);
 		
        //WHEN
        List<TransactionDTO> listTransactions = transactionService
        		. findAllTransactionByPayer(testUserDTO);
 	      //THEN
	      assertNotNull(listTransactions);
	  }
	  	
		// ******************************************************************* 	 
	
    
    

	  @DisplayName("User Transaction Wallet Operation Condition False- "
				+ "GIVEN transactionDTO "
				+ "WHEN request walletOperation result false"
				+ "THEN returns Exception")
	  @Test
	  public void testWalletOperationFalse(){
	
		  
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
		  
			TransactionDTO dto = new TransactionDTO(
					user,
					user,
					1000000.0,
					"testDescription");
		  
			boolean result = transactionService.walletOperation(dto);
		  

			assertEquals(false, result);		
	  }  


	// *******************************************************************

	   
	    

		  @DisplayName("User checkTransactionAmountNotZeroValue ConditionException- "
					+ "GIVEN transactionDTO "
					+ "WHEN request checkTransactionAmountNotZeroValue zero amount"
					+ "THEN returns Exception")
		  @Test
		  public void testcheckTransactionAmountNotZeroValueZeroAmount(){
		
			  
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
			  
				TransactionDTO dto = new TransactionDTO(
						user,
						user,
						0,
						"testDescription");

			  
			    assertThrows(DataNotConformException.class, ()
			     		  -> transactionService.walletOperation(dto));	
		  }  


		// *******************************************************************

		    

			  @DisplayName("User checkForPayerById Null Condition Exception- "
						+ "GIVEN transactionDTO "
						+ "WHEN request checkForPayerById null "
						+ "THEN returns Exception")
			  @Test
			  public void testcheckForPayerByIdNull(){
			
				  
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
				  
					TransactionDTO dto = new TransactionDTO(
							null,
							user,
							100.0,
							"testDescription");

				  
				    assertThrows(NullPointerException.class, ()
				     		  -> userService.findUserById(dto
				      				.getPayer().getId()));
				    
				    assertThrows(NullPointerException.class, ()
				     		  -> transactionService.checkForPayerById(dto));
			  }  


			// *******************************************************************


			    

			  @DisplayName("User checkForPayerById Contact Null Condition Exception- "
						+ "GIVEN transactionDTO "
						+ "WHEN request checkForPayerById contact null "
						+ "THEN returns Exception")
			  @Test
			  public void testcheckForPayerByIdContactNull(){
			
				  
					User user = new User(
				    		0,
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
				  
					TransactionDTO dto = new TransactionDTO(
							null,
							null,
							100.0,
							"testDescription");

				  
				    assertThrows(NullPointerException.class, ()
				     		  -> userService.findUserById(dto
				      				.getPayer().getId()));	
				    
				    assertThrows(NullPointerException.class, ()
				     		  -> transactionService.checkForPayerById(dto));	
			  }  


			// *******************************************************************

		        

			    

			  @DisplayName("User checkDescriptionLengthGreaterThan30Characters Condition Exception- "
						+ "GIVEN transactionDTO "
						+ "WHEN request checkDescriptionLengthGreaterThan30Characters "
						+ "THEN returns Exception")
			  @Test
			  public void testcheckDescriptionLengthGreaterThan30Characters(){
			
				  
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
				  
					TransactionDTO dto = new TransactionDTO(
							user,
							user,
							100.0,
							"DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD");

				  
				    assertThrows(DataNotConformException.class, ()
				     		  -> transactionService.checkDescriptionLengthGreaterThan30Characters(dto));	
			  }  


			// *******************************************************************

		        
}
