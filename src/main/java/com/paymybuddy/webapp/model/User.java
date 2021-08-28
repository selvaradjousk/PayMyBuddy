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



@Entity
@Table(name="user")
public class User {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id_user", nullable = false)
    private int id;

    @Column(name= "user_name", length = 50, nullable = false)
    private String userName;

    @Column(name= "first_name", length = 50)
    private String firstName;

    @Column(name= "last_name", length = 50)
    private String lastName;

    @Column(name= "email", length = 50, unique = true, nullable = false)
    private String email;

    @Column(name= "password", length = 50, nullable = false)
    private String password;

	@Column(name= "creation_date", nullable = false)
    private LocalDate creationDate = LocalDate.now();

    @Column(name= "modification_date")
    private LocalDate modificationDate = LocalDate.now();

    @Column(name= "roles", length = 10, nullable = false)
    private String roles;

    @Column(name= "active")
    private boolean active;

    @Column(name= "wallet_amount", length = 10)
    private Double  walletAmount  ;

    public User() {
    }
    

    public User(int id, String userName, String firstName, String lastName, String email, String password,
			LocalDate creationDate, LocalDate modificationDate, String roles, boolean active, Double walletAmount) {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public LocalDate getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(LocalDate modificationDate) {
        this.modificationDate = modificationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }


    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Double getWalletAmount() {
        return walletAmount; 
    }

    public void setWalletAmount(Double walletAmount) {
    	 this.walletAmount = walletAmount;
    }
    

    @Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", password=" + password + ", creationDate=" + creationDate
				+ ", modificationDate=" + modificationDate + ", roles=" + roles + ", active=" + active
				+ ", walletAmount=" + walletAmount + "]";
	}
}
