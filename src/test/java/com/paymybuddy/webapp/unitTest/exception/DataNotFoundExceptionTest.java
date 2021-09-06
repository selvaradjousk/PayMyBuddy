package com.paymybuddy.webapp.unitTest.exception;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.paymybuddy.webapp.exception.DataNotFoundException;

@SpringBootTest
@ActiveProfiles("test")
class DataNotFoundExceptionTest {

	   
		  @Test
		  public void testdoAddTransfer(){

		  assertThrows(DataNotFoundException.class, () -> methodThrowing());

		  
		  }

			void methodThrowing(){
				throw new DataNotFoundException("Exception message");
			}
		  
		 	// *******************************************************************
	
	   
	    
	    
}
