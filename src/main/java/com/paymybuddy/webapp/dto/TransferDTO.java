package com.paymybuddy.webapp.dto;

import java.time.LocalDate;

import com.paymybuddy.webapp.model.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class TransferDTO {

	@Setter @Getter
	private int idTransfer;

	private String rib;

	private LocalDate createDate;

	private double amount;

	private String type;

	private User user;


	public TransferDTO(int idTransfer, String rib, LocalDate createDate,
			double amount, String type, User user) {
		super();
		this.idTransfer = idTransfer;
		this.rib = rib;
		this.createDate = createDate;
		this.amount = amount;
		this.type = type;
		this.user = user;
	}

	public String getRib() {
		return rib;
	}

	public void setRib(String rib) {
		this.rib = rib;
	}

	public LocalDate getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDate createDate) {
		this.createDate = createDate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "TransferDTO [idTransfer=" + idTransfer + ", rib=" + rib + ","
				+ " createDate=" + createDate + ", amount="
				+ amount + ", type=" + type + ", user=" + user + "]";
	}
}
