package com.paymybuddy.webapp.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.paymybuddy.webapp.dto.TransactionDTO;
import com.paymybuddy.webapp.dto.UserDTO;

public interface ITransactionService {

	List<TransactionDTO> findAllTransactions();

	List<TransactionDTO> findAllTransactionByPayer(UserDTO userDTO);

	Page<TransactionDTO> findAllTransactionByPayer(UserDTO userDTO, Pageable pageable);

}
