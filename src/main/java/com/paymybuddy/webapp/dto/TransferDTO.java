package com.paymybuddy.webapp.dto;

import java.time.LocalDate;

import com.paymybuddy.webapp.model.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// TODO: Auto-generated Javadoc
/**
 * Instantiates a new transfer DTO.
 */
@NoArgsConstructor
public class TransferDTO {

	/**
	 * Sets the id transfer.
	 *
	 * @param idTransfer the new id transfer
	 */
	@Setter /**
  * Gets the id transfer.
  *
  * @return the id transfer
  */
 @Getter
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
	 * @param idTransfer the id transfer
	 * @param rib the rib
	 * @param createDate the create date
	 * @param amount the amount
	 * @param type the type
	 * @param user the user
	 */
	public TransferDTO(
			int idTransfer,
			String rib,
			LocalDate createDate,
			double amount,
			String type,
			User user) {
		super();
		this.rib = rib;
		this.createDate = createDate;
		this.amount = amount;
		this.type = type;
		this.user = user;
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
	 * @param rib the new rib
	 */
	public void setRib(String rib) {
		this.rib = rib;
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
	 * @param createDate the new creates the date
	 */
	public void setCreateDate(LocalDate createDate) {
		this.createDate = createDate;
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
	 * @param type the new type
	 */
	public void setType(String type) {
		this.type = type;
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
	 * @param user the new user
	 */
	public void setUser(User user) {
		this.user = user;
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
				+ " createDate=" + createDate + ", amount="
				+ amount + ", type=" + type + ", user=" + user + "]";
	}
}
