package com.paymybuddy.webapp.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


//**************************** TODOs LIST ***********************************

//Method: c
//--> Checkstyle => java doc, hidden fields, blank spaces
//--> validation field constraints -> not null, not blank, unique,
//--> updating ER (Entity Relationship types) - associations



/**
 * The Class User.
 */
@Entity
@Table(name="user")
public class User {

	/** The id. */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id_user", nullable = false)
    private int id;

    /** The user name. */
    @Column(name= "user_name", length = 50, nullable = false)
    private String userName;

    /** The first name. */
    @Column(name= "first_name", length = 50)
    private String firstName;

    /** The last name. */
    @Column(name= "last_name", length = 50)
    private String lastName;

    /** The email. */
    @Column(name= "email", length = 50, unique = true, nullable = false)
    private String email;

    /** The password. */
    @Column(name= "password", length = 50, nullable = false)
    private String password;

	/** The creation date. */
	@Column(name= "creation_date", nullable = false)
    private LocalDate creationDate = LocalDate.now();

    /** The modification date. */
    @Column(name= "modification_date")
    private LocalDate modificationDate = LocalDate.now();

    /** The roles. */
    @Column(name= "roles", length = 10, nullable = false)
    private String roles;

    /** The active. */
    @Column(name= "active")
    private boolean active;

    /** The wallet amount. */
    @Column(name= "wallet_amount", length = 10)
    private Double  walletAmount  ;

    /**
     * Instantiates a new user.
     */
    public User() {
    }
    

    /**
     * Instantiates a new user.
     *
     * @param id the id
     * @param userName the user name
     * @param firstName the first name
     * @param lastName the last name
     * @param email the email
     * @param password the password
     * @param creationDate the creation date
     * @param modificationDate the modification date
     * @param roles the roles
     * @param active the active
     * @param walletAmount the wallet amount
     */
    public User(int id,
    		String userName,
    		String firstName,
    		String lastName,
    		String email,
    		String password,
			LocalDate creationDate,
			LocalDate modificationDate,
			String roles,
			boolean active,
			Double walletAmount) {
		super();
		this.id = id;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.creationDate = creationDate;
		this.modificationDate = modificationDate;
		this.roles = roles;
		this.active = active;
		this.walletAmount = walletAmount;
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

    /**
     * Gets the last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name.
     *
     * @param lastName the new last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
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
     * Gets the creation date.
     *
     * @return the creation date
     */
    public LocalDate getCreationDate() {
        return creationDate;
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
     * Sets the creation date.
     *
     * @param creationDate the new creation date
     */
    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
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
     * To string.
     *
     * @return the string
     */
    @Override
	public String toString() {
		return "User ["
				+ "id=" + id
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
