package com.paymybuddy.webapp.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.webapp.dto.UserDTO;
import com.paymybuddy.webapp.exception.DataAlreadyExistException;
import com.paymybuddy.webapp.exception.DataNotConformException;
import com.paymybuddy.webapp.exception.DataNotFoundException;
import com.paymybuddy.webapp.model.User;
import com.paymybuddy.webapp.repository.UserRepository;
import com.paymybuddy.webapp.util.UserMapper;

import lombok.extern.log4j.Log4j2;

// **************************** TODOs LIST ***********************************

// Method: 
// --> getAllUsers(String email) served by userRepository.findAll()
// --> addNewUser(userDTO) 
// 			check for user exist already by userRepository.findUserByEmail(email)
//			add served by userMapper.toUserDTO(user)
// --> check for email validity
// --> check password match on confirm password
// --> input fields validation done in the respective DO with annotations

/**
 * The Class UserServiceImpl.
 */
/** The Constant log. */
@Log4j2	
@Service
public class UserServiceImpl implements IUserService {

	/** The user repository. */
	@Autowired
	private UserRepository userRepository;

    /** The user mapper. */
    public UserMapper userMapper = new UserMapper();


    // *******************************************************************
    /**
     * Find all users.
     *
     * @return the list
     */
	@Override
	public List<UserDTO> findAllUsers() {

		List<User> listOfUsers = userRepository.findAll();

		log.info(" ====> FIND All USER requested <==== ");

		List<UserDTO> listOfUsersDTO = new ArrayList<UserDTO>();

        for (User user: listOfUsers) {
        	listOfUsersDTO.add(userMapper.toUserDTO(user));
        }
        log.info(" ====> FIND All USER Successfull <==== ");
        return listOfUsersDTO;        
	}


	// *******************************************************************
	/**
	 * Find user by email.
	 *
	 * @param email the email
	 * @return the user DTO
	 */
	@Override
	public UserDTO findUserByEmail(String email) {

		log.info(" ====> FIND USER by EMAIL requested <==== " + email);

		User user = userRepository.findUserByEmail(email);

		log.info(" ====> Email <==== "+ user.getEmail());

		log.info(" ====> FIND USER by EMAIL Sucessfull <==== ");

		log.info(" ====> Email <==== "+ user.getEmail());

		return userMapper.toUserDTO(user);
	}
	// *******************************************************************


	/**
	 * Find user by id.
	 *
	 * @param id the id
	 * @return the user DTO
	 */
	@Override
	public UserDTO findUserById(Integer id) {

		log.info("FIND USER by ID requested ");

		User user = userRepository.findUserById(id);

        if (user == null) {
        log.info(" FIND USER by ID - user NOT FOUND ");
            throw new DataNotFoundException(
            		"ERROR: User By ID - NOT FOUND");
        }

        return userMapper.toUserDTO(user);
	}


    /**
     * Save user.
     *
     * @param userDTO the user DTO
     * @return the user DTO
     */
    //******************************************************************
    @Override
    public UserDTO saveUser(UserDTO userDTO) {

    	log.info(" ====> SAVE User requested <==== ");
 
    	User userAdd = new User();
 
    	userAdd = userRepository.save(userMapper.toUserDO(userDTO));

        log.info(" ====> User SAVED Successfully <==== ");
 
        return userMapper.toUserDTO(userAdd);
    }
    

    /**
     * User exist by id.
     *
     * @param id the id
     * @return true, if successful
     */
    //******************************************************************
    @Override
    public boolean userExistById(int id) {

    	log.info(" ====> Check VERIFY userExistById <==== ");

    	return userRepository.existsById(id);
    }
    
    //******************************************************************
    
    /**
     * Save new user.
     *
     * @param userDTO the user DTO
     * @param confirmationPass the confirmation pass
     * @return the user DTO
     */
    public UserDTO saveNewUser(UserDTO userDTO, String confirmationPass) {
 
   	log.info(" ====> SAVE NEW User requested <==== ");

        User userAdd = new User();

 		if(userDTO.getPassword().equals(confirmationPass)==false){

 			log.info(" ====> ERROR: Password MISMATCH <==== ");

 			throw new DataNotConformException("Password MISMATCH");
         }


 		if(userDTO.getPassword().equals("")==true){

         	log.info(" ====> ERROR: Password FIELD EMPTY <==== ");

         	throw new DataNotConformException("Password NEEDED");
         }

        checkAlphanumericNameEntry(userDTO);

        checkEntryForEmailFormat(userDTO);

        if (userRepository.findUserByEmail(userDTO.getEmail())==null){

            userDTO.setRoles("ROLE_USER");
            userDTO.setWalletAmount(0.0);
            userDTO.setActive(true);
            userDTO.setCreationDate(LocalDate.now());


            log.info(" ====> NEW User PASSWORD: " + userDTO.getPassword() + " <==== ");

            userAdd = userRepository.save(userMapper.toUserDO(userDTO));

            log.info(" ====> SAVE NEW User SUCCESSFULL <==== ");

            return userMapper.toUserDTO(userAdd);
        }else{
            throw new DataAlreadyExistException("Email ID not available,"
            		+ " already taken by existing user !");
        }
    }

    //******************************************************************

 	/**
     * Check entry for email format.
     *
     * @param userDTO the user DTO
     */
 	private void checkEntryForEmailFormat(UserDTO userDTO) {

 		if(checkStringEmail(userDTO.getEmail())==false){

             throw new DataNotConformException("Please enter EMAIL ID");
         }
 	}


 	//******************************************************************
 	/**
 	  * Check alphanumeric name entry.
 	  *
 	  * @param userDTO the user DTO
 	  */
 	private void checkAlphanumericNameEntry(UserDTO userDTO) {

 		if(checkStringName(userDTO.getUserName())==false ||
                         checkStringName(userDTO.getFirstName())==false){

         	log.info(" ====> ERROR: Names not alphanumeric <==== ");

             throw new DataNotConformException(
            		 "Non alphanumeric names not accepted");
         }
 	}


 	//******************************************************************
// 	/**
// 	 * @param userDTO
// 	 * @param confirmationPass
// 	 */
// 	private void checkConfirmationPasswordMatch(UserDTO userDTO, String confirmationPass) {
// 		if(userDTO.getPassword().equals(confirmationPass)==true){
//         	log.info(" ====> ERROR: Password MISMATCH <==== ");
//             throw new DataNotConformException("Password MISMATCH");
//         }
// 	}
 	 //******************************************************************

   /**
 	  * Check string name.
 	  *
 	  * @param string the string
 	  * @return true, if successful
 	  */
 	 public boolean checkStringName(String string) {

        Pattern stringNamePattern = Pattern.compile(
        		"[a-zA-Z\\+\\-\\+]{2,100}");

        return stringNamePattern.matcher(string).matches();
    }
    //******************************************************************

    /**
     * Check string email.
     *
     * @param string the string
     * @return true, if successful
     */
    public boolean checkStringEmail(String string) {
        Pattern stringNamePattern = Pattern.compile(
        		"(?:\\w|[\\-_])"
        		+ "+(?:\\.(?:\\w|[\\-_])"
        		+ "+)*\\@(?:\\w|[\\-_])"
        		+ "+(?:\\.(?:\\w|[\\-_])+)+");
        return stringNamePattern.matcher(string).matches();
    }


}
