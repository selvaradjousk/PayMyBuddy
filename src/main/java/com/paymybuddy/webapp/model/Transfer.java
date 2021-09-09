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


/**
 * The Class Transfer.
 */
@Entity
@Table(name ="transfer")
public class Transfer {

	/** idTransfer. */
	@Id
	@Getter @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transfer", nullable = false)
    private int idTransfer;

	/** rib. */
    @Getter @Setter
    @Column(name = "rib", nullable = false)
    private String rib;

	/** createDate. */
    @Getter @Setter
    @Column(name = "date", nullable = false)
    private LocalDate createDate = LocalDate.now();

	/** amount. */
    @Getter @Setter
    @Column(name = "amount", nullable = false)
    private double amount;

	/** type. */
    @Getter @Setter
    @Column(name = "type", columnDefinition = "enum('CREDIT','DEBIT')")
    private String type;

	/** user. */
    @Getter @Setter
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * Instantiates a new transfer.
     */
    public Transfer() {
    }

	/**
	 * Instantiates a new transfer.
	 *
	 * @param ribb the ribb
	 * @param createDatee the create datee
	 * @param amountt the amountt
	 * @param typee the typee
	 * @param userr the userr
	 */
	public Transfer(
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
}