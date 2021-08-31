package com.paymybuddy.webapp.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.paymybuddy.webapp.dto.TransactionDTO;
import com.paymybuddy.webapp.dto.UserDTO;
import com.paymybuddy.webapp.exception.BalanceNotSufficientException;
import com.paymybuddy.webapp.exception.DataNotConformException;
import com.paymybuddy.webapp.exception.DataNotFoundException;
import com.paymybuddy.webapp.model.Transaction;
import com.paymybuddy.webapp.model.User;
import com.paymybuddy.webapp.repository.TransactionRepository;
import com.paymybuddy.webapp.util.TransactionMapper;
import com.paymybuddy.webapp.util.UserMapper;

import lombok.extern.log4j.Log4j2;

/** The Constant log. */
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


	/** The transaction repository. */
	@Autowired
	TransactionRepository transactionRepository;

    /** The user service. */
    @Autowired
    IUserService userService;

	/** The transaction mapper. */
	public TransactionMapper transactionMapper = new TransactionMapper();

	/** The user mapper. */
	public UserMapper userMapper = new UserMapper();

	// *******************************************************************

	/**
	 * Find all transactions.
	 *
	 * @return the list
	 */
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

	// *************This method is a step for pagable template*************

	/**
	 * Find all transaction by payer.
	 *
	 * @param userDTO the user DTO
	 * @return the list
	 */
	@Override
	public List<TransactionDTO> findAllTransactionByPayer(UserDTO userDTO) {

		User user = userMapper.toUserDO(userDTO);

		List<Transaction> listOfTransactions = transactionRepository
				.findAllTransactionByPayer(user);

		log.info(" ====> FIND All TRANSACTION for a user requested <==== ");

		List<TransactionDTO> listOfTransactionsDTO = new ArrayList<TransactionDTO>();

		for (Transaction transaction : listOfTransactions) {
			listOfTransactionsDTO.add(transactionMapper.toTransactionDTO(transaction));
		}

		log.info(" ====> FIND All TRANSACTION for a user Successfull <==== ");

		return listOfTransactionsDTO;
	}

    // ************************************************************************

	  /**
     * Find all transaction by payer.
     *
     * @param userDTO the user DTO
     * @param pageable the pageable
     * @return the page
     */
    @Override
	  public Page<TransactionDTO> findAllTransactionByPayer(
			  UserDTO userDTO, Pageable pageable) {

	      User payer = userMapper.toUserDO(userDTO);

	      Page<TransactionDTO> pagesTransactionDTO = transactionRepository
	      		.findAllTransactionByPayer(payer, pageable)
	      		.map(transactionMapper::toTransactionDTO);

	      return pagesTransactionDTO;
	  }

	    // ************************************************************************

	    /**
    	 * Last three transactions.
    	 *
    	 * @param userDTO the user DTO
    	 * @param pageable the pageable
    	 * @return the page
    	 */
    	public Page<TransactionDTO> lastThreeTransactions(
	    		UserDTO userDTO,
	    		Pageable pageable) {

	    	User user = userMapper.toUserDO(userDTO);

	    	Page<TransactionDTO> pagesTransactionDTO = transactionRepository
	        		.lastThreeTransactions(user, pageable)
	        		.map(transactionMapper::toTransactionDTO);

	    	return pagesTransactionDTO;
	    }

	    // ************************************************************************
    	 
    	/**
         * Adds the transaction.
         *
         * @param transactionDTO the transaction DTO
         * @return the transaction DTO
         */
        @Override
        @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false, rollbackFor = RuntimeException.class)
        public TransactionDTO addTransaction(TransactionDTO transactionDTO) {

        	// Check payer field not null
        	checkPayerNotNull(transactionDTO);
        	
        	// Check length of the description field
            checkDescriptionLengthGreaterThan30Characters(transactionDTO);

        	// Check amount field has value greater than zero
            checkTransactionAmountGreaterThanZero(transactionDTO);

            // Check for payer id not null
            checkForPayerById(transactionDTO);

            // prepare the data for transaction operation
            setupTransactionOperationData(transactionDTO);

            // Check transaction amount not zero and balance after transaction
            // including commission is affordable with the existing balance
            Boolean buddyUserWalletOperation = walletOperation(transactionDTO);

            	// If above checks pass and result is true we do the following steps
                if (buddyUserWalletOperation == true){

                	// save transaction operation to DO
                	Transaction transaction = saveTransactionOperation(transactionDTO);

                	// Map it back to transaction DTO
                    transactionDTO = transactionMapper.toTransactionDTO(transaction);

                    // update the balance of the beneficiary user
                    updateWalletOfBeneficiary(transactionDTO);

                    // Amount formating - rounding the values
                    updateWalletOfPayer(transactionDTO);

                    // Transaction modification date updated
                    addModificationDateToPayerAndBeneficiaryUsers(transactionDTO);

                    // save the user (payer) transaction to user DTO
                    userService.saveUser(userMapper.toUserDTO(transactionDTO.getPayer()));

                    // save the user (beneficiary) transaction to user DTO
                    userService.saveUser(userMapper.toUserDTO(transactionDTO.getBeneficiary()));

                    return transactionDTO;
                } else {
                    throw new BalanceNotSufficientException("the amount exceeds the wallet");
                }
        }

        /**
         * Wallet operation.
         *
         * @param transactionDTO the transaction DTO
         * @return the boolean
         */
        // ************************************************************************
        private Boolean walletOperation(TransactionDTO transactionDTO) {

        	// Check if the transaction amount is not zero value
        	checkTransactionAmountNotZeroValue(transactionDTO);

        	double wallet = transactionDTO.getPayer().getWalletAmount();
            double amount = transactionDTO.getAmount();
            double commision = transactionDTO.getCommision();

            // check transaction amount + commision is not
            // exceeding user account balance
            if( wallet - (amount+commision) >= 0) {
                return true;
            }else {
                return false;
            }
        }
        // ************************************************************************
    	/** 
    	 * check Transaction Amount Not Zero Value
    	 * @param transactionDTO
    	 */
    	private void checkTransactionAmountNotZeroValue(
    			TransactionDTO transactionDTO) {
 
    		if ( transactionDTO.getAmount() == 0) {
                throw new DataNotConformException(
                		"Amount must be greater than 0");
            }
    	}
    	

        // ************************************************************************
        /**
         * Sets the up transaction operation data.
         *
         * @param transactionDTO the new up transaction operation data
         */
    	private void setupTransactionOperationData(
    			TransactionDTO transactionDTO) {
 
    		LocalDate createDate = LocalDate.now();
 
    		Double fee = (double)Math.round((transactionDTO
    				.getAmount()*0.005)*100)/100;
 
    		transactionDTO.setCreationDate(createDate);

    		transactionDTO.setCommision(fee);
    	}

        // ************************************************************************
    	/**
         * Adds the modification date to payer and beneficiary users.
         *
         * @param transactionDTO the transaction DTO
         */
    	private void addModificationDateToPayerAndBeneficiaryUsers(
    			TransactionDTO transactionDTO) {
 
    		LocalDate modifDate = LocalDate.now();

    		transactionDTO.getBeneficiary()
    		.setModificationDate(modifDate);
    		transactionDTO.getPayer().setModificationDate(modifDate);
    	}


        // ************************************************************************
        /**
         * Update wallet of payer.
         *
         * @param transactionDTO the transaction DTO
         */
    	private void updateWalletOfPayer(TransactionDTO transactionDTO) {
 
    		Double newWallet = (transactionDTO.getPayer().getWalletAmount()
    				- (transactionDTO
    						.getAmount() + transactionDTO
    						.getCommision()) );

    		newWallet = (double)Math.round((newWallet)*100)/100;
 
    		transactionDTO.getPayer().setWalletAmount(newWallet);
    	}

        // ************************************************************************
    	/**
         * Update wallet of beneficiary.
         *
         * @param transactionDTO the transaction DTO
         */
    	private void updateWalletOfBeneficiary(TransactionDTO transactionDTO) {

    		    transactionDTO.getBeneficiary().setWalletAmount((
    		    		transactionDTO.getBeneficiary().getWalletAmount()
    		    		+ transactionDTO.getAmount()));
    	}

        // ************************************************************************
    	/**
         * Save transaction operation.
         *
         * @param transactionDTO the transaction DTO
         * @return the transaction
         */
    	private Transaction saveTransactionOperation(TransactionDTO transactionDTO) {

    		    Transaction transaction = new Transaction();

    		    transaction = transactionMapper
    		    		.toTransactionDO(transactionDTO);
    		    transactionRepository.save(transaction);
    		return transaction;
    	}

        // ************************************************************************
    	/**
         * Check transaction amount greater than zero.
         *
         * @param transactionDTO the transaction DTO
         */
    	private void checkTransactionAmountGreaterThanZero(
    			TransactionDTO transactionDTO) {
    		if (transactionDTO.getAmount() <= 0) {
    		    throw new BalanceNotSufficientException(
    		    		"The amount should be a positive value");
    		}
    	}

        // ************************************************************************
        /**
         * Check for payer by id.
         *
         * @param transactionDTO the transaction DTO
         */
    	private void checkForPayerById(TransactionDTO transactionDTO) {

    		if (userService.findUserById(transactionDTO
    				.getPayer().getId())==null) {

    			throw new DataNotFoundException(
    					"Problem with the database, user payer not found");
    		}
    	}

        // ************************************************************************
        /**
         * Check description length greater than 30 characters.
         *
         * @param transactionDTO the transaction DTO
         */
    	private void checkDescriptionLengthGreaterThan30Characters(
    			TransactionDTO transactionDTO) {

    		if (transactionDTO.getDescription().length() > 30) {

    		    throw new DataNotConformException(
    		    		"description should be 30 characters maximum");
    		}
    	}
        // ************************************************************************        
        
    	
    	/**
         * Check description length greater than 30 characters.
         *
         * @param transactionDTO the transaction DTO
         */
    	private void checkPayerNotNull(
    			TransactionDTO transactionDTO) {

    		if (transactionDTO.getPayer() == null) {

    		    throw new DataNotConformException(
    		    		"user - Payer cannot be null");
    		}
    	}
        // ************************************************************************  
    	
    	
    	
    	/**
	     * Mapped transaction DTO.
	     *
	     * @param transaction the transaction
	     * @return the transaction DTO
	     */
    	public TransactionDTO mappedTransactionDTO(Transaction transaction) {
    		TransactionDTO transactionDTO = new TransactionDTO();
            transactionDTO = transactionMapper.toTransactionDTO(transaction);
            return transactionDTO;
    	}        
	

}
