package com.paymybuddy.webapp.unitTest.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import javax.sql.DataSource;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.paymybuddy.webapp.model.Contact;
import com.paymybuddy.webapp.model.Transaction;
import com.paymybuddy.webapp.model.User;
import com.paymybuddy.webapp.repository.TransactionRepository;
import com.paymybuddy.webapp.repository.UserRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
public class TransactionRepositoryTest {


	  @Autowired private DataSource dataSource;
	  @Autowired private JdbcTemplate jdbcTemplate;
	
	  @Autowired
	  private TestEntityManager entityManager;

	  @Autowired
	  TransactionRepository transactionRepository;
	  
	  @Autowired
	  UserRepository userRespository;

		User testUser = new User();

		// *******************************************************************
	  
	  @Test
	  void injectedComponentsAreNotNull(){
	    assertThat(dataSource).isNotNull();
	    assertThat(jdbcTemplate).isNotNull();
	    assertThat(entityManager).isNotNull();
	    assertThat(transactionRepository).isNotNull();
	  }
	  
		// ******************************************************************
		// *******************LIST BANK ACCOUNTS ***************************
		// ******************************************************************	  
	  @Test
	  public void should_find_all_Transactions() {
		  
		  Iterable<Transaction> transactions = transactionRepository.findAll();

	    assertThat(transactions).hasSize(10);
	  }

		// ******************************************************************
		// ******************* LOAD TRANSACTIONS - SQL SCRIPT***********************
		// ******************************************************************	    
	    @Test
	    @Sql({"/h2sourcedata_moretransactions.sql"})
	    public void testLoadDataForTestClassOnMoreTransaction() {
	        assertEquals(15, transactionRepository.findAll().size());
	    } 
	    
		// ******************************************************************
		// ******************* FIND CONTACTS BY USER ***************************
		// ******************************************************************
		@DisplayName(" Find By LIST OF Transactions BY USER (Payer) - "
				+ "GIVEN a user (Payer)"
				+ "WHEN request find transactions by payer"
				+ "THEN returns number of transactions by payer")
		@Test
		void whenRequestTransactionsListBPayerthenReturnTransactionsListByPayerFromDB() {
			

			// GIVEN
			testUser = userRespository.findUserByEmail("testemail1@email.com");
			
			// WHEN
			List<Transaction> savedTranactions = transactionRepository.findAllByPayer(testUser);
			

			// THEN
			assertNotNull(savedTranactions);
			assertEquals(3, savedTranactions.size());
		}	    

		
}
