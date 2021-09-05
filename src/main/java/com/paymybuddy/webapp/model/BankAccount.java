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
@Table(name="bank_account")
public class BankAccount {

    /** The id bank account. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id_bank_account", nullable = false)
    private Integer idBankAccount;

    /** The user. */
    @ManyToOne(optional = false)
    @JoinColumn(name= "user_id")
    private User user;

    /** The rib. */
    @Column(name= "rib", nullable = false)
    private String rib;

    /**
     * Instantiates a new bank account.
     */
    public BankAccount() {
    }

	/**
	 * Instantiates a new bank account.
	 *
	 * @param user the user
	 * @param rib the rib
	 */
	public BankAccount(User user, String rib) {
        this.user = user;
        this.rib = rib;
    }

    /**
     * Instantiates a new bank account.
     *
     * @param idBankAccount the id bank account
     * @param user the user
     * @param rib the rib
     */
    public BankAccount(Integer idBankAccount, User user, String rib) {
        this.idBankAccount = idBankAccount;
        this.user = user;
        this.rib = rib;
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
     * @param idBankAccount the new id bank account
     */
    public void setIdBankAccount(Integer idBankAccount) {
        this.idBankAccount = idBankAccount;
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
