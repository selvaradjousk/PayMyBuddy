package com.paymybuddy.webapp.service;

import java.util.List;

import com.paymybuddy.webapp.dto.TransactionDTO;

public interface ITransactionService {

	List<TransactionDTO> findAllTransactions();

}
