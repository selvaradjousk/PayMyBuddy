package com.paymybuddy.webapp.unitTest.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
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
	
	@Mock
	User testUser1; 
	
	@Mock
	UserRepository userRepository;
	
	public void setUp() {

		
	}


	// *******************************************************************
	@Test
	void injectedComponentsAreNotNull() {
		assertThat(dataSource).isNotNull();
		assertThat(jdbcTemplate).isNotNull();
		assertThat(entityManager).isNotNull();
		assertThat(transferRepository).isNotNull();
	}

	// *******************************************************************
	@Test
	public void should_find_all_Users() {

		Iterable<Transfer> transfers = transferRepository.findAll();

		assertThat(transfers).hasSize(10);
	}

	// *******************************************************************
//	  @Sql("classpath:h2sourcedata_moretransfers.sql")
	@Test
	@Sql({ "/h2sourcedata_moretransfers.sql" })
	public void testLoadDataForTestClass() {
		assertEquals(15, transferRepository.findAll().size());
	}


}
