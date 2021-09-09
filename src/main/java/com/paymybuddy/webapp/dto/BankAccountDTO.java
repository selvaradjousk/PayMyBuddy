package com.paymybuddy.webapp.dto;

import com.paymybuddy.webapp.model.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The Class BankAccountDTO.
 */
@NoArgsConstructor
public class BankAccountDTO {

	/** The id bank account. */
	@Setter @Getter
    private Integer idBankAccount;

    /** The user. */
    private User user;

    /** The rib. */
    private String rib;


	/**
	 * Instantiates a new bank account DTO.
	 *
	 * @param userr the userr
	 * @param ribb the ribb
	 */
	public BankAccountDTO(
			final User userr,
			final String ribb) {
        this.user = userr;
        this.rib = ribb;
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
     * To string.
     *
     * @return the string
     */
    @Override
	public String toString() {
		return "BankAccount ["
				+ "idBankAccount=" + idBankAccount + ","
				+ " user=" + user + ","
				+ " rib=" + rib + "]";
	}
}
