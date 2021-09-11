package com.paymybuddy.webapp.unitTest.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

import com.paymybuddy.webapp.constant.TransferType;
import com.paymybuddy.webapp.dto.TransferDTO;
import com.paymybuddy.webapp.dto.UserDTO;
import com.paymybuddy.webapp.exception.DataNotConformException;
import com.paymybuddy.webapp.model.User;
import com.paymybuddy.webapp.service.ITransferService;
import com.paymybuddy.webapp.service.IUserService;
import com.paymybuddy.webapp.util.UserMapper;

@DisplayName("TRANSFER SERVICE - H2 DB TEST ")
@SpringBootTest
@ActiveProfiles("test")
class TransferServiceH2Test {

    @Autowired
    ITransferService transferService;
    
    @Autowired
    IUserService userService;
    
    public UserMapper userMapper = new UserMapper();
    
    List<UserDTO> listUserDTO = new ArrayList<UserDTO>();
    
    
    
    
	// *******************************************************************
    
    
    
    
	@DisplayName("Find Transfer All Transfers (Service) - "
			+ "GIVEN Transfers list "
			+ "WHEN Requested find all transfers list"
			+ "THEN returns expected transfers list size")
    @Test
    public void testFindAllTransfers(){

		//GIVEN
		// list of transfers populated in the H2 DB dataset
		
        //WHEN
        List<TransferDTO> listTransferDTO = transferService
        		.findAllTransfers();

        //THEN
        assertEquals(11, listTransferDTO.size());
    }
	
	
	
	
	// *************This method is a step for pagable template*************	

	
	
	
	  @DisplayName("Find Transfer All Transfers by User - "
				+ "GIVEN Transfers list "
				+ "WHEN Requested find all transfers list by User"
				+ "THEN returns expected transfers list size by User")
	  @Test
	  public void testfindAllTransferByUser(){
	
	  	//GIVEN
	      listUserDTO = userService.findAllUsers();
	      UserDTO userDTO = listUserDTO.get(1);
	
	      //WHEN
	      List<TransferDTO> listTransfers = transferService
	    		  .findAllTransferByUser(userDTO);
	      //THEN
	      assertEquals(4,listTransfers.size());
	  }
  
	  
	  
  
	
	// *******************************************************************

	  
	  
	  
	  @DisplayName("Find Transfer All Transfers by User Credit Type - "
				+ "GIVEN Transfers list "
				+ "WHEN Requested find all transfers list by User Credit Type"
				+ "THEN returns expected transfers list size by User Credit Type")
	  @Test
	  public void testfindAllTransferByUserCreditType(){
	
	  	//GIVEN
	      listUserDTO = userService.findAllUsers();
	      UserDTO userDTO = listUserDTO.get(1);
	
	      //WHEN
	      List<TransferDTO> listTransfers = transferService
	    		  .findAllByUserTypeCredit(userDTO);
	      //THEN
	      assertEquals(1,listTransfers.size());
  }
	
	  
	  
	  
  
  	// *******************************************************************

	  
	  
	  
	  @DisplayName("Find Transfer All Transfers by User Debit Type - "
				+ "GIVEN Transfers list "
				+ "WHEN Requested find all transfers list by User Debit Type"
				+ "THEN returns expected transfers list size by User Debit Type")
	  @Test
	  public void testfindAllTransferByUserDebitType(){
	
	  	//GIVEN
	      listUserDTO = userService.findAllUsers();
	      UserDTO userDTO = listUserDTO.get(1);
	
	      //WHEN
	      List<TransferDTO> listTransfers = transferService
	    		  .findAllByUserTypeDebit(userDTO);
	      //THEN
	      assertEquals(2,listTransfers.size());
	  }
		
	  
	  
	  
  
