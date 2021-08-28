package com.paymybuddy.webapp.dto;

import java.time.LocalDate;

import com.paymybuddy.webapp.model.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class TransactionDTO {

	@Setter @Getter
	private int idTransaction;

	private User payer;

	private User beneficiary;

	private double amount;

	private String description;

	private LocalDate creationDate;

	private double commision;
	
	

	public TransactionDTO(User payer, User beneficiary, double amount,
			 String description, LocalDate creationDate, double commision) {
		super();
		this.payer = payer;
		this.beneficiary = beneficiary;
		this.amount = amount;
		this.description = description;
		this.creationDate = creationDate;
		this.commision = commision;
	}


	public User getPayer() {
		return payer;
	}

	public void setPayer(User payer) {
		this.payer = payer;
	}

	public User getBeneficiary() {
		return beneficiary;
	}

	public void setBeneficiary(User beneficiary) {
		this.beneficiary = beneficiary;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate createDate) {
		this.creationDate = createDate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getCommision() {
		return commision;
	}

	public void setCommision(double commision) {
		this.commision = commision;
	}

	@Override
	public String toString() {
		return "Transaction ["
				+ "idTransaction=" + idTransaction
				+ ", payer=" + payer
				+ ", beneficiary=" + beneficiary
				+ ", amount=" + amount
				+ ", description=" + description
				+ ", creationDate=" + creationDate
				+ ", commision=" + commision + "]";
	}
}
