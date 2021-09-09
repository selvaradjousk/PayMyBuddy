package com.paymybuddy.webapp.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

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

/**
 * The Class TransactionServiceImpl.
 * @author Senthil
 */
@Log4j2
@Service
public class TransactionServiceImpl  implements ITransactionService  {

	/** The transaction repository. */
	@Autowired
	TransactionRepository transactionRepository;

	/** The user service. */
    @Autowired
    IUserService userService;

	/** The transaction mapper. */
	private TransactionMapper transactionMapper = new TransactionMapper();

	/** The user mapper. */
	private UserMapper userMapper = new UserMapper();

	/** The Constant DESCRIPTION_LENGTH. */
	private static final double DESCRIPTION_LENGTH = 30;

	/** The Constant NUMBER_100. */
	private static final double NUMBER_100 = 100;

	/** The Constant COMMISION_RATE. */
	private static final double COMMISSION_RATE = 0.005;

	/**
	 * Instantiates a new transaction service impl.
	 *
	 * @param transactionRepositoryy the transaction repositoryy
	 * @param userServicee the user servicee
	 * @param transactionMapperr the transaction mapperr
	 * @param userMapperr the user mapperr
	 */
    public TransactionServiceImpl(
    		final TransactionRepository transactionRepositoryy,
    		final IUserService userServicee,
			final TransactionMapper transactionMapperr,
			final UserMapper userMapperr) {
		super();
		this.transactionRepository = transactionRepositoryy;
		this.userService = userServicee;
		this.transactionMapper = transactionMapperr;
		this.userMapper = userMapperr;
	}

	// *******************************************************************

