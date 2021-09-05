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
 * The Class Transaction.
 */
@Entity
@Table(name="transaction")
public class Transaction {

	/** The id transaction. */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name= "id_transaction", nullable = false)
	private int idTransaction;

	/** The payer. */
	@ManyToOne
	@JoinColumn(name = "payer_id", nullable = false)
	private User payer;

	/** The beneficiary. */
	@ManyToOne
	@JoinColumn(name= "beneficiary_id", nullable = false)
	private User beneficiary;

	/** The amount. */
	@Column(name= "amount", nullable = false)
	private double amount;

	/** The description. */
	@Column(name= "description", length = 150,nullable = false)
	private String description;

	/** The creation date. */
	@Column(name= "date", nullable = false)
	private LocalDate creationDate = LocalDate.now();

	/** The commision. */
	@Column(name= "commision")
	private double commision;

	/**
	 * Instantiates a new transaction.
	 */
	public Transaction() {
		super();
	}


	/**
	 * Instantiates a new transaction.
	 *
	 * @param idTransaction the id transaction
	 * @param payer the payer
	 * @param beneficiary the beneficiary
	 * @param amount the amount
	 * @param description the description
	 * @param creationDate the creation date
	 * @param commision the commision
	 */
	public Transaction(
			int idTransaction,
			User payer,
			User beneficiary,
			double amount,
			String description,
			LocalDate creationDate,
			double commision) {
		super();
		this.idTransaction = idTransaction;
		this.payer = payer;
		this.beneficiary = beneficiary;
		this.amount = amount;
		this.description = description;
		this.creationDate = creationDate;
		this.commision = commision;
	}

	/**
	 * Gets the id transaction.
	 *
	 * @return the id transaction
	 */
	public int getIdTransaction() {
		return idTransaction;
	}

	/**
	 * Sets the id transaction.
	 *
	 * @param idTransaction the new id transaction
	 */
	public void setIdTransaction(int idTransaction) {
		this.idTransaction = idTransaction;
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
	public void setCreationDate(LocalDate createDate) {
		this.creationDate = createDate;
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
