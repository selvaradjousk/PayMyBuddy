package com.paymybuddy.webapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * The Class BankAccount.
 */
@Entity
@Table(name = "bank_account")
public class BankAccount {

    /** The id bank account. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bank_account", nullable = false)
    private Integer idBankAccount;

    /** The user. */
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    /** The rib. */
    @Column(name = "rib", nullable = false)
    private String rib;

    /**
     * Instantiates a new bank account.
     */
    public BankAccount() {
    }

	/**
	 * Instantiates a new bank account.
	 *
	 * @param userr the userr
	 * @param ribb the ribb
	 */
	public BankAccount(
			final User userr,
			final String ribb) {
        this.user = userr;
        this.rib = ribb;
    }

    /**
     * Instantiates a new bank account.
     *
     * @param idBankAccountt the id bank accountt
     * @param userr the userr
     * @param ribb the ribb
     */
    public BankAccount(
    		final Integer idBankAccountt,
    		final User userr,
    		final String ribb) {
        this.idBankAccount = idBankAccountt;
        this.user = userr;
        this.rib = ribb;
    }

    /**
     * Gets the id bank account.
     *
     * @return the id bank account
     */
    public Integer getIdBankAccount() {
        return idBankAccount;
    }

    /**
     * Sets the id bank account.
     *
     * @param idBankAccountt the new id bank account
     */
    public void setIdBankAccount(final Integer idBankAccountt) {
        this.idBankAccount = idBankAccountt;
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
		return "BankAccount [idBankAccount=" + idBankAccount
				+ ", user=" + user
				+ ", rib=" + rib + "]";
	}
}
