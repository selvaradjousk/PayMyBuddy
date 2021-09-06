package com.paymybuddy.webapp.unitTest.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
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
import com.paymybuddy.webapp.model.User;
import com.paymybuddy.webapp.repository.ContactRepository;
import com.paymybuddy.webapp.repository.UserRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
public class ContactRepositoryTest {

	@Autowired
	private DataSource dataSource;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	ContactRepository contactRepository;
	
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
		assertThat(contactRepository).isNotNull();
	}

	// ******************************************************************
	// *******************LIST CONTACTS ***************************
	// ******************************************************************
	@DisplayName("LIST OF CONTACTS - "
			+ "GIVEN - Contacts in H2 Database "
			+ "WHEN request list of Contacts SpringData JPA repositories"
			+ "THEN returns the number of contacts associated to H2 DB dataset")
	@Test
	public void should_find_all_Contacts() {

		Iterable<Contact> contacts = contactRepository.findAll();

		assertThat(contacts).hasSize(10);
	}

	// ******************************************************************
	// ******************* LOAD CONTACTS - SQL SCRIPT***********************
	// ******************************************************************
	@DisplayName("SQL SCRIPT TO LOAD & FIND ALL CONTACTS SIZE- "
			+ "GIVEN sql script to load more Contacts in H2 Database "
			+ "WHEN request insert contacts data and get number ofcontacts after update"
			+ "THEN returns the number of contacts avalable after update in the H2 DB dataset")
	@Test
	@Sql({ "/h2sourcedata_morecontacts.sql" })
	public void testLoadDataForTestClassContacts() {
		assertEquals(15, contactRepository.findAll().size());
	}

	
	// ******************************************************************
	// ******************* FIND CONTACTS BY USER ***************************
	// ******************************************************************
	@DisplayName(" Find By LIST OF Contacts BY USER- "
			+ "GIVEN a user"
			+ "WHEN request find contacts by user"
			+ "THEN returns number of contacts by user")
	@Test
	void whenRequestContactListByUserthenReturnContactsListByUserFromDB() {
		

		// GIVEN
		testUser = userRespository.findUserByEmail("testemail1@email.com");
		
		// WHEN
		List<Contact> savedContacts = contactRepository.findListContactByPayer(testUser);

		// THEN
		assertNotNull(savedContacts);
		assertEquals(4, savedContacts.size());
	}
	
	
	// ******************************************************************
	// ******************* FIND CONTACTS AS BENiFICIARY ***************************
	// ******************************************************************
	@DisplayName(" Find By LIST OF Contacts AS BENiFICIARY - "
			+ "GIVEN a user"
			+ "WHEN request find contacts by user"
			+ "THEN returns number of contacts by user")
	@Test
	void whenRequestContactListByBeneficiarythenReturnContactsListByBeneficiaryFromDB() {
		

		// GIVEN
		testUser = userRespository.findUserByEmail("testemail1@email.com");
		
		// WHEN
		List<Contact> savedContacts = contactRepository.findListContactByContact(testUser);

		// THEN
		assertNotNull(savedContacts);
		assertEquals(2, savedContacts.size());
	}
	
	// ******************************************************************
	// ******************* FIND CONTACTS AS BENiFICIARY ***************************
	// ******************************************************************
	@DisplayName(" Find By LIST OF Contacts AS BENiFICIARY - "
			+ "GIVEN a user"
			+ "WHEN request find contacts by user"
			+ "THEN returns number of contacts by user")
	@Sql({ "/h2sourcedata_morecontacts.sql" })
	@Test
	void whenRequestContactListByBeneficiarythenReturnContactsListByBeneficiaryFromDBOnMoreUpdateData() {
		

		// GIVEN
		testUser = userRespository.findUserByEmail("testemail1@email.com");
		
		// WHEN
		List<Contact> savedContacts = contactRepository.findListContactByContact(testUser);

		// THEN
		assertNotNull(savedContacts);
		assertEquals(4, savedContacts.size());
	}
	
	
	// ******************************************************************
	// ******************* SAVE CONTACT TO USER ***************************
	// ******************************************************************
	@DisplayName(" SAVING NEW CONTACT TO USER- "
			+ "GIVEN a new contact to user"
			+ "WHEN request to Add new contacts to user and count number of contacts associated to user"
			+ "THEN returns number of contacts by user after adding contact")
	@Test
	void whenSaved_thenFindContactsByUserFromDB() {

		// GIVEN
		testUser = userRespository.findUserByEmail("testemail1@email.com");
		
		Contact newContact = new Contact();
		newContact.setContact(testUser);
		newContact.setPayer(testUser);
		newContact.setCreationDate(LocalDate.parse("2021-08-26"));
		
		
		entityManager.persist(newContact);
		entityManager.flush();

		// WHEN
		contactRepository.save(newContact);
		
		// WHEN
		List<Contact> savedContacts = contactRepository.findAll();

		// THEN
		assertNotNull(savedContacts);
		assertEquals(10+1, savedContacts.size());
	}
	
	
	// ******************************************************************
	// ******************* SAVE CONTACT TO USER ***************************
	// ******************************************************************
	@DisplayName(" DELETE CONTACT TO USER- "
			+ "GIVEN a user"
			+ "WHEN request to delete a contact and count number of accounts associated to user"
			+ "THEN returns number of contacts by user after adding contact")
	@Test
	void whenDelete_thenDeleteContactsByUserFromDB() {

		// GIVEN
		testUser = userRespository.findUserByEmail("testemail1@email.com");
		
		Contact newContact = new Contact();
		newContact.setContact(testUser);
		newContact.setPayer(testUser);
		newContact.setCreationDate(LocalDate.parse("2021-08-26"));
		
		
		entityManager.persist(newContact);
		entityManager.flush();

		contactRepository.save(newContact);
		
		List<Contact> savedContacts = contactRepository.findAll();

		assertNotNull(savedContacts);
		assertEquals(10+1, savedContacts.size());
		
		
		// WHEN
		contactRepository.delete(newContact);
		
		List<Contact> savedContactsAfterDelete = contactRepository.findAll();
		
		// THEN
		assertNotNull(savedContactsAfterDelete);
		assertEquals(10, savedContactsAfterDelete.size());			
		
	}
	
}
