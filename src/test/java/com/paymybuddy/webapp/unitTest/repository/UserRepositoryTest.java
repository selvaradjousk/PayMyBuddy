package com.paymybuddy.webapp.unitTest.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

import javax.sql.DataSource;

import org.junit.jupiter.api.DisplayName;
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

//@Transactional
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
		assertThat(userRepository).isNotNull();
	}
	
	// ******************************************************************
	// *******************LIST USERS ************************************
	// ******************************************************************
	@DisplayName("LIST OF USERS - "
			+ "GIVEN - Users in H2 Database "
			+ "WHEN request list of users SpringData JPA repositories"
			+ "THEN returns the number of user avalable in the H2 DB dataset")
	@Test
	public void should_find_all_Users() {
		Iterable<User> users = userRepository.findAll();
		assertThat(users).hasSize(10);
	}
	
	// ******************************************************************
	// ******************* LOAD USERS - SQL SCRIPT***********************
	// ******************************************************************
	@DisplayName("SQL SCRIPT TO LOAD & FIND ALL SIZE- "
			+ "GIVEN sql script to load more Users in H2 Database "
			+ "WHEN request insert users data and get number of user after update"
			+ "THEN returns the number of user avalable after update in the H2 DB dataset")
	@Test
	@Sql({ "/h2sourcedata_moreusers.sql" })
	public void testLoadDataForTestClass() {
		assertEquals(15, userRepository.findAll().size());
	}
	
	// https://stackoverflow.com/questions/23435937/how-to-test-spring-data-repositories
	// ******************************************************************
	//**********************prepopulate using Repo ***********************
	// ******************* SAVE USER -FIND USER *************************
	// ******************************************************************
//	@Rollback(false)
	@DisplayName("SAVE USER & FIND USER BY EMAIL - "
			+ "GIVEN a user to add (save) using userRepository.save(user) to list of Users in H2 Database "
			+ "WHEN request add user and on check number of user and user data added"
			+ "THEN returns expected number of user and added user entity values")
	@Test
	void whenSaved_thenFindsByEmail() {
		userRepository.save(new User(
				100,
				"userName",
				"firstName",
				"lastName",
				"myEmail",
				"password",
				LocalDate.parse("2021-08-28"),
				LocalDate.parse("2021-08-28"),
				"roles",
				true,
				1000.0));

		User savedUser = userRepository.findUserByEmail("myEmail");

		assertNotNull(savedUser);
		assertEquals(11, userRepository.findAll().size());
		assertEquals("myEmail", savedUser.getEmail());
		assertEquals("userName", savedUser.getUserName());
		assertEquals("firstName", savedUser.getFirstName());
		assertEquals("lastName", savedUser.getLastName());
		assertEquals("password", savedUser.getPassword());
		assertEquals(LocalDate.parse("2021-08-28"), savedUser.getCreationDate());
		assertEquals(LocalDate.parse("2021-08-28"), savedUser.getModificationDate());
		assertEquals("roles", savedUser.getRoles());
		assertEquals(true, savedUser.isActive());
		assertEquals(1000.0, savedUser.getWalletAmount());
	}
	
	// ******************************************************************
	// ******************* FIND USER BY EMAIL ***************************
	// ******************************************************************
	@DisplayName(
			"GIVEN a valid user email Id"
			+ "WHEN request find user by email"
			+ "THEN returns user corresponding to the given email")
	@Test
	void whenSaved_thenFindsByEmailFromDB() {

		User savedUser = userRepository.findUserByEmail("testemail3@email.com");

		assertThat(userRepository.findByEmail("testemail3@email.com")).isNotNull();
		assertEquals("testemail3@email.com", savedUser.getEmail());
	}

	// ******************************************************************
	@DisplayName(
			"GIVEN User "
			+ "WHEN requested to find user by Email"
			+ "THEN returns user found ")
	@WithMockUser(username = "testemail1@email.com")
	@Test
	public void whenFindByEmail_thenReturnUserfromDB() {

		User foundUser = userRepository.findUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		assertNotNull(foundUser);
		assertEquals("testemail1@email.com", foundUser.getEmail());
	}
	
	// ******************************************************************
	//**********************prepopulate using SQL ***********************
	// ******************* ADD USER & FIND BY EMAIL *********************
	// ******************************************************************
	@DisplayName(
			"GIVEN sql script to add a new User in H2 Database "
			+ "WHEN request insert user and find user by email"
			+ "THEN user added returns expected email ")
	@Test
	@Sql("/createUser.sql")
	void whenInitializedByDbUnit_thenFindsByEmail() {
		User user = userRepository.findUserByEmail("testemail100@email.com");
		assertNotNull(user);
		assertEquals("testemail100@email.com", user.getEmail());
	}

	//*******************************************************************
	//**********************prepopulate using SQL ***********************
	//*******************************************************************
	@DisplayName(
			"GIVEN sql script to add a new User in H2 Database "
			+ "WHEN request insert user and find user by email"
			+ "THEN user added returns expected parameter values of the user added ")
	@Test
	@Sql("/createUser.sql")
	void whenInitializedByDbUnit_thenFindsByNameAndCheckEmailExistEquals() {
		User user = userRepository.findUserByEmail("testemail100@email.com");
		assertEquals("testfirstname100", user.getFirstName());
		assertThat(user).hasFieldOrPropertyWithValue("password", "testpassword100");
	}

	//*******************************************************************
	//**********************prepopulate using EntityManager**************
	//*******************************************************************
	@DisplayName(
			"GIVEN new User to add in H2 Database "
			+ "WHEN requested to persist user data using entityManager in H2 DB"
			+ "THEN user data email added match expected value ")
	@Test
	public void whenFindByEmail_thenReturnUser() {
		// given
		User alex = new User();
		alex.setFirstName("firstName");
		alex.setLastName("lastName");
		alex.setUserName("userName");
		alex.setEmail("myEmail@email.com");
		alex.setPassword("password");
		alex.setRoles("roles");
		alex.setCreationDate(LocalDate.parse("2019-12-31"));
		alex.setModificationDate(LocalDate.parse("2019-12-31"));
		// LocalDateTime.parse("2019-12-31T23:59:59"))

		entityManager.persist(alex);
		entityManager.flush();

		// when
		User found = userRepository.findUserByEmail(alex.getEmail());

		// then
		assertEquals(alex.getEmail(), found.getEmail());
	}

	
	//*******************************************************************



	
	
	
	
	
	// ******************************************************************
	// ******************* OPTINAL<User> FIND BY EMAIL ******************
	// ******************************************************************
	@DisplayName(
			"GIVEN User "
			+ "WHEN requested to find by Email"
			+ "THEN returns expected user found by Email")
	@Test
	public void it_should_find_user_byEmail() {
		User user = new User();
		user.setFirstName("myEmail");
		user.setLastName("myEmail");
		user.setUserName("myEmail");
		user.setEmail("myEmail@email.com");
		user.setPassword("myEmail");
		user.setRoles("myEmail");
		user.setCreationDate(LocalDate.parse("2019-12-31"));
		user.setModificationDate(LocalDate.parse("2019-12-31"));
		user = entityManager.persistAndFlush(user);
		assertEquals(user, userRepository.findByEmail(user.getEmail()).get());
		}

	// **************** SQL generated by Hibernate *********************************
