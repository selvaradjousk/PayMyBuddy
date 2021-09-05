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


/**
 * The Class Contact.
 */
@Entity
@Table(name="contact")
public class Contact {

    /** The id contact. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id_contact", nullable = false)
    private Integer idContact;

	/** The creation date. */
	@Column(name= "creation_date", updatable=false)
    private LocalDate creationDate = LocalDate.now();

    /** The payer. */
    @ManyToOne(optional = false)
    @JoinColumn(name= "payer_id")
    private User payer;

    /** The contact. */
    @ManyToOne(optional = false)
    @JoinColumn(name= "contact_id")
    private User contact;

      /**
       * Instantiates a new contact.
       */
      public Contact() {
        super();
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
		return "Contact ["
				+ "idContact=" + idContact
				+ ", creationDate=" + creationDate
				+ ", payer=" + payer
				+ ", contact=" + contact + "]";
	}
}
