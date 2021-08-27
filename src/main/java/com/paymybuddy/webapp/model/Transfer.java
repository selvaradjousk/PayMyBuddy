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

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="transfer")
public class Transfer {

	@Id
    @Getter @Setter
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name= "id_transfer", nullable = false)
    private int idTransfer;

    @Getter @Setter
    @Column(name= "rib", nullable = false)
    private String rib;

    @Getter
    @Column(name= "date", nullable = false)
    private LocalDate createDate = LocalDate.now();

    @Getter @Setter
    @Column(name= "amount", nullable = false)
    private double amount;

    @Getter @Setter
    @Column(name="type", columnDefinition = "enum('CREDIT','DEBIT')")
    private String type;

    @Getter @Setter
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Transfer() {
    }
    
    public Transfer(String rib, LocalDate createDate, double amount, String type, User user) {
		super();
		this.rib = rib;
		this.createDate = createDate;
		this.amount = amount;
		this.type = type;
		this.user = user;
	}

}