  	// *******************************************************************

	  
	  
	  
	  @DisplayName("Find Transfer All Transfers by User Pageable- "
				+ "GIVEN Transfers list "
				+ "WHEN Requested find all transfers list by User Pageable"
				+ "THEN returns expected transfers list size by User") 
	  @Test
	  public void testFindAllByTransfersByUserPageable(){
	      //GIVEN
	      listUserDTO = userService.findAllUsers();
	      UserDTO userDTO = listUserDTO.get(1);
	      int page = 0;
	      //WHEN
	      Page<TransferDTO> pagesTransfer = transferService
	    		  .findAllTransferByUser(userDTO,  PageRequest.of(page,2));
	      //THEN
	      assertEquals(2,pagesTransfer.getContent().size());
	  }
  
	  
	  
	  
	// *******************************************************************  

	  
	  
	  
	  @DisplayName("Find Transfer three Transfers by User Pageable- "
				+ "GIVEN Transfers list "
				+ "WHEN Requested find three transfers list by User Pageable"
				+ "THEN returns expected transfers list size by User") 
	  @Test
	  public void TestLastThreeTransfersByUserPageable(){
	
	      //GIVEN
	      listUserDTO = userService.findAllUsers();
	      UserDTO userDTO = listUserDTO.get(1);
	      int page = 0;
	
	      //WHEN
	      Page<TransferDTO> pagesTransferDTO = transferService
	    		  .lastThreeTransfers(userDTO,  PageRequest.of(page,2));
	
	      //THEN
	      assertEquals(2, pagesTransferDTO.getContent().size());
	      assertEquals(4, pagesTransferDTO.getTotalElements());
	  }
	  
	  
	  

	// *******************************************************************

	  
	  
	  
	  @DisplayName("Save new transfer type credit- "
				+ "GIVEN a new transfer "
				+ "WHEN Requested save transfer type credit"
				+ "THEN returns as expectedTransfer saved") 
	  @Test
	  public void testSaveTransferTypeCredit(){
	
	      //GIVEN
	      listUserDTO = userService
	    		  .findAllUsers();
	      
	      User userBeneficiary = userMapper
	    		  .toUserDO(listUserDTO.get(1));
	      
	      Double walletBefore = userBeneficiary
	    		  .getWalletAmount();
	      
	      TransferDTO transferDTO = new TransferDTO();
	
	      //WHEN
	      transferDTO = transferService.addTransfer(
	    		  "FR 1111 1111 1111",
	    		  500.0,
	              TransferType.CREDIT.toString(),
	              userBeneficiary);
	      
	      Double newWallet = userService
	    		  .findUserById(
	    				  userBeneficiary.getId()).getWalletAmount();
	
	      //THEN
	      // Balance after debit has expected balance 
	      assertEquals(walletBefore+500.0, newWallet);
	      
	      // Transfer operation has been performed one time 
	      assertTrue( transferDTO.getIdTransfer() > 0);
	  }
  
	  
	  
	  
	// *******************************************************************  

	  
	  
	  
	  @DisplayName("Save new transfer type debit- "
				+ "GIVEN a new transfer "
				+ "WHEN Requested save transfer type debit"
					+ "THEN returns as expectedTransfer saved") 
	  @Test
	  public void testSaveTransferTypeDebit(){
	
	      //GIVEN
	      listUserDTO = userService
	    		  .findAllUsers();
	      
	      User userBeneficiary = userMapper
	    		  .toUserDO(listUserDTO.get(1));
	      
	      Double walletBefore = userBeneficiary
	    		  .getWalletAmount();
	      
	      TransferDTO transferDTO = new TransferDTO();
	
	      //WHEN
	      transferDTO = transferService.addTransfer(
	    		  "FR 1111 1111 1111",
	    		  500.0,
	              TransferType.DEBIT.toString(),
	              userBeneficiary);
	      
	      Double newWallet = userService
	    		  .findUserById(
	    				  userBeneficiary.getId()).getWalletAmount();
	
	      //THEN
	      // Balance after debit has expected balance 
	      assertEquals(walletBefore-500.0, newWallet);
	      
	      // Transfer operation has been performed one time 
	      assertTrue(transferDTO.getIdTransfer() > 0);
	  }
  
  
	  
	  

