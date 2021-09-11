package com.paymybuddy.webapp.unitTest.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.paymybuddy.webapp.dto.TransferDTO;
import com.paymybuddy.webapp.dto.UserDTO;
import com.paymybuddy.webapp.exception.DataNotFoundException;
import com.paymybuddy.webapp.model.Transfer;
import com.paymybuddy.webapp.model.User;
import com.paymybuddy.webapp.repository.TransferRepository;
import com.paymybuddy.webapp.repository.UserRepository;
import com.paymybuddy.webapp.service.TransferServiceImpl;
import com.paymybuddy.webapp.service.UserServiceImpl;
import com.paymybuddy.webapp.util.TransferMapper;
import com.paymybuddy.webapp.util.UserMapper;

@DisplayName("TRANSFER SERVICE - UNIT TEST ")
@ExtendWith(SpringExtension.class)
class TransferServiceImplTest {

	@InjectMocks
    TransferServiceImpl transferService;
    
    @Mock
    UserServiceImpl userService;
    
    @Mock
    private TransferRepository transferRepository;
    
    @Mock
    private UserRepository userRepository;
    
    @Mock
    private TransferMapper transferMapper;
     
    @Mock
    public UserMapper userMapper;;
    
    List<UserDTO> listUserDTO = new ArrayList<UserDTO>();
    
    List<Transfer> transferList;
    
    List<User> userList;
    
    
    User user, user1, user2, userBeneficiary;
    
    UserDTO userDto, userDto1, userDto2;
    
    Transfer entity1, entity2, entity3;
    
    TransferDTO dto1, dto2, dto3, transferrDTO;
    
