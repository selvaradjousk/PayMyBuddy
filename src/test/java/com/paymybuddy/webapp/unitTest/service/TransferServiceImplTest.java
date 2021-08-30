package com.paymybuddy.webapp.unitTest.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

import com.paymybuddy.webapp.dto.TransferDTO;
import com.paymybuddy.webapp.dto.UserDTO;
import com.paymybuddy.webapp.service.ITransferService;
import com.paymybuddy.webapp.service.IUserService;

@DisplayName("TRANSFER SERVICE - H2 DB TEST ")
@SpringBootTest
@ActiveProfiles("test")
class TransferServiceImplTest {

    @Autowired
    ITransferService transferService;
    
    @Autowired
    IUserService userService;
    
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
        assertEquals(10, listTransferDTO.size());
    }
	
	// *************This method is a step for pagable template*************	
  @Test
  public void testfindAllTransferByUser(){

  	//GIVEN
      listUserDTO = userService.findAllUsers();
      UserDTO userDTO = listUserDTO.get(1);

      //WHEN
      List<TransferDTO> listTransfers = transferService
    		  .findAllTransferByUser(userDTO);
      //THEN
      assertEquals(3,listTransfers.size());
  }
	
	
	// *******************************************************************

  @Test
  public void TestLastThreeTransfers(){

      //GIVEN
      listUserDTO = userService.findAllUsers();
      UserDTO userDTO = listUserDTO.get(1);
      int page = 0;

      //WHEN
      Page<TransferDTO> pagesTransferDTO = transferService
    		  .lastThreeTransfers(userDTO,  PageRequest.of(page,2));

      //THEN
      assertEquals(2, pagesTransferDTO.getContent().size());
      assertEquals(3, pagesTransferDTO.getTotalElements());
  }

	// *******************************************************************
  
}
