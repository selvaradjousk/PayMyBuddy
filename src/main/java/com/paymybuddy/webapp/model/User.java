package com.paymybuddy.webapp.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The Class User.
 */
@Entity
@Table(name = "user")
public class User {

	/** The Constant VARIABLE_LENGTH_10. */
	private static final int VARIABLE_LENGTH_10 = 10;
	
	/** The Constant VARIABLE_LENGTH_50. */
	private static final int VARIABLE_LENGTH_50 = 50;
	
	/** The Constant VARIABLE_LENGTH_100. */
	private static final int VARIABLE_LENGTH_100 = 100;

	/** The id. */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user", nullable = false)
    private int id;

    /** The user name. */
    @Column(name = "user_name", length = VARIABLE_LENGTH_50, nullable = false)
    private String userName;

    /** The first name. */
    @Column(name = "first_name", length = VARIABLE_LENGTH_50)
    private String firstName;

    /** The last name. */
    @Column(name = "last_name", length = VARIABLE_LENGTH_50)
    private String lastName;

    /** The email. */
    @Column(name = "email", length = VARIABLE_LENGTH_50,
    		unique = true, nullable = false)
    private String email;

    /** The password. */
    @Column(name = "password", length = VARIABLE_LENGTH_100, nullable = false)
    private String password;

	/** The creation date. */
	@Column(name = "creation_date", nullable = false)
    private LocalDate creationDate = LocalDate.now();

    /** The modification date. */
    @Column(name = "modification_date")
    private LocalDate modificationDate = LocalDate.now();

    /** The roles. */
    @Column(name = "roles", length = VARIABLE_LENGTH_10, nullable = false)
    private String roles;

    /** The active. */
    @Column(name = "active")
    private boolean active;

    /** The wallet amount. */
    @Column(name = "wallet_amount", length = VARIABLE_LENGTH_10)
    private Double  walletAmount;

    /**
     * Instantiates a new user.
     */
    public User() {
    }

    /**
     * Instantiates a new user.
     *
     * @param idd the idd
     * @param userNamee the user namee
     * @param firstNamee the first namee
     * @param lastNamee the last namee
     * @param emaill the emaill
     * @param passwordd the passwordd
     * @param creationDatee the creation datee
     * @param modificationDatee the modification datee
     * @param roless the roless
     * @param activee the activee
     * @param walletAmountt the wallet amountt
     */
    public User(
    		final int idd,
    		final String userNamee,
    		final String firstNamee,
    		final String lastNamee,
    		final String emaill,
    		final String passwordd,
    		final LocalDate creationDatee,
    		final LocalDate modificationDatee,
    		final String roless,
    		final boolean activee,
    		final Double walletAmountt) {
		super();
		this.id = idd;
		this.userName = userNamee;
		this.firstName = firstNamee;
		this.lastName = lastNamee;
		this.email = emaill;
		this.password = passwordd;
		this.creationDate = creationDatee;
		this.modificationDate = modificationDatee;
		this.roles = roless;
		this.active = activee;
		this.walletAmount = walletAmountt;
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
     * @param lastNamee the new last name
     */
    public void setLastName(final String lastNamee) {
        this.lastName = lastNamee;
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
     * @param modificationDatee the new modification date
     */
    public void setModificationDate(final LocalDate modificationDatee) {
        this.modificationDate = modificationDatee;
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
