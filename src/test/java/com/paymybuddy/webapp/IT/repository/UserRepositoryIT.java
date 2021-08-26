package com.paymybuddy.webapp.IT.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlConfig.TransactionMode;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;

import com.paymybuddy.webapp.model.User;
import com.paymybuddy.webapp.repository.UserRepository;


//@SpringBootTest
//@Transactional
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@DataJpaTest
class UserRepositoryIT {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    LocalDate creationDate = LocalDate.now();
    LocalDate modifitionDate = LocalDate.now();
    
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
        assertThat(found.getEmail())
          .isEqualTo(alex.getEmail());
    }
    
    @WithMockUser(username = "testemail1@email.com")
    @Test
    public void whenFindByEmail_thenReturnUserfromDB() {
        assertNotNull(userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
    }
    
    @Test
    @Sql({"/h2sourcedata2.sql"})
    public void testLoadDataForTestClass() {
        assertEquals(15, userRepository.findAll().size());
    } 
    
}
