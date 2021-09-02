package com.paymybuddy.webapp.dto;

import java.time.LocalDate;

import com.paymybuddy.webapp.model.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Instantiates a new transaction DTO.
 */
@NoArgsConstructor
public class TransactionDTO {

	/**
	 * Sets the id transaction.
	 *
	 * @param idTransaction the new id transaction
	 */
 /**
  * Gets the id transaction.
  *
  * @return the id transaction
  */
 @Getter @Setter
	private int idTransaction;

	/** The payer. */
	private User payer;

	/** The beneficiary. */
	private User beneficiary;

	/** The amount. */
	private double amount;

	/** The description. */
	private String description;

	/** The creation date. */
	private LocalDate creationDate;

	/** The commision. */
	private double commision;
	
	

	/**
	 * Instantiates a new transaction DTO.
	 *
	 * @param payer the payer
	 * @param beneficiary the beneficiary
	 * @param amount the amount
	 * @param description the description
	 * @param creationDate the creation date
	 * @param commision the commision
	 */
	public TransactionDTO(
			User payer,
			User beneficiary,
			double amount,
			 String description) {
		super();
		this.payer = payer;
		this.beneficiary = beneficiary;
		this.amount = amount;
		this.description = description;
	}
	
//	public TransactionDTO(
//			User payer,
//			User beneficiary,
//			double amount,
//			 String description,
//			 LocalDate creationDate,
//			 double commision) {
//		super();
//		this.payer = payer;
//		this.beneficiary = beneficiary;
//		this.amount = amount;
//		this.description = description;
//		this.creationDate = creationDate;
//		this.commision = commision;
//	}


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
	 * Gets the beneficiary.
	 *
	 * @return the beneficiary
	 */
	public User getBeneficiary() {
		return beneficiary;
	}

	/**
	 * Sets the beneficiary.
	 *
	 * @param beneficiary the new beneficiary
	 */
	public void setBeneficiary(User beneficiary) {
		this.beneficiary = beneficiary;
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
	 * @param createDate the new creation date
	 */
	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * Gets the amount.
	 *
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * Sets the amount.
	 *
	 * @param amount the new amount
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the commision.
	 *
	 * @return the commision
	 */
	public double getCommision() {
		return commision;
	}

	/**
	 * Sets the commision.
	 *
	 * @param commision the new commision
	 */
	public void setCommision(double commision) {
		this.commision = commision;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
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
