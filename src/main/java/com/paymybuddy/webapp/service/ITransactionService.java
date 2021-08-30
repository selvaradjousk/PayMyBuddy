package com.paymybuddy.webapp.service;

import java.util.List;

import com.paymybuddy.webapp.dto.TransactionDTO;
import com.paymybuddy.webapp.dto.UserDTO;

public interface ITransactionService {

	List<TransactionDTO> findAllTransactions();

	List<TransactionDTO> findAllTransactionByUser(UserDTO userDTO);

}
