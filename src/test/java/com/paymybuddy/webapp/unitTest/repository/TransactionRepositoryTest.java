package com.paymybuddy.webapp.unitTest.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.paymybuddy.webapp.model.Transaction;
import com.paymybuddy.webapp.repository.TransactionRepository;

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


	  @Test
	  void injectedComponentsAreNotNull(){
	    assertThat(dataSource).isNotNull();
	    assertThat(jdbcTemplate).isNotNull();
	    assertThat(entityManager).isNotNull();
	    assertThat(transactionRepository).isNotNull();
	  }
	  
	  
	  @Test
	  public void should_find_all_Userss() {
		  
		  Iterable<Transaction> transactions = transactionRepository.findAll();

	    assertThat(transactions).hasSize(10);
	  }

	    
	    @Test
	    @Sql({"/h2sourcedata_moretransactions.sql"})
	    public void testLoadDataForTestClass() {
	        assertEquals(15, transactionRepository.findAll().size());
	    } 
	  
}
