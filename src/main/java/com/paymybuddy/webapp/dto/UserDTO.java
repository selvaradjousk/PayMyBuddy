package com.paymybuddy.webapp.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The Class UserDTO.
 */
@NoArgsConstructor
public class UserDTO {

	
	// **************************** TODOs LIST ***********************************
	
	// Method:
	// --> https://www.baeldung.com/entity-to-and-from-dto-for-a-java-spring-application
	//
	// --> An outside entity (a "mapper" or "assembler") is called to create a DTO from 
	// a Domain Object. Normally there is an ORM on the domain object side.
	// The downside of this is that the "mapper" tends to get extremely complex 
	// for any real situation and can be very fragile
	// --> Entity is class mapped to table. Dto is class mapped to "view" layer mostly.
	// --> What needed to store is entity & which needed to 'show' on web page is DTO
	// --> https://www.amitph.com/spring-entity-to-dto/
	


	/** The id. */
	@Setter @Getter
    private int id;

    /** The user name. */
    private String userName;

    private String firstName;

	/** The last name. */
	@Setter @Getter
    private String lastName;

    /** The email. */
    private String email;

    /** The password. */
    private String password;

	/** The creation date. */
	@Setter @Getter
    private LocalDate creationDate;

	@Setter @Getter
    private LocalDate modificationDate;

	/** The roles. */
	@Setter @Getter
    private String roles;

    /** The active. */
    private boolean active;

	/** The wallet amount. */
	@Setter @Getter
    private Double  walletAmount;	

    /**
     * Instantiates a new user DTO.
     *
     * @param userName the user name
     * @param firstName the first name
     * @param email the email
     * @param password the password
     */
    public UserDTO(String userName, String firstName, String email, String password) {
		this.userName = userName;
		this.firstName = firstName;
		this.email = email;
		this.password = password;
	}

//	public UserDTO(int id, String userName, String firstName,
    //		String lastName, String email, String password,
//			LocalDate creationDate, LocalDate modificationDate,
    //		String roles, boolean active, Double walletAmount) {
//		super();
//		this.id = id;
//		this.userName = userName;
//		this.firstName = firstName;
//		this.lastName = lastName;
//		this.email = email;
//		this.password = password;
//		this.creationDate = creationDate;
//		this.modificationDate = modificationDate;
//		this.roles = roles;
//		this.active = active;
//		this.walletAmount = walletAmount;
//	}	
	
    /**
 * Gets the user name.
 *
 * @return the user name
 */
public String getUserName() {
        return userName;
    }

    /**
     * Sets the user name.
     *
     * @param userName the new user name
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Gets the first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the lastt name.
     *
     * @param firstName the new lastt name
     */
    public void setLasttName(String firstName) {
        this.firstName = firstName;
    }
    
    /**
     * Gets the last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return firstName;
    }

    /**
     * Sets the first name.
     *
     * @param firstName the new first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email.
     *
     * @param email the new email
     */
    public void setEmail(String email) {
        this.email = email;
    }


    /**
     * Gets the password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     *
     * @param password the new password
     */
    public void setPassword(String password) {
        this.password = password;
    }
   
    /**
     * Checks if is active.
     *
     * @return true, if is active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Sets the active.
     *
     * @param active the new active
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * To string.
     *
     * @return the string
     */
    @Override
	public String toString() {
		return "User [id=" + id
				+ ", userName=" + userName
				+ ", firstName=" + firstName
				+ ", lastName=" + lastName
				+ ", email=" + email
				+ ", password=" + password
				+ ", creationDate=" + creationDate
				+ ", modificationDate=" + modificationDate
				+ ", roles=" + roles
				+ ", active=" + active
				+ ", walletAmount=" + walletAmount + "]";
	}
}
