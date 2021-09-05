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

	/** The id. */
	@Setter @Getter
    private int id;

    /** The user name. */
    private String userName;

    /** The first name. */
    private String firstName;

	/** The last name. */
    private String lastName;

    /** The email. */
    private String email;

    /** The password. */
    private String password;

	/** The creation date. */
    private LocalDate creationDate;

    /** The modification date. */
    private LocalDate modificationDate;

	/** The roles. */
    private String roles;

    /** The active. */
    private boolean active;

	/** The wallet amount. */
    private Double  walletAmount;	

    /**
     * Instantiates a new user DTO.
     *
     * @param userName the user name
     * @param firstName the first name
     * @param email the email
     * @param password the password
     */
    public UserDTO(
    		String userName,
    		String firstName,
    		String email,
    		String password) {
    	
		this.userName = userName;
		this.firstName = firstName;
		this.email = email;
		this.password = password;
	}

	
    /**
     * Gets the id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(int id) {
        this.id = id;
    }

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
     * Sets the first name.
     *
     * @param firstName the new first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    //**********************************************************************
    //**********************************************************************
    //**********************************************************************
    /**
     * Sets the last name.
     *
     * @param firstName the new last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
 

    /**
     * Gets the last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }


    //**********************************************************************
    //**********************************************************************
    //**********************************************************************


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
     * Gets the roles.
     *
     * @return the roles
     */
    public String getRoles() {
        return roles;
    }

    /**
     * Sets the roles.
     *
     * @param roles the new roles
     */
    public void setRoles(String roles) {
        this.roles = roles;
    }

    /**
     * Gets the wallet amount.
     *
     * @return the wallet amount
     */
    public Double getWalletAmount() {
        return walletAmount;
    }

    /**
     * Sets the wallet amount.
     *
     * @param walletAmount the new wallet amount
     */
    public void setWalletAmount(Double walletAmount) {
        this.walletAmount = walletAmount;
    }

    /**
     * Gets the creation date.
     *
     * @return the creation date
     */
    public LocalDate getCreationDate() {
        return creationDate;
    }

    /**
     * Sets the creation date.
     *
     * @param creationDate the new creation date
     */
    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Gets the modification date.
     *
     * @return the modification date
     */
    public LocalDate getModificationDate() {
        return modificationDate;
    }

    /**
     * Sets the modification date.
     *
     * @param modificationDate the new modification date
     */
    public void setModificationDate(LocalDate modificationDate) {
        this.modificationDate = modificationDate;
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