    @BeforeEach
    public void setUp() {
	user = new User(
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
	
	user1 = new User(
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
	
	user2 = new User(
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
	
	userBeneficiary = new User(
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
	
	userDto = new UserDTO(
			"testUserName",
	            "testFirstName",
				"myEmail",
				"password");
	
	userDto1 = new UserDTO(
			"testUserName",
	            "testFirstName",
				"myEmail",
				"password");
    
	userDto2 = new UserDTO(
			"testUserName",
	            "testFirstName",
				"myEmail",
				"password");
    
    
    
	dto1 = new TransferDTO(
			100,
			"testRib1",
			LocalDate.parse("2021-08-26"),
			1000.0,
			"CREDIT",
			user);
	
	dto2 = new TransferDTO(
			100,
			"testRib1",
			LocalDate.parse("2021-08-26"),
			1000.0,
			"CREDIT",
			user);
	
	dto3 = new TransferDTO(
			100,
			"testRib1",
			LocalDate.parse("2021-08-26"),
			1000.0,
			"CREDIT",
			user);
    
    
	entity1 = new Transfer(
			"testRib",
			LocalDate.parse("2021-08-26"),
			1000.0,
			"CREDIT",
			user);
	
	entity2 = new Transfer(
			"testRib",
			LocalDate.parse("2021-08-26"),
			1000.0,
			"CREDIT",
			user);
	
	entity3 = new Transfer(
			"testRib",
			LocalDate.parse("2021-08-26"),
			1000.0,
			"CREDIT",
			user);
	
	transferList = Arrays.asList(entity1, entity2, entity3);
	
	listUserDTO = Arrays.asList(userDto, userDto1, userDto2); 
	
	userList = Arrays.asList(user, user1, user2); 
	
}
    
 
	
	// *******************************************************************

    @DisplayName("Find Transfer All Transfers (Service) - "
			+ "GIVEN Transfers list "
			+ "WHEN Requested find all transfers list"
			+ "THEN returns expected transfers list size")
    @Test
    public void testFindAllTransfers(){

		//GIVEN
		// list of transfers populated in the H2 DB dataset
        when(transferRepository
        		.findAll())
        .thenReturn(transferList);

        //WHEN
        List<TransferDTO> listTransferDTO = transferService
        		.findAllTransfers();

        //THEN
        assertEquals(3, listTransferDTO.size());
    }
	

	
	
	// *************This method is a step for pagable template*************	

	  @DisplayName("Find Transfer All Transfers by User - "
				+ "GIVEN Transfers list "
				+ "WHEN Requested find all transfers list by User"
				+ "THEN returns expected transfers list size by User")
      @Test
	  public void testfindAllTransferByUser(){
	
		  transferList = Arrays.asList(entity1, entity2, entity3);
		  //GIVEN
		  
		  when(userMapper
				  .toUserDO(any(UserDTO.class)))
		  .thenReturn(user);
		  
	      when(transferRepository
	    		  .findAllByUser(any(User.class)))
	      .thenReturn(transferList);
	
	      when(userMapper
	    		  .toUserDTO(any(User.class)))
	      .thenReturn(userDto);
	      
	      // WHEN
	      List<TransferDTO> listOfTransfersDTO = transferService.findAllTransferByUser(userDto);
	 
	      //THEN
	      assertEquals(transferList.size(), listOfTransfersDTO.size());
	      verify(userMapper, times(1)).toUserDO(any(UserDTO.class));
	      verify(transferRepository, times(1)).findAllByUser(any(User.class));
  }
  
  
	
	// *******************************************************************

	  @DisplayName("Find Transfer All Transfers by User Credit Type - "
				+ "GIVEN Transfers list "
				+ "WHEN Requested find all transfers list by User Credit Type"
				+ "THEN returns expected transfers list size by User Credit Type")
	  @Test
	  public void testfindAllTransferByUserCreditType(){
	
		  transferList = Arrays.asList(entity1, entity2, entity3);
		  //GIVEN
		  
		  when(userMapper
				  .toUserDO(any(UserDTO.class)))
		  .thenReturn(user);
		  
	      when(transferRepository
	    		  .findAllByUserTypeCredit(any(User.class)))
	      .thenReturn(transferList);
	
	      when(userMapper
	    		  .toUserDTO(any(User.class)))
	      .thenReturn(userDto);
	      
	      // WHEN
	      List<TransferDTO> listOfTransfersDTO = transferService
	    		  .findAllByUserTypeCredit(userDto);
	 
	      //THEN
	      assertEquals(transferList.size(), listOfTransfersDTO.size());
	      verify(userMapper, times(1)).toUserDO(any(UserDTO.class));
	      verify(transferRepository, times(1)).findAllByUserTypeCredit(any(User.class));
	  
  }
	
  
  	// *******************************************************************

	  @DisplayName("Find Transfer All Transfers by User Debit Type - "
				+ "GIVEN Transfers list "
				+ "WHEN Requested find all transfers list by User Debit Type"
				+ "THEN returns expected transfers list size by User Debit Type")
	  @Test
	  public void testfindAllTransferByUserDebitType(){
	
	
		  transferList = Arrays.asList(entity1, entity2, entity3);
		  //GIVEN
		  
		  when(userMapper
				  .toUserDO(any(UserDTO.class)))
		  .thenReturn(user);
		  
	      when(transferRepository
	    		  .findAllByUserTypeDebit(any(User.class)))
	      .thenReturn(transferList);
	
	      when(userMapper
	    		  .toUserDTO(any(User.class)))
	      .thenReturn(userDto);
	      
	      // WHEN
	      List<TransferDTO> listOfTransfersDTO = transferService
	    		  .findAllByUserTypeCredit(userDto);
	 
	      //THEN
	      assertEquals(0, listOfTransfersDTO.size());
	      
	      verify(userMapper, times(1)).toUserDO(any(UserDTO.class));
	      verify(transferRepository, times(0)).findAllByUserTypeDebit(any(User.class));
  }
	

	  
	  	// *******************************************************************

		  @DisplayName("Wallet Operation null Test Exception- "
					+ "GIVEN transfer "
					+ "WHEN request Wallet amount not zero"
					+ "THEN returns Exception")
		  @Test
		  public void testWalletOperationNullException(){
		
			    assertThrows(NullPointerException.class, ()
			     		  -> transferService.walletOperation(null));
	  }  
	  
	  
	  	// *******************************************************************
	
		  

		  @DisplayName("Wallet Operation Zero Test DataNotFoundException- "
					+ "GIVEN transfer "
					+ "WHEN request Wallet amount not zero"
					+ "THEN returns DataNotFoundException")
		  @Test
		  public void testWalletOperationZeroException(){
		
				Transfer entity = new Transfer(
						"testRib",
						LocalDate.parse("2021-08-26"),
						0,
						"CREDIT",
						user);
			  
			  
			    assertThrows(DataNotFoundException.class, ()
			     		  -> transferService.walletOperation(entity));
	  }  
	  
	  
	  	// *******************************************************************
		  

		  @DisplayName("User Transfer Null Check Exception- "
					+ "GIVEN transfer "
					+ "WHEN request checkIfUserExistAndNotNull"
					+ "THEN returns Exception")
		  @Test
		  public void testCheckIfUserExistAndNotNullForNull(){
		
		  
			  
			    assertThrows(NullPointerException.class, ()
			     		  -> transferService.checkIfUserExistAndNotNull(null, false));
	  }  
	  
	  
	  	// *******************************************************************
		  

		  @DisplayName("User Transfer Null Check Exception result true- "
					+ "GIVEN transfer "
					+ "WHEN request checkIfUserExistAndNotNull"
					+ "THEN returns Exception")
		  @Test
		  public void testCheckIfUserExistAndNotNullForNullResultTrue(){
		
		  
			  
			    assertThrows(NullPointerException.class, ()
			     		  -> transferService.checkIfUserExistAndNotNull(null, true));
	  }  
	  
	  
	  	// *******************************************************************

		  @DisplayName("User Transfer Check Exception result false- "
					+ "GIVEN transfer "
					+ "WHEN request checkIfUserExistAndNotNull result false"
					+ "THEN returns Exception")
		  @Test
		  public void testCheckIfUserExistAndNotNullForNullResultFalse(){
		
				Transfer entity = new Transfer(
						"testRib",
						LocalDate.parse("2021-08-26"),
						100.0,
						"CREDIT",
						user);
			  
				boolean result = transferService.checkIfUserExistAndNotNull(entity, false);
			  

				assertEquals(false, result);		
	  }  
	  
	  
	  	// *******************************************************************

		  @DisplayName("User Transfer Check Exception result True- "
					+ "GIVEN transfer "
					+ "WHEN request checkIfUserExistAndNotNull result true"
					+ "THEN returns Exception")
		  @Test
		  public void testCheckIfUserExistAndNotNullForNullResultTrueCondition(){
		
				Transfer entity = new Transfer(
						"testRib",
						LocalDate.parse("2021-08-26"),
						100.0,
						"CREDIT",
						user);
			  
				boolean result = transferService.checkIfUserExistAndNotNull(entity, true);
			  

				assertEquals(false, result);		
	  }  
	  
	  
	  	// *******************************************************************
	  
		  
}

