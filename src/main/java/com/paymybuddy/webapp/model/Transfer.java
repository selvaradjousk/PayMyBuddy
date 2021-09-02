package com.paymybuddy.webapp.model;


import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

//**************************** TODOs LIST *******************************

//Method: c
//--> Checkstyle => java doc, hidden fields, blank spaces
//--> validation field constraints -> not null, not blank, unique,
//--> updating ER (Entity Relationship types) - associations

/**
 * The Class Transfer.
 */
@Entity
@Table(name="transfer")
public class Transfer {

	/** The id transfer. */
	@Id
    
    /**
     * Gets the id transfer.
     *
     * @return the id transfer
     */
    @Getter 
 /**
  * Sets the id transfer.
  *
  * @param idTransfer the new id transfer
  */
 @Setter
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name= "id_transfer", nullable = false)
    private int idTransfer;

    /**
     * Gets the rib.
     *
     * @return the rib
     */
    @Getter /**
  * Sets the rib.
  *
  * @param rib the new rib
  */
 @Setter
    @Column(name= "rib", nullable = false)
    private String rib;

    /**
     * Gets the creates the date.
     *
     * @return the creates the date
     */
    @Getter /**
  * Sets the creates the date.
  *
  * @param createDate the new creates the date
  */
 @Setter
    @Column(name= "date", nullable = false)
    private LocalDate createDate = LocalDate.now();

    /**
     * Gets the amount.
     *
     * @return the amount
     */
    @Getter /**
  * Sets the amount.
  *
  * @param amount the new amount
  */
 @Setter
    @Column(name= "amount", nullable = false)
    private double amount;

    /**
     * Gets the type.
     *
     * @return the type
     */
    @Getter /**
  * Sets the type.
  *
  * @param type the new type
  */
 @Setter
    @Column(name="type", columnDefinition = "enum('CREDIT','DEBIT')")
    private String type;

    /**
     * Gets the user.
     *
     * @return the user
     */
    @Getter /**
  * Sets the user.
  *
  * @param user the new user
  */
 @Setter
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * Instantiates a new transfer.
     */
    public Transfer() {
    }

	/**
	 * Instantiates a new transfer.
	 *
	 * @param rib the rib
	 * @param createDate the create date
	 * @param amount the amount
	 * @param type the type
	 * @param user the user
	 */
	public Transfer(String rib, LocalDate createDate,
			double amount, String type,  User user ) {
		super();
		this.rib = rib;
		this.createDate = createDate;
		this.amount = amount;
		this.type = type;
		this.user = user;
	}
}