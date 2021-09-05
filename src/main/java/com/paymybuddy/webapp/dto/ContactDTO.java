package com.paymybuddy.webapp.dto;

import java.time.LocalDate;

import com.paymybuddy.webapp.model.User;

import lombok.NoArgsConstructor;

/**
 * The Class ContactDTO.
 */

/**
 * Instantiates a new contact DTO.
 */
@NoArgsConstructor
public class ContactDTO {

	/** The id contact. */
    private Integer idContact;

    /** The creation date. */
    private LocalDate creationDate;

    /** The payer. */
    private User payer;

    /** The contact. */
    private User contact;

      
	/**
	 * Instantiates a new contact DTO.
	 *
	 * @param creationDate the creation date
	 * @param payer the payer
	 * @param contact the contact
	 */
	public ContactDTO(
			LocalDate creationDate, User payer, User contact) {
  		super();
		this.creationDate = creationDate;
  		this.payer = payer;
  		this.contact = contact;
  	}

	   /**
     * Gets the id contact.
     *
     * @return the id contact
     */
    public Integer getIdContact() {
  		return idContact;
  	}


    /**
     * Sets the id contact.
     *
     * @param idContact the new id contact
     */
    public void setIdContact(Integer idContact) {
        this.idContact = idContact;
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
     * Gets the payer.
     *
     * @return the payer
     */
    public User getPayer() {
        return payer;
    }

    /**
     * Sets the payer.
     *
     * @param payer the new payer
     */
    public void setPayer(User payer) {
        this.payer = payer;
    }

    /**
     * Gets the contact.
     *
     * @return the contact
     */
    public User getContact() {
        return contact;
    }

    /**
     * Sets the contact.
     *
     * @param contact the new contact
     */
    public void setContact(User contact) {
        this.contact = contact;
    }

    /**
     * To string.
     *
     * @return the string
     */
    @Override
	public String toString() {
		return "Contact [idContact=" + idContact
				+ ", creationDate=" + creationDate
				+ ", payer=" + payer + ", contact="
				+ contact + "]";
	}
}
