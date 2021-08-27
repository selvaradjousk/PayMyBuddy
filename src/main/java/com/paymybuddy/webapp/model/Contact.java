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

@Entity
@Table(name="contact")
public class Contact {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id_contact", nullable = false)
    private Integer idContact;

	@Column(name= "creation_date", updatable=false)
    private LocalDate creationDate = LocalDate.now();

    @ManyToOne(optional = false)
    @JoinColumn(name= "payer_id")
    private User payer;

    @ManyToOne(optional = false)
    @JoinColumn(name= "contact_id")
    private User contact;


      public Contact() {
        super();
    }
      
	public Contact(Integer idContact, LocalDate creationDate, User payer, User contact) {
  		super();
  		this.idContact = idContact;
  		this.creationDate = creationDate;
  		this.payer = payer;
  		this.contact = contact;
  	}

    public Integer getIdContact() {
  		return idContact;
  	}


	public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public void setIdContact(Integer idContact) {
        this.idContact = idContact;
    }

    public User getPayer() {
        return payer;
    }

    public void setPayer(User payer) {
        this.payer = payer;
    }

    public User getContact() {
        return contact;
    }

    public void setContact(User contact) {
        this.contact = contact;
    }

    @Override
	public String toString() {
		return "Contact [idContact=" + idContact + ", creationDate=" + creationDate + ", payer=" + payer + ", contact="
				+ contact + "]";
	}
}
