package com.paymybuddy.webapp.dto;

import java.time.LocalDate;

import com.paymybuddy.webapp.model.User;

import lombok.NoArgsConstructor;

/**
 * The Class ContactDTO.
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
	 * @param creationDatee the creation datee
	 * @param payere the payere
	 * @param contacte the contacte
	 */
	public ContactDTO(
			final LocalDate creationDatee,
			final User payere,
			final User contacte) {
  		super();
		this.creationDate = creationDatee;
  		this.payer = payere;
  		this.contact = contacte;
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
     * @param idContactt the new id contact
     */
    public void setIdContact(final Integer idContactt) {
        this.idContact = idContactt;
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
     * @param creationDater the new creation date
     */
    public void setCreationDate(final LocalDate creationDater) {
        this.creationDate = creationDater;
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
     * @param payerr the new payer
     */
    public void setPayer(final User payerr) {
        this.payer = payerr;
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
     * @param contactt the new contact
     */
    public void setContact(final User contactt) {
        this.contact = contactt;
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
