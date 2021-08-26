package com.paymybuddy.webapp.IT.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.paymybuddy.webapp.model.User;
import com.paymybuddy.webapp.repository.UserRepository;
import com.paymybuddy.webapp.service.UserServiceImpl;

// Mocking With @MockBean
// https://www.baeldung.com/spring-boot-testing

@RunWith(SpringRunner.class)
class UserServiceImplTestContextConfiguration {

    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {
 
        @Bean
        public UserServiceImpl userService() {
            return new UserServiceImpl();
        }
    }

    @Autowired
    private UserServiceImpl userService;

    @MockBean
    private UserRepository userRepository;

//    @BeforeEach
//    public void setUp() {
//        User alex = new User();
//        alex.setId(1);
//        alex.setFirstName("myEmail");
//        alex.setLastName("myEmail");
//        alex.setUserName("myEmail");
//        alex.setEmail("myEmail");
//        alex.setPassword("myEmail");
//        alex.setRoles("myEmail");
//        alex.setCreationDate(LocalDate.parse("2019-12-31"));
//        alex.setModificationDate(LocalDate.parse("2019-12-31"));
//
//        Mockito.when(userRepository.findByEmail(alex.getEmail()))
//          .thenReturn(alex);
//    }
//    
//    @Test
//    public void whenValidEmail_thenUserShouldBeFound() {
//        String email = "alex";
//        User found = userService.getUserByEmail(email);
//     
//         assertThat(found.getEmail())
//          .isEqualTo(email);
//     }
    
}
