package com.paymybuddy.webapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paymybuddy.webapp.model.BankAccount;
import com.paymybuddy.webapp.model.User;

/**
 * The Interface BankAccountRepository.
 */
public interface BankAccountRepository extends JpaRepository<BankAccount, Integer> {


	// **************************** TODOs LIST **************************

	// Method:
	// --> getAllBankAccountByUser(UserDTO) served by
	// bankAccountRepository.findBankAccountByUser(user) : listBankAccountDTO
	// --> addBankAccount(String rib, userDTO):
	// bankAccountDTO served by Mapper toBankAccountDTO
	// --> deleteBankAccount(rib) served by
	// bankAccountRepository.deleteById(id)

	/**
	 * Find bank account by user.
	 *
	 * @param user the user
	 * @return the list
	 */
	List<BankAccount> findBankAccountByUser(User user);
}
