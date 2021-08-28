package com.paymybuddy.webapp.dto;

import java.time.LocalDate;

import com.paymybuddy.webapp.model.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class ContactDTO {

	@Getter @Setter
    private Integer idContact;

    private LocalDate creationDate;

    private User payer;

    private User contact;

      
	public ContactDTO(LocalDate creationDate, User payer, User contact) {
  		super();
		this.creationDate = creationDate;
  		this.payer = payer;
  		this.contact = contact;
  	}

	public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
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
		return "Contact [idContact=" + idContact
				+ ", creationDate=" + creationDate
				+ ", payer=" + payer + ", contact="
				+ contact + "]";
	}
}
