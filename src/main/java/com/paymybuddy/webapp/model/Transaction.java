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
@Table(name = "transaction")
public class Transaction {

	/** The id transaction. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_transaction", nullable = false)
	private int idTransaction;

	/** The payer. */
	@ManyToOne
	@JoinColumn(name = "payer_id", nullable = false)
	private User payer;

	/** The beneficiary. */
	@ManyToOne
	@JoinColumn(name = "beneficiary_id", nullable = false)
	private User beneficiary;

	/** The amount. */
	@Column(name = "amount", nullable = false)
	private double amount;

	/** The description. */
	@Column(name = "description", length = 150, nullable = false)
	private String description;

	/** The creation date. */
	@Column(name = "date", nullable = false)
	private LocalDate creationDate = LocalDate.now();

	/** The commision. */
	@Column(name = "commision")
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
	 * @param idTransactionn the id transactionn
	 * @param payerr the payerr
	 * @param beneficiaryy the beneficiaryy
	 * @param amountt the amountt
	 * @param descriptionn the descriptionn
	 * @param creationDatee the creation datee
	 * @param commisionn the commisionn
	 */
	public Transaction(
			final int idTransactionn,
			final User payerr,
			final User beneficiaryy,
			final double amountt,
			final String descriptionn,
			final LocalDate creationDatee,
			final double commisionn) {
		super();
		this.idTransaction = idTransactionn;
		this.payer = payerr;
		this.beneficiary = beneficiaryy;
		this.amount = amountt;
		this.description = descriptionn;
		this.creationDate = creationDatee;
		this.commision = commisionn;
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
	 * @param idTransactionn the new id transaction
	 */
	public void setIdTransaction(final int idTransactionn) {
		this.idTransaction = idTransactionn;
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
