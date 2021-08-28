package com.paymybuddy.webapp.dto;

import com.paymybuddy.webapp.model.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class BankAccountDTO {

	@Setter @Getter
    private Integer idBankAccount;

    private User user;

    private String rib;


	public BankAccountDTO(User user, String rib) {
        this.user = user;
        this.rib = rib;
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
		return "BankAccount [idBankAccount=" + idBankAccount + ","
				+ " user=" + user + ", rib=" + rib + "]";
	}
}
