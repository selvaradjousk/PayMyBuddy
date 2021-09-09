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

	/** The idTransaction. */
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
	 * @param payerr the payerr
	 * @param beneficiaryy the beneficiaryy
	 * @param amountt the amountt
	 * @param descriptionn the descriptionn
	 */
	public TransactionDTO(
			final User payerr,
			final User beneficiaryy,
			final double amountt,
			final String descriptionn) {
		super();
		this.payer = payerr;
		this.beneficiary = beneficiaryy;
		this.amount = amountt;
		this.description = descriptionn;
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
	 * @param payerr the new payer
	 */
	public void setPayer(final User payerr) {
		this.payer = payerr;
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
	 * @param beneficiaryy the new beneficiary
	 */
	public void setBeneficiary(final User beneficiaryy) {
		this.beneficiary = beneficiaryy;
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
	 * @param createDatee the new creation date
	 */
	public void setCreationDate(final LocalDate createDatee) {
		this.creationDate = createDatee;
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
	 * @param amountt the new amount
	 */
	public void setAmount(final double amountt) {
		this.amount = amountt;
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
	 * @param descriptionn the new description
	 */
	public void setDescription(final String descriptionn) {
		this.description = descriptionn;
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
	 * @param commisionn the new commision
	 */
	public void setCommision(final double commisionn) {
		this.commision = commisionn;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "TransactionDTO ["
				+ "idTransaction=" + idTransaction
				+ ", payer=" + payer
				+ ", beneficiary=" + beneficiary
				+ ", amount=" + amount
				+ ", description=" + description
				+ ", creationDate=" + creationDate
				+ ", commision=" + commision + "]";
	}
}
