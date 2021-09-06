package com.paymybuddy.webapp.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
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

/**
 * The Class UserServiceImpl.
 */
@Log4j2
@Service
public class UserServiceImpl implements IUserService {


	/** The user repository. */
	@Autowired
	private UserRepository userRepository;

    /** The password encoder. */
    @Autowired
    PasswordEncoder passwordEncoder;

    /** The user mapper. */
    private UserMapper userMapper = new UserMapper();


	/**
	 * Instantiates a new user service impl.
	 *
	 * @param userRepository the user repository
	 * @param passwordEncoder the password encoder
	 * @param userMapper the user mapper
	 */
	public UserServiceImpl(
			UserRepository userRepository,
			PasswordEncoder passwordEncoder,
			UserMapper userMapper) {

		super();
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.userMapper = userMapper;
	}


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

        log.info(" ====> FIND All USER Successfull"
        		+ " - size: " + listOfUsers.size());

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
	public UserDTO findUserByEmail(final String email) {

		log.info(" ====> FIND USER by EMAIL requested <==== " + email);

		User user = userRepository.findUserByEmail(email);

		log.info(" ====> FIND USER by EMAIL Sucessfull <==== ");

		log.info(" ====> Email <==== " + user.getEmail());

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
	public UserDTO findUserById(final Integer id) {

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
    public UserDTO saveUser(final UserDTO userDTO) {

    	log.info(" ====> SAVE User requested <==== ");

    	User userAdd = new User();

    	userAdd = userRepository.save(userMapper.toUserDO(userDTO));

        log.info(" ====> User SAVED Successfully : " + userAdd);

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
    public boolean userExistById(final int id) {

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
    public UserDTO saveNewUser(
    		final UserDTO userDTO,
    		final String confirmationPass) {

       	log.info(" ====> SAVE NEW User requested <==== ");
       	log.info(" ====> confirmation password <==== "+ confirmationPass);
       	log.info(" ====> password <==== "+ userDTO.getPassword());

        User userAdd = new User();

//      checkConfirmationPasswordMatch(userDTO, confirmationPass);
        //*********************************************************************          
        //*********************************************************************
 		if (userDTO.getPassword().equals(confirmationPass) == false){

 		   	log.info(" ====> confirmation password <==== "+ confirmationPass);
 		   	log.info(" ====> password <==== "+ userDTO.getPassword());
 			log.info(" ====> ERROR: Password MISMATCH <==== ");

 			throw new DataNotConformException("Password MISMATCH");
         }


 		if (userDTO.getPassword().equals("") == true){

         	log.info(" ====> ERROR: Password FIELD EMPTY <==== ");

         	throw new DataNotConformException("Password NEEDED");
         }

        checkAlphanumericNameEntry(userDTO);

        checkEntryForEmailFormat(userDTO);

        if (userRepository.findUserByEmail(userDTO.getEmail()) == null) {

            userDTO.setRoles("ROLE_USER");
            userDTO.setWalletAmount(0.0);
            userDTO.setActive(true);
            userDTO.setCreationDate(LocalDate.now());

            //*****************************************************************           
            //*****************************************************************
            userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            //*****************************************************************
            //*****************************************************************

            log.info(" ====> NEW User PASSWORD: "
            + userDTO.getPassword() + " <==== ");

            userAdd = userRepository.save(userMapper.toUserDO(userDTO));

            log.info(" ====> SAVE NEW User SUCCESSFULL"
            		+ " - userAdd : " + userAdd);

            return userMapper.toUserDTO(userAdd);
        } else {
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
 	private void checkEntryForEmailFormat(final UserDTO userDTO) {

 		if (checkStringEmail(userDTO.getEmail()) == false) {

             throw new DataNotConformException("Please enter EMAIL ID");
         }
 	}


 	//******************************************************************
 	/**
 	  * Check alphanumeric name entry.
 	  *
 	  * @param userDTO the user DTO
 	  */
 	private void checkAlphanumericNameEntry(final UserDTO userDTO) {

 		if (checkStringName(userDTO
 				.getUserName()) == false ||
                         checkStringName(userDTO
                        		 .getFirstName()) == false) {

         	log.info(" ====> ERROR: Names not alphanumeric <==== ");

             throw new DataNotConformException(
            		 "Non alphanumeric names not accepted");
         }

 		log.info(" ==== Names are ok - alphanumeric <==== ");
 	}


 	//******************************************************************
// 	/**
// 	 * @param userDTO
// 	 * @param confirmationPass
// 	 */
// 	private void checkConfirmationPasswordMatch
 	// (UserDTO userDTO, String confirmationPass) {
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
 	 public boolean checkStringName(final String string) {

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
    public boolean checkStringEmail(final String string) {
        Pattern stringNamePattern = Pattern.compile(
        		"(?:\\w|[\\-_])"
        		+ "+(?:\\.(?:\\w|[\\-_])"
        		+ "+)*\\@(?:\\w|[\\-_])"
        		+ "+(?:\\.(?:\\w|[\\-_])+)+");
        return stringNamePattern.matcher(string).matches();
    }

    //******************************************************************
    @Override
    public Page<UserDTO> listUserNotBuddy(UserDTO userDTO, String mc,Pageable pageable) {
        log.info(" ====> FIND LIST USER Not Buddy requested <==== ");
        User payer = userMapper.toUserDO(userDTO);
        Page<User> pagesUsers = userRepository.listUserNotBuddy(payer,mc,pageable);
        log.info(" ====> pagesUsers <==== " + pagesUsers);
        Page<UserDTO> pagesUsersDTO= pagesUsers.map(new Function<User, UserDTO>() {
            @Override
            public UserDTO apply(User user) {
                UserDTO userDTO = new UserDTO();
                userDTO = userMapper.toUserDTO(user);
                log.info(" ====> userDTO <==== " + userDTO.toString());
                return userDTO;
            }
        });
        log.info(" ====> FIND LIST USER Pages returned <==== ");
        log.info(" ====> pagesUsersDTO <==== " + pagesUsersDTO.toString());
        return pagesUsersDTO;
    }
    //******************************************************************


    

}
