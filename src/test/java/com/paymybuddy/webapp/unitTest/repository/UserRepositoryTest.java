package com.paymybuddy.webapp.unitTest.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.paymybuddy.webapp.model.User;
import com.paymybuddy.webapp.repository.UserRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
public class UserRepositoryTest {

	@Autowired
	private DataSource dataSource;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	UserRepository userRepository;

	@Test
	void injectedComponentsAreNotNull() {
		assertThat(dataSource).isNotNull();
		assertThat(jdbcTemplate).isNotNull();
		assertThat(entityManager).isNotNull();
		assertThat(userRepository).isNotNull();
	}
// *******************************************************************
	@Test
	public void should_find_all_Users() {
		Iterable<User> users = userRepository.findAll();
		assertThat(users).hasSize(10);
	}
	
	//*******************************************************************
	@Test
	@Sql({ "/h2sourcedata_moreusers.sql" })
	public void testLoadDataForTestClass() {
		assertEquals(15, userRepository.findAll().size());
	}
	
	//*******************************************************************
	@Test
	void whenSaved_thenFindsByEmail() {
		userRepository.save(new User(100, "myEmail", "myEmail", "myEmail", "myEmail", "myEmail",
				LocalDate.parse("2019-12-31"), LocalDate.parse("2019-12-31"), "myEmail", true, 1000.0));

		User savedUser = userRepository.findByEmail("myEmail");

		assertNotNull(savedUser);
		assertEquals("myEmail", savedUser.getEmail());
	}
	
	//*******************************************************************
	@Test
	void whenSaved_thenFindsByEmailFromDB() {

		User savedUser = userRepository.findByEmail("testemail3@email.com");

		assertThat(userRepository.findByEmail("testemail3@email.com")).isNotNull();
		assertEquals("testemail3@email.com", savedUser.getEmail());
	}
	
	//*******************************************************************
	@Test
	@Sql("/createUser.sql")
	void whenInitializedByDbUnit_thenFindsByEmail() {
		User user = userRepository.findByEmail("testemail100@email.com");
		assertThat(user).isNotNull();
	}

	//*******************************************************************
	@Test
	@Sql("/createUser.sql")
	void whenInitializedByDbUnit_thenFindsByNameAndCheckEmailExistEquals() {
		User user = userRepository.findByEmail("testemail100@email.com");
		assertEquals("testfirstname100", user.getFirstName());
		assertThat(user).hasFieldOrPropertyWithValue("password", "testpassword100");
	}

	//*******************************************************************
	@Test
	public void whenFindByEmail_thenReturnUser() {
		// given
		User alex = new User();
		alex.setFirstName("myEmail");
		alex.setLastName("myEmail");
		alex.setUserName("myEmail");
		alex.setEmail("myEmail");
		alex.setPassword("myEmail");
		alex.setRoles("myEmail");
		alex.setCreationDate(LocalDate.parse("2019-12-31"));
		alex.setModificationDate(LocalDate.parse("2019-12-31"));
		// LocalDateTime.parse("2019-12-31T23:59:59"))

		entityManager.persist(alex);
		entityManager.flush();

		// when
		User found = userRepository.findByEmail(alex.getEmail());

		// then
		assertThat(found.getEmail()).isEqualTo(alex.getEmail());
	}

	//*******************************************************************
	@WithMockUser(username = "testemail1@email.com")
	@Test
	public void whenFindByEmail_thenReturnUserfromDB() {

		User foundUser = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		assertNotNull(foundUser);
		assertEquals("testemail1@email.com", foundUser.getEmail());
	}
	
	//*******************************************************************

}
