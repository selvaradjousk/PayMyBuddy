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
	 * @param user the user
	 * @param rib the rib
	 */
	public BankAccountDTO(User user, String rib) {
        this.user = user;
        this.rib = rib;
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
     * To string.
     *
     * @return the string
     */
    @Override
	public String toString() {
		return "BankAccount ["
				+ "idBankAccount=" + idBankAccount + ","
				+ " user=" + user +
				", rib=" + rib + "]";
	}
}
