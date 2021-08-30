package com.paymybuddy.webapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.webapp.dto.TransactionDTO;
import com.paymybuddy.webapp.dto.UserDTO;
import com.paymybuddy.webapp.model.Transaction;
import com.paymybuddy.webapp.repository.TransactionRepository;
import com.paymybuddy.webapp.util.TransactionMapper;
import com.paymybuddy.webapp.util.UserMapper;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class TransactionServiceImpl  implements ITransactionService  {



	// **************************** TODOs LIST ***********************************
	
	// Method: 
	// --> getAllTransactions(): List(TransactionsDTO) served by TransactionMapper.toDTO(transaction)
	// --> getAllTransactionsByPayer(UserDTO); TransactionDTO by pages
	// --> getAllTransactionsByBeneficiary(UserDTO); TransactionDTO by pages
	// --> addTransaction(transactionDTO): transactionDTO
	// --> pagination of transaction list
	// --> Before validation of Transfer, Transaction approval done by checking the account balance amount
	

	
	@Autowired
	TransactionRepository transactionRepository;

    @Autowired
    IUserService userService;
	
	public TransactionMapper transactionMapper = new TransactionMapper();

	public UserMapper userMapper = new UserMapper();

	// *******************************************************************
	
	@Override
	public List<TransactionDTO> findAllTransactions() {
		List<Transaction> listOfTransactions = transactionRepository.findAll();

		log.info(" ====> FIND All TRANSACTION requested <==== ");
		
		List<TransactionDTO> listOfTransactionsDTO = new ArrayList<TransactionDTO>();

		for (Transaction transaction : listOfTransactions) {
			listOfTransactionsDTO.add(transactionMapper.toTransactionDTO(transaction));
		}

		log.info(" ====> FIND All TRANSACTION Successfull <==== ");

		return listOfTransactionsDTO;
	}

	@Override
	public List<TransactionDTO> findAllTransactionByUser(UserDTO userDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
}
