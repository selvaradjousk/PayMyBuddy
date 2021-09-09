package com.paymybuddy.webapp.dto;

import java.time.LocalDate;

import com.paymybuddy.webapp.model.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Instantiates a new transfer DTO.
 */
@NoArgsConstructor
public class TransferDTO {

	/** The idTransfer. */
	@Getter @Setter
	private int idTransfer;

	/** The rib. */
	private String rib;

	/** The create date. */
	private LocalDate createDate;

	/** The amount. */
	private double amount;

	/** The type. */
	private String type;

	/** The user. */
	private User user;


	/**
	 * Instantiates a new transfer DTO.
	 *
	 * @param idTransferr the id transferr
	 * @param ribb the ribb
	 * @param createDatee the create datee
	 * @param amountt the amountt
	 * @param typee the typee
	 * @param userr the userr
	 */
	public TransferDTO(
			final int idTransferr,
			final String ribb,
			final LocalDate createDatee,
			final double amountt,
			final String typee,
			final User userr) {
		super();
		this.rib = ribb;
		this.createDate = createDatee;
		this.amount = amountt;
		this.type = typee;
		this.user = userr;
	}

	/**
	 * Gets the rib.
	 *
	 * @return the rib
	 */
	public String getRib() {
		return rib;
	}

	/**
	 * Sets the rib.
	 *
	 * @param ribb the new rib
	 */
	public void setRib(final String ribb) {
		this.rib = ribb;
	}

	/**
	 * Gets the creates the date.
	 *
	 * @return the creates the date
	 */
	public LocalDate getCreateDate() {
		return createDate;
	}

	/**
	 * Sets the creates the date.
	 *
	 * @param createDatee the new creates the date
	 */
	public void setCreateDate(final LocalDate createDatee) {
		this.createDate = createDatee;
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
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the type.
	 *
	 * @param typee the new type
	 */
	public void setType(final String typee) {
		this.type = typee;
	}

	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Sets the user.
	 *
	 * @param userr the new user
	 */
	public void setUser(final User userr) {
		this.user = userr;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "TransferDTO [idTransfer=" + idTransfer
				+ ", rib=" + rib + ","
				+ " createDate=" + createDate
				+ ", amount="
				+ amount + ", type=" + type
				+ ", user=" + user + "]";
	}
}
