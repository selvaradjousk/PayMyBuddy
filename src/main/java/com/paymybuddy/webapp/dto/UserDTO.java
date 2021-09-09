package com.paymybuddy.webapp.dto;

import java.time.LocalDate;

import lombok.NoArgsConstructor;

/**
 * The Class UserDTO.
 */
@NoArgsConstructor
public class UserDTO {

	/** The id. */
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
     * @param userNamee the user namee
     * @param firstNamee the first namee
     * @param emaill the emaill
     * @param passwordd the passwordd
     */
    public UserDTO(
    		final String userNamee,
    		final String firstNamee,
    		final String emaill,
    		final String passwordd) {

		this.userName = userNamee;
		this.firstName = firstNamee;
		this.email = emaill;
		this.password = passwordd;
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
     * @param idd the new id
     */
    public void setId(final int idd) {
        this.id = idd;
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
     * @param userNamee the new user name
     */
    public void setUserName(final String userNamee) {
        this.userName = userNamee;
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
     * @param firstNamee the new first name
     */
    public void setFirstName(final String firstNamee) {
        this.firstName = firstNamee;
    }


    //**********************************************************************
    //**********************************************************************
    //**********************************************************************
    /**
     * Sets the last name.
     *
     * @param lastNamee the new last name
     */
    public void setLastName(final String lastNamee) {
        this.lastName = lastNamee;
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
     * @param emaill the new email
     */
    public void setEmail(final String emaill) {
        this.email = emaill;
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
     * @param passwordd the new password
     */
    public void setPassword(final String passwordd) {
        this.password = passwordd;
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
     * @param activee the new active
     */
    public void setActive(final boolean activee) {
        this.active = activee;
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
     * @param roless the new roles
     */
    public void setRoles(final String roless) {
        this.roles = roless;
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
     * @param walletAmountt the new wallet amount
     */
    public void setWalletAmount(final Double walletAmountt) {
        this.walletAmount = walletAmountt;
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
     * @param creationDatee the new creation date
     */
    public void setCreationDate(final LocalDate creationDatee) {
        this.creationDate = creationDatee;
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
     * @param modificationDatee the new modification date
     */
    public void setModificationDate(final LocalDate modificationDatee) {
        this.modificationDate = modificationDatee;
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
