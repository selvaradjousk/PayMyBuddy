package com.paymybuddy.webapp.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
	


	@Setter @Getter
    private int id;

    private String userName;

    private String firstName;

	@Setter @Getter
    private String lastName;

    private String email;

    private String password;

	@Setter @Getter
    private LocalDate creationDate;

	@Setter @Getter
    private LocalDate modificationDate;

	@Setter @Getter
    private String roles;

    private boolean active;

	@Setter @Getter
    private Double  walletAmount;	

    public UserDTO(String userName, String firstName, String email, String password) {
		this.userName = userName;
		this.firstName = firstName;
		this.email = email;
		this.password = password;
	}

//	public UserDTO(int id, String userName, String firstName, String lastName, String email, String password,
//			LocalDate creationDate, LocalDate modificationDate, String roles, boolean active, Double walletAmount) {
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
	
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLasttName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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
   
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

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
