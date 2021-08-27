package com.paymybuddy.webapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


// **************************** TODOs LIST ***********************************

// Method: c
// --> Checkstyle => java doc, hidden fields, blank spaces
// --> validation field constraints -> not null, not blank, unique,
// --> updating ER (Entity Relationship types) - associations


@Entity
@Table(name="bank_account")
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id_bank_account", nullable = false)
    private Integer idBankAccount;

    @ManyToOne(optional = false)
    @JoinColumn(name= "user_id")
    private User user;

    @Column(name= "rib", nullable = false)
    private String rib;

    public BankAccount() {
    }

	public BankAccount(User user, String rib) {
        this.user = user;
        this.rib = rib;
    }

    public BankAccount(Integer idBankAccount, User user, String rib) {
        this.idBankAccount = idBankAccount;
        this.user = user;
        this.rib = rib;
    }

    public Integer getIdBankAccount() {
        return idBankAccount;
    }

    public void setIdBankAccount(Integer idBankAccount) {
        this.idBankAccount = idBankAccount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRib() {
        return rib;
    }

    public void setRib(String rib) {
        this.rib = rib;
    }
    

    @Override
	public String toString() {
		return "BankAccount [idBankAccount=" + idBankAccount + ", user=" + user + ", rib=" + rib + "]";
	}
}