 	// *******************************************************************

	  
	  
	  
	  @DisplayName("Save new transfer type invalid - DataNotConformException- "
				+ "GIVEN a new transfer "
				+ "WHEN Requested save transfer type invalid"
					+ "THEN returns error DataNotConformException") 
	  	@Test
	 	public void testSaveTransferNotCreditOrDebitType(){
	
	 	//GIVEN
	 	listUserDTO = userService
	   		  .findAllUsers();
	     
	 	User userBeneficiary = userMapper
	   		  .toUserDO(listUserDTO.get(1));
	
	
	 	//WHEN  // THEN
	 	assertThrows(DataNotConformException.class, ()
	   		  -> transferService.addTransfer(
	   		  "FR 1111 1111 1111",
	   		  5000.0,
	           "SOMETHING",
	           userBeneficiary));
	 }  
	  
	  
	  
	  
 
 	// *******************************************************************
 	

	  
	  
	  @DisplayName("Save new transfer Zero amount input value - DataNotConformException- "
				+ "GIVEN a new transfer "
				+ "WHEN Requested save transfer zero amount input value "
					+ "THEN returns error DataNotConformException")  
		@Test
		public void testSaveTransferAmountZero(){
	
		//GIVEN
			listUserDTO = userService
			  .findAllUsers();
	
			User userBeneficiary = userMapper
			  .toUserDO(listUserDTO.get(1));
	
		//WHEN  // THEN
			assertThrows(DataNotConformException.class, ()
					-> transferService.addTransfer(
					"FR 1111 1111 1111",
					0,
					TransferType.CREDIT.toString(),
					userBeneficiary));
		}
	
	  
	  
	  

  	// *******************************************************************		

	  
	  
	  
	  @DisplayName("Save new transfer null user input value - DataNotConformException- "
				+ "GIVEN a new transfer "
				+ "WHEN Requested save transfer null user input value "
					+ "THEN returns error DataNotConformException")  	
	  @Test
	  public void testSaveTransferUserNull(){

	      //GIVEN
	      listUserDTO = userService
	    		  .findAllUsers();
	      
	
	      //WHEN  // THEN
	      assertThrows(DataNotConformException.class, () -> transferService.addTransfer(
	    		  "FR 1111 1111 1111",
	    		  5000.0,
	    		  TransferType.CREDIT.toString(),
	    		  null));
	  }
	  
	  
	  
	  
	  
	  	// *******************************************************************

	  
	  
	  
	  
	  @DisplayName("Save new transfer Debit Excceds Balance- DataNotConformException- "
				+ "GIVEN a new transfer "
				+ "WHEN Requested save transfer Debit Excceds Balance"
					+ "THEN returns error DataNotConformException")  	  
	  @Test
	  public void testSaveTransferDebitExccedsBalance(){

	      //GIVEN
	      listUserDTO = userService
	    		  .findAllUsers();
	      
	      User userBeneficiary = userMapper
	    		  .toUserDO(listUserDTO.get(1));
	      
	      Double walletBefore = userBeneficiary
	    		  .getWalletAmount();
	      
	      TransferDTO transferDTO = new TransferDTO();
      
	      //THEN  //WHEN
	      assertThrows(DataNotConformException.class, () -> transferService.addTransfer(
	    		  "FR 1111 1111 1111",
	    		  5000.0,
	    		  TransferType.DEBIT.toString(),
	    		  userBeneficiary));
	      
	      Double newWallet = userService
	    		  .findUserById(userBeneficiary.getId()).getWalletAmount();
	      
	      // Account Balance remains unchanged
	      assertEquals(walletBefore, newWallet);
	      
	      // No operation Transfer performed
	      assertFalse(transferDTO.getIdTransfer() > 0);
	  }
	  
	 	// *******************************************************************		
  
}