	/**
	 * Find all transactions.
	 *
	 * @return the list
	 */
	@Override
	public List<TransactionDTO> findAllTransactions() {
		List<Transaction> listOfTransactions = transactionRepository
				.findAll();

		log.info(" ====> FIND All TRANSACTION requested <==== ");

		List<TransactionDTO> listOfTransactionsDTO
				= new ArrayList<TransactionDTO>();

		for (Transaction transaction : listOfTransactions) {
			listOfTransactionsDTO.add(transactionMapper
					.toTransactionDTO(transaction));
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
	public List<TransactionDTO> findAllTransactionByPayer(
			final UserDTO userDTO) {

		User user = userMapper.toUserDO(userDTO);

		List<Transaction> listOfTransactions = transactionRepository
				.findAllTransactionByPayer(user);

		log.info(" ====> FIND All TRANSACTION"
				+ " for a user requested <==== ");

		List<TransactionDTO> listOfTransactionsDTO
				= new ArrayList<TransactionDTO>();

		for (Transaction transaction : listOfTransactions) {
			listOfTransactionsDTO.add(transactionMapper
					.toTransactionDTO(transaction));
		}

		log.info(" ====> FIND All TRANSACTION for a user Successfull <==== ");

		return listOfTransactionsDTO;
	}

    // ********************************************************************

	  /**
     * Find all transaction by payer.
     *
     * @param userDTO the user DTO
     * @param pageable the pageable
     * @return the page
     */
    @Override
	  public Page<TransactionDTO> findAllTransactionByPayer(
			  final UserDTO userDTO, final Pageable pageable) {

	      User payer = userMapper.toUserDO(userDTO);

	      Page<TransactionDTO> pagesTransactionDTO = transactionRepository
	      		.findAllTransactionByPayer(payer, pageable)
	      		.map(transactionMapper::toTransactionDTO);

	      return pagesTransactionDTO;
	  }

	    // *****************************************************************

	    /**
    	 * Last three transactions.
    	 *
    	 * @param userDTO the user DTO
    	 * @param pageable the pageable
    	 * @return the page
    	 */
    	public Page<TransactionDTO> lastThreeTransactions(
	    		final UserDTO userDTO,
	    		final Pageable pageable) {

	    	User user = userMapper.toUserDO(userDTO);

	    	Page<TransactionDTO> pagesTransactionDTO = transactionRepository
	        		.lastThreeTransactions(user, pageable)
	        		.map(transactionMapper::toTransactionDTO);

	    	return pagesTransactionDTO;
	    }

  	  // *******************************************************************

    	/**
  	   * Last three transactions beneficiary.
  	   *
  	   * @param userDTO the user DTO
  	   * @param pageable the pageable
  	   * @return the page
  	   */
  	  @Override
    	public Page<TransactionDTO> lastThreeTransactionsBeneficiary(
    			final UserDTO userDTO,
    			final Pageable pageable) {

          User user = userMapper.toUserDO(userDTO);

          Page<Transaction> pagesTransaction = transactionRepository
        		  .lastThreeTransactionsBeneficiary(user, pageable);

          Page<TransactionDTO> pagesTransactionDTO = pagesTransaction
        		  .map(new Function<Transaction, TransactionDTO>() {

        			  @Override
        			  public TransactionDTO apply(
        					  final Transaction transaction) {
            				  return mappedTransactionDTO(transaction);
            				  }
        			  });

          return pagesTransactionDTO;
    	}

  	  // *****************************************************************

    	/**
         * Adds the transaction.
         *
         * @param transactionDTO the transaction DTO
         * @return the transaction DTO
         */
        @Override
        @Transactional(propagation = Propagation.REQUIRES_NEW,
        		readOnly = false, rollbackFor = RuntimeException.class)
        public TransactionDTO addTransaction(
        		TransactionDTO transactionDTO) {

        	// Check payer field not null
        	checkPayerNotNull(transactionDTO);

        	// Check beneficiary field not null
        	checkBeneficiaryNotNull(transactionDTO);

        	// Check length of the description field
            checkDescriptionLengthGreaterThan30Characters(transactionDTO);

        	// Check length of the description field
            checkDescriptionLengthEmpty(transactionDTO);

        	// Check amount field has value greater than zero
            checkTransactionAmountGreaterThanZero(transactionDTO);

            // Check for payer id not null
            checkForPayerById(transactionDTO);

            // prepare the data for transaction operation
            setupTransactionOperationData(transactionDTO);

            // Check transaction amount not zero and balance after transaction
            // including commission is affordable with the existing balance
            Boolean buddyUserWalletOperation = walletOperation(transactionDTO);

            	// If above checks pass and result is true
            	// we do the following steps
                if (buddyUserWalletOperation == true) {

                	// save transaction operation to DO
                	Transaction transaction = saveTransactionOperation(
                			transactionDTO);

                	// Map it back to transaction DTO
                    transactionDTO = transactionMapper
                    		.toTransactionDTO(transaction);

                    // update the balance of the beneficiary user
                    updateWalletOfBeneficiary(transactionDTO);

                    // Amount formating - rounding the values
                    updateWalletOfPayer(transactionDTO);

                    // Transaction modification date updated
                    addModificationDateToPayerAndBeneficiaryUsers(
                    		transactionDTO);

                    // save the user (payer) transaction to user DTO
                    userService.saveUser(userMapper
                    		.toUserDTO(transactionDTO.getPayer()));

                    // save the user (beneficiary) transaction to user DTO
                    userService.saveUser(userMapper
                    		.toUserDTO(transactionDTO.getBeneficiary()));

                    return transactionDTO;
                } else {
                    throw new BalanceNotSufficientException(
                    		"the amount exceeds the wallet");
                }
        }

        /**
         * Wallet operation.
         *
         * @param transactionDTO the transaction DTO
         * @return the boolean
         */
        // ********************************************************************
        private Boolean walletOperation(
        		final TransactionDTO transactionDTO) {

        	// Check if the transaction amount is not zero value
        	checkTransactionAmountNotZeroValue(transactionDTO);

        	double wallet = transactionDTO.getPayer().getWalletAmount();
            double amount = transactionDTO.getAmount();
            double commission = transactionDTO.getCommision();

            // check transaction amount + commision is not
            // exceeding user account balance
            if (wallet - (amount + commission) >= 0) {
                return true;
            } else {
                return false;
            }
        }

        // ********************************************************************
    	/**
    	 * check Transaction Amount Not Zero Value.
    	 * @param transactionDTO
    	 */
    	private void checkTransactionAmountNotZeroValue(
    			final TransactionDTO transactionDTO) {

    		if (transactionDTO.getAmount() == 0) {
                throw new DataNotConformException(
                		"Amount must be greater than 0");
            }
    	}


        // ********************************************************************
        /**
         * Sets the up transaction operation data and commission calculation.
         *
         * @param transactionDTO the new up transaction operation data
         */
    	private void setupTransactionOperationData(
    			final TransactionDTO transactionDTO) {

    		LocalDate createDate = LocalDate.now();

    		Double commissionFee = (double) Math.round((transactionDTO
    				.getAmount() * COMMISSION_RATE)
    				* NUMBER_100) / NUMBER_100;

    		transactionDTO.setCreationDate(createDate);

    		transactionDTO.setCommision(commissionFee);
    	}

        // ********************************************************************
    	/**
         * Adds the modification date to payer and beneficiary users.
         *
         * @param transactionDTO the transaction DTO
         */
    	private void addModificationDateToPayerAndBeneficiaryUsers(
    			final TransactionDTO transactionDTO) {

    		LocalDate modifDate = LocalDate.now();

    		transactionDTO.getBeneficiary()
    		.setModificationDate(modifDate);
    		transactionDTO.getPayer().setModificationDate(modifDate);
    	}


        // *******************************************************************
        /**
         * Update wallet of payer.
         *
         * @param transactionDTO the transaction DTO
         */
    	private void updateWalletOfPayer(
    			final TransactionDTO transactionDTO) {

    		Double newWallet = (transactionDTO.getPayer()
    				.getWalletAmount()
    				- (transactionDTO
    						.getAmount() + transactionDTO
    						.getCommision()));

    		newWallet = (double) Math.round((newWallet)
    				* NUMBER_100) / NUMBER_100;

    		transactionDTO.getPayer().setWalletAmount(newWallet);
    	}

        // *******************************************************************
    	/**
         * Update wallet of beneficiary.
         *
         * @param transactionDTO the transaction DTO
         */
    	private void updateWalletOfBeneficiary(
    			final TransactionDTO transactionDTO) {

    		    transactionDTO.getBeneficiary()
    		    .setWalletAmount((
    		    		transactionDTO.getBeneficiary()
    		    		.getWalletAmount()
    		    		+ transactionDTO.getAmount()));
    	}

        // *******************************************************************
    	/**
         * Save transaction operation.
         *
         * @param transactionDTO the transaction DTO
         * @return the transaction
         */
    	private Transaction saveTransactionOperation(
    			final TransactionDTO transactionDTO) {

//    		    Transaction transaction = new Transaction();

    		    Transaction transaction = transactionMapper
    		    		.toTransactionDO(transactionDTO);
    		    transactionRepository.save(transaction);
    		return transaction;
    	}

        // *******************************************************************
    	/**
         * Check transaction amount greater than zero.
         *
         * @param transactionDTO the transaction DTO
         */
    	private void checkTransactionAmountGreaterThanZero(
    			final TransactionDTO transactionDTO) {
    		if (transactionDTO.getAmount() <= 0) {
    		    throw new BalanceNotSufficientException(
    		    		"The amount should be a positive value");
    		}
    	}

        // ********************************************************************
        /**
         * Check for payer by id.
         *
         * @param transactionDTO the transaction DTO
         */
    	public void checkForPayerById(
    			final TransactionDTO transactionDTO) {

    		if (userService.findUserById(transactionDTO
    				.getPayer().getId()) == null) {

    			throw new DataNotFoundException(
    					"Problem with the database,"
    					+ " user payer not found");
    		}
    	}

        // ********************************************************************
        /**
         * Check description length greater than 30 characters.
         *
         * @param transactionDTO the transaction DTO
         */
    	private void checkDescriptionLengthGreaterThan30Characters(
    			final TransactionDTO transactionDTO) {

    		if (transactionDTO.getDescription()
    				.length() > DESCRIPTION_LENGTH) {

    		    throw new DataNotConformException(
    		    		"description should be 30 characters maximum");
    		}
    	}

    	// ********************************************************************

    	/**
         * Check payer not null.
         *
         * @param transactionDTO the transaction DTO
         */
        private void checkPayerNotNull(
    			final TransactionDTO transactionDTO) {

    		if (transactionDTO.getPayer() == null) {

    		    throw new DataNotConformException(
    		    		"user - Payer cannot be null");
    		}
    	}

        // ********************************************************************

    	/**
         * Check beneficiary not null.
         *
         * @param transactionDTO the transaction DTO
         */
        private void checkBeneficiaryNotNull(
    			final TransactionDTO transactionDTO) {

    		if (transactionDTO.getBeneficiary() == null) {

    		    throw new DataNotConformException(
    		    		"user - Beneficiary cannot be null");
    		}
    	}

        // ********************************************************************

    	/**
         * Check description length empty.
         *
         * @param transactionDTO the transaction DTO
         */
        private void checkDescriptionLengthEmpty(
    			final TransactionDTO transactionDTO) {

    		if (transactionDTO.getDescription().length() == 0) {

    		    throw new DataNotConformException(
    		    		"description should be entered");
    		}
    	}

        // ********************************************************************

    	/**
	     * Mapped transaction DTO.
	     *
	     * @param transaction the transaction
	     * @return the transaction DTO
	     */
    	public TransactionDTO mappedTransactionDTO(
    			final Transaction transaction) {

//    		TransactionDTO transactionDTO = new TransactionDTO();
    		TransactionDTO transactionDTO = transactionMapper
    				.toTransactionDTO(transaction);
            return transactionDTO;
    	}

    	   // *************************************************************

     	/**
     	 * Do save new transaction.
     	 *
     	 * @param page the page
     	 * @param amount the amount
     	 * @param contactEmail the friend email
     	 * @param description the description
     	 * @param beneficiary the beneficiary
     	 * @param payer the payer
     	 * @return the string
     	 */
     	public String doSaveNewTransaction(
     			final int page,
     			final Double amount,
     			final String contactEmail,
     			final String description,
     			final User beneficiary,
     			final User payer) {

     		String errorMessage;

     		try {
     		    TransactionDTO newTransactionDTO = new TransactionDTO(
     		    		payer,
     		    		beneficiary,
     		    		amount,
     		    		description);

     		    addTransaction(newTransactionDTO);

     		} catch (DataNotFoundException
     				| DataNotConformException
     				| BalanceNotSufficientException e) {

     			errorMessage = e.getMessage();

     			return "redirect:/transaction?page=" + page
                        + "&errorMessage=" + errorMessage
                        + "&friendEmail=" + contactEmail
                        + "&amount=" + amount
                        + "&description=" + description;
     		}

     		errorMessage = "Transaction saved";

     		return "redirect:/transaction?page=" + page
                     + "&errorMessage=" + errorMessage
                     + "&friendEmail=" + contactEmail
                     + "&amount=" + amount
                     + "&description=" + description;
     	}


     	// *********************************************************************

        /**
	      * Delete by id.
	      *
	      * @param idTransactionDTO the id transaction DTO
	      * @return the transaction
	      */
	     @Override
        public Transaction deleteById(final int idTransactionDTO) {

            transactionRepository.deleteById(idTransactionDTO);

            return null;
        }
        // ******************************************************************

}
