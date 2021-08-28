package com.paymybuddy.webapp.util;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.paymybuddy.webapp.dto.UserDTO;
import com.paymybuddy.webapp.model.User;

@Component
public class UserMapper {

//    LocalDate creationDate = LocalDate.now();

    public UserDTO toUserDTO(User user) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setUserName(user.getUserName());
            userDTO.setFirstName(user.getFirstName());
            userDTO.setEmail(user.getEmail());
            userDTO.setPassword(user.getPassword());
            userDTO.setCreationDate(user.getCreationDate());
            userDTO.setModificationDate(user.getModificationDate());
            userDTO.setRoles(user.getRoles());
            userDTO.setActive(user.isActive());
            userDTO.setWalletAmount(user.getWalletAmount());



            return userDTO;
    }

    public User toUserDO(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setUserName(userDTO.getUserName());
        user.setFirstName(userDTO.getFirstName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setCreationDate(userDTO.getCreationDate());
        user.setModificationDate(userDTO.getModificationDate());
        user.setRoles(userDTO.getRoles());
        user.setActive(userDTO.isActive());
        user.setWalletAmount(userDTO.getWalletAmount());
            return user;
    }
}