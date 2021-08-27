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


//**************************** TODOs LIST ***********************************

//Method: c
//--> Checkstyle => java doc, hidden fields, blank spaces
//--> validation field constraints -> not null, not blank, unique,
//--> updating ER (Entity Relationship types) - associations




@Entity
@Table(name="transaction")
public class Transaction {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name= "id_transaction", nullable = false)
	private int idTransaction;
	
	@ManyToOne
	@JoinColumn(name = "payer_id", nullable = false)
	private User payer;
	
	@ManyToOne
	@JoinColumn(name= "beneficiary_id", nullable = false)
	private User beneficiary;
	
	@Column(name= "amount", nullable = false)
	private double amount;
	
	@Column(name= "description", length = 150,nullable = false)
	private String description;

	@Column(name= "date", nullable = false)
	private LocalDate creationDate = LocalDate.now();

	@Column(name= "commision")
	private double commision;
	
	public Transaction() {
		super();
	}
	

	public Transaction(int idTransaction, User payer, User beneficiary, double amount, String description,
			LocalDate creationDate, double commision) {
		super();
		this.idTransaction = idTransaction;
		this.payer = payer;
		this.beneficiary = beneficiary;
		this.amount = amount;
		this.description = description;
		this.creationDate = creationDate;
		this.commision = commision;
	}

	public int getIdTransaction() {
		return idTransaction;
	}

	public void setIdTransaction(int idTransaction) {
		this.idTransaction = idTransaction;
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
		return "Transaction [idTransaction=" + idTransaction + ", payer=" + payer + ", beneficiary=" + beneficiary
				+ ", amount=" + amount + ", description=" + description + ", creationDate=" + creationDate
				+ ", commision=" + commision + "]";
	}

}