//	Hibernate: 
//	    insert 
//	    into
//	        user
//	        (id_user, active, creation_date, email, first_name, last_name, modification_date, password, roles, user_name, wallet_amount) 
//	    values
//	        (null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
//	Hibernate: 
//	    select
//	        user0_.id_user as id_user1_4_,
//	        user0_.active as active2_4_,
//	        user0_.creation_date as creation3_4_,
//	        user0_.email as email4_4_,
//	        user0_.first_name as first_na5_4_,
//	        user0_.last_name as last_nam6_4_,
//	        user0_.modification_date as modifica7_4_,
//	        user0_.password as password8_4_,
//	        user0_.roles as roles9_4_,
//	        user0_.user_name as user_na10_4_,
//	        user0_.wallet_amount as wallet_11_4_ 
//	    from
//	        user user0_ 
//	    where
//	        user0_.email=?

	
	
	
	
	
	
	
	// ******************************************************************
	// ******************* findUserById(Integer id) ******************
	// ******************************************************************
	@DisplayName(
			"GIVEN User Id "
			+ "WHEN requested to find by user ID"
			+ "THEN returns expected user found by Id")
	@Test
	public void testFindUserByyId() {
		User user = new User();
		user.setId(1);
		user.setFirstName("testfirstname1");
		user.setLastName("testleastname1");
		user.setUserName("testusername1");
		user.setEmail("testemail1@email.com");
		user.setPassword("testpassword1");
		user.setRoles("admin");
		user.setCreationDate(LocalDate.parse("2021-08-26"));
		user.setModificationDate(LocalDate.parse("2021-08-26"));
		user = userRepository.findById(1).get();
		assertEquals(1, user.getId());
		}

	
	
}
