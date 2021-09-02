package com.paymybuddy.webapp.util;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.paymybuddy.webapp.dto.TransactionDTO;
import com.paymybuddy.webapp.model.Transaction;

/**
 * The Class TransactionMapper.
 */
@Component
public class TransactionMapper {

	LocalDate creationDate = LocalDate.now();	
	
	  /**
 * To transaction DO.
 *
 * @param transactionDTO the transaction DTO
 * @return the transaction
 */
public Transaction toTransactionDO(TransactionDTO transactionDTO) {

	        Transaction transaction = new Transaction();

	        if (transactionDTO !=null){
	        transaction.setIdTransaction(transactionDTO.getIdTransaction());
	        transaction.setPayer(transactionDTO.getPayer());
	        transaction.setBeneficiary(transactionDTO.getBeneficiary());
	        transaction.setAmount(transactionDTO.getAmount());
	        transaction.setDescription(transactionDTO.getDescription());
	        transaction.setCreationDate(transactionDTO.getCreationDate());
	        transaction.setCommision(transactionDTO.getCommision());

	        return transaction;
	        } else{
	        return null;
	        }
	    }

	    /**
    	 * To transaction DTO.
    	 *
    	 * @param transaction the transaction
    	 * @return the transaction DTO
    	 */
    	public TransactionDTO toTransactionDTO(Transaction transaction) {

	        TransactionDTO transactionDTO = new TransactionDTO();
	        
	        if (transaction !=null){
	        transactionDTO.setIdTransaction(transaction.getIdTransaction());
	        transactionDTO.setPayer(transaction.getPayer());
	        transactionDTO.setBeneficiary(transaction.getBeneficiary());
	        transactionDTO.setAmount(transaction.getAmount());
	        transactionDTO.setDescription(transaction.getDescription());
	        transactionDTO.setCreationDate(transaction.getCreationDate());
	        transactionDTO.setCommision(transaction.getCommision());

	        return transactionDTO;
	        } else{
	        return null;
	        }
	    }
}
