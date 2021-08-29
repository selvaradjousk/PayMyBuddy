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

import com.paymybuddy.webapp.model.Transfer;
import com.paymybuddy.webapp.model.User;
import com.paymybuddy.webapp.repository.TransferRepository;
import com.paymybuddy.webapp.repository.UserRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
public class TransferRepositoryTest {

	@Autowired
	private DataSource dataSource;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	TransferRepository transferRepository;
	
	User testUser1 = new User(); 
	
	@Autowired
	UserRepository userRepository;
	
	public void setUp() {
	
	}


	// *******************************************************************
	@Test
	@DisplayName("@DataJpaTest & JPA components - "
			+ "GIVEN @DataJpaTest "
			+ "WHEN testing SpringData JPA repositories or JPA components"
			+ "THEN will set up a Spring application context")
	void injectedComponentsAreNotNull() {
		assertThat(dataSource).isNotNull();
		assertThat(jdbcTemplate).isNotNull();
		assertThat(entityManager).isNotNull();
		assertThat(transferRepository).isNotNull();
	}

	// ******************************************************************
	// *******************LIST TRANSFERS ***************************
	// ******************************************************************	
	@Test
	@DisplayName("LIST OF TRANSFERS - "
			+ "GIVEN - TRANSFERS in H2 Database "
			+ "WHEN request list of TRANSFERS SpringData JPA repositories"
			+ "THEN returns the number of TRANSFERS associated to H2 DB dataset")
	public void should_find_all_Transferss() {

		Iterable<Transfer> transfers = transferRepository.findAll();

		assertThat(transfers).hasSize(10);
	}

	// *******************************************************************
//	  @Sql("classpath:h2sourcedata_moretransfers.sql")
	@Test
	@DisplayName("SQL SCRIPT TO LOAD & FIND ALL TRANSFERS SIZE- "
			+ "GIVEN sql script to load more TRANSFERS in H2 Database "
			+ "WHEN request insert TRANSFERS data and get number of TRANSFERS after update"
			+ "THEN returns the number of TRANSACTIONS avalable after update in the H2 DB dataset")
	@Sql({ "/h2sourcedata_moretransfers.sql" })
	public void testLoadDataForTestClasOnMoreTransferss() {
		assertEquals(15, transferRepository.findAll().size());
	}

	// ******************************************************************
	// ******************* FIND TRANSFERS BY USER ***************************
	// ******************************************************************
	@DisplayName(" Find By LIST OF TRANSFERS BY USER (Payer) - "
			+ "GIVEN a user"
			+ "WHEN request find TRANSFERS by user"
			+ "THEN returns number of TRANSFERS by user")
	@Test
	void whenRequestTransfersListUserthenReturnTransfersListByUserFromDB() {

		// GIVEN
		testUser1 = userRepository.findUserByEmail("testemail1@email.com");
		
		// WHEN
		List<Transfer> savedTransfers = transferRepository.findAllByUser(testUser1);
		

		// THEN
		assertNotNull(savedTransfers);
		assertEquals(4, savedTransfers.size());
	}		


	// ******************************************************************
	// ******************* FIND TRANSFERS BY USER TYPE CREDIT ***************************
	// ******************************************************************
	@DisplayName(" Find By LIST OF TRANSFERS BY USER Type Credit - "
			+ "GIVEN a user"
			+ "WHEN request find TRANSFERS by user Type Credit"
			+ "THEN returns number of TRANSFERS by user Type Credit")
	@Test
	void whenRequestTransfersListUserTypeCreditthenReturnTransfersListByUserTypeCreditFromDB() {

		// GIVEN
		testUser1 = userRepository.findUserByEmail("testemail1@email.com");
		
		// WHEN
		List<Transfer> savedTransfers = transferRepository.findAllByUserTypeCredit(testUser1);
		

		// THEN
		assertNotNull(savedTransfers);
		assertEquals(2, savedTransfers.size());
	}
	
	// ******************************************************************
	// ******************* FIND TRANSFERS BY USER TYPE DEBIT ***************************
	// ******************************************************************
	@DisplayName(" Find By LIST OF TRANSFERS BY USER Type DEBIT - "
			+ "GIVEN a user"
			+ "WHEN request find TRANSFERS by user Type DEBIT"
			+ "THEN returns number of TRANSFERS by user Type DEBIT")
	@Test
	void whenRequestTransfersListUserTypeDEBITthenReturnTransfersListByUserTypeDEBITFromDB() {

		// GIVEN
		testUser1 = userRepository.findUserByEmail("testemail1@email.com");
		
		// WHEN
		List<Transfer> savedTransfers = transferRepository.findAllByUserTypeDebit(testUser1);
		

		// THEN
		assertNotNull(savedTransfers);
		assertEquals(2, savedTransfers.size());
	}	
}
