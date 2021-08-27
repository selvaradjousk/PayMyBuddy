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

import com.paymybuddy.webapp.model.BankAccount;
import com.paymybuddy.webapp.repository.BankAccountRepository;

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

	@Test
	void injectedComponentsAreNotNull() {
		assertThat(dataSource).isNotNull();
		assertThat(jdbcTemplate).isNotNull();
		assertThat(entityManager).isNotNull();
		assertThat(bankAccountRepository).isNotNull();
	}

	@Test
	public void should_find_all_BankAccounts() {

		Iterable<BankAccount> bankAccounts = bankAccountRepository.findAll();

		assertThat(bankAccounts).hasSize(10);
	}

	@Test
	@Sql({ "/h2sourcedata_morebankaccounts.sql" })
	public void testLoadDataForTestClass() {
		assertEquals(15, bankAccountRepository.findAll().size());
	}

}
