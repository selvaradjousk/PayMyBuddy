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

import com.paymybuddy.webapp.model.BankAccount;
import com.paymybuddy.webapp.model.User;
import com.paymybuddy.webapp.repository.BankAccountRepository;
import com.paymybuddy.webapp.repository.UserRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
public class BankAccountRepositoryTest {

	@Autowired
	private DataSource dataSource;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	BankAccountRepository bankAccountRepository;
	
	@Autowired
	UserRepository userRespository;
	
	User testUser = new User();


	// *******************************************************************
	@DisplayName("@DataJpaTest & JPA components - "
			+ "GIVEN @DataJpaTest "
			+ "WHEN testing SpringData JPA repositories or JPA components"
			+ "THEN will set up a Spring application context")
	@Test
	void injectedComponentsAreNotNull() {
		assertThat(dataSource).isNotNull();
		assertThat(jdbcTemplate).isNotNull();
		assertThat(entityManager).isNotNull();
		assertThat(bankAccountRepository).isNotNull();
	}

	// ******************************************************************
	// *******************LIST BANK ACCOUNTS ***************************
	// ******************************************************************
	@DisplayName("LIST OF BANK ACCOUNTS - "
			+ "GIVEN - Users in H2 Database "
			+ "WHEN request list of Bank Accounts SpringData JPA repositories"
			+ "THEN returns the number of Bank Accounts associated to the user in the H2 DB dataset")
	@Test
	public void should_find_all_BankAccounts() {

		Iterable<BankAccount> bankAccounts = bankAccountRepository.findAll();

		assertThat(bankAccounts).hasSize(10);
	}

	// ******************************************************************
	// ******************* LOAD USERS - SQL SCRIPT***********************
	// ******************************************************************
	@DisplayName("SQL SCRIPT TO LOAD & FIND ALL BANK ACCOUNTS SIZE- "
			+ "GIVEN sql script to load more Bank accountss in H2 Database "
			+ "WHEN request insert bank accountss data and get number of bank accounts after update"
			+ "THEN returns the number of bank accounts avalable after update in the H2 DB dataset")
	@Test
	@Sql({ "/h2sourcedata_morebankaccounts.sql" })
	public void testLoadDataForTestClass() {
		assertEquals(15, bankAccountRepository.findAll().size());
	}

	// ******************************************************************
	// ******************* FIND BANK ACCOUNTS BY USER ***************************
	// ******************************************************************
	@DisplayName(" Find By LIST OF BANK ACCOUNTS BY USER- "
			+ "GIVEN a valid user"
			+ "WHEN request find bank accounts by user"
			+ "THEN returns number of bank accounts by user")
	@Test
	void whenSaved_thenFindBankAccountsByUserFromDB() {

		// GIVEN
		testUser = userRespository.findUserByEmail("testemail1@email.com");
		
		// WHEN
		List<BankAccount> savedUser = bankAccountRepository.findBankAccountByUser(testUser);

		// THEN
		assertNotNull(savedUser);
		assertEquals(4, savedUser.size());
	}
	
	
	
	
}
