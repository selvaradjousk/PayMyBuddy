package com.paymybuddy.webapp.unitTest.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import com.paymybuddy.webapp.model.User;
import com.paymybuddy.webapp.repository.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {


	  @Autowired private DataSource dataSource;
	  @Autowired private JdbcTemplate jdbcTemplate;
	
	  @Autowired
	  private TestEntityManager entityManager;

	  @Autowired
	  UserRepository userRepository;


	  @Test
	  void injectedComponentsAreNotNull(){
	    assertThat(dataSource).isNotNull();
	    assertThat(jdbcTemplate).isNotNull();
	    assertThat(entityManager).isNotNull();
	    assertThat(userRepository).isNotNull();
	  }
	  
	  @Test
	  void whenSaved_thenFindsByEmail() {
	    userRepository.save(new User(
	    		100,
	            "myEmail",
	            "myEmail",
	            "myEmail",
	            "myEmail",
	            "myEmail",
	            LocalDate.parse("2019-12-31"),
	            LocalDate.parse("2019-12-31"),
	            "myEmail",
	            true,
	            1000.0));

	    assertThat(userRepository.findByEmail("myEmail")).isNotNull();
	  }

	  @Test
	  @Sql("/createUser.sql")
	  void whenInitializedByDbUnit_thenFindsByEmail() {
	    User user = userRepository.findByEmail("testemail100@email.com");
	    assertThat(user).isNotNull();
	  }
	  
	  @Test
	  @Sql("/createUser.sql")
	  void whenInitializedByDbUnit_thenFindsByNameAndCheckEmailExistEquals() {
	    User user = userRepository.findByEmail("testemail100@email.com");
	    assertEquals("testfirstname100", user.getFirstName());
	    assertThat(user).hasFieldOrPropertyWithValue("password", "testpassword100");
	  }
	  
	  @Test
	  public void should_find_all_Userss() {
		  
		  Iterable<User> users = userRepository.findAll();

	    assertThat(users).hasSize(10);
	  }


	  
}
