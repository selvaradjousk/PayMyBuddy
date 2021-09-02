package com.paymybuddy.webapp.util;

import org.springframework.stereotype.Component;

import com.paymybuddy.webapp.dto.UserDTO;
import com.paymybuddy.webapp.exception.DataNotConformException;
import com.paymybuddy.webapp.model.User;

/**
 * The Class UserMapper.
 */
@Component
public class UserMapper {

//    LocalDate creationDate = LocalDate.now();

    /**
 * To user DTO.
 *
 * @param user the user
 * @return the user DTO
 */
public UserDTO toUserDTO(final User user) {
    	if (user != null) {
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
        } else {
            throw new DataNotConformException(" USER CANNOT BE NULL");
        }
    }

    /**
     * To user DO.
     *
     * @param userDTO the user DTO
     * @return the user
     */
    public User toUserDO(final UserDTO userDTO) {
        if(userDTO != null) {
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
        } else {
            return null;
        }
    }
}
