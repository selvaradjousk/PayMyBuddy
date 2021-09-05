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

import com.paymybuddy.webapp.constant.TransferType;
import com.paymybuddy.webapp.dto.TransferDTO;
import com.paymybuddy.webapp.dto.UserDTO;
import com.paymybuddy.webapp.exception.DataNotConformException;
import com.paymybuddy.webapp.exception.DataNotFoundException;
import com.paymybuddy.webapp.model.Transfer;
import com.paymybuddy.webapp.model.User;
import com.paymybuddy.webapp.repository.TransferRepository;
import com.paymybuddy.webapp.util.TransferMapper;
import com.paymybuddy.webapp.util.UserMapper;

import lombok.extern.log4j.Log4j2;



/**
 * The Class TransferServiceImpl.
 */
@Log4j2
@Service
public class TransferServiceImpl implements ITransferService {


	/** The transfer repository. */
	@Autowired
	TransferRepository transferRepository;

	/** The user service. */
    @Autowired
    IUserService userService;

	/** The transfer mapper. */
	private TransferMapper transferMapper = new TransferMapper();

	/** The user mapper. */
	private UserMapper userMapper = new UserMapper();



    /**
     * Instantiates a new transfer service impl.
     *
     * @param transferRepository the transfer repository
     * @param userService the user service
     * @param transferMapper the transfer mapper
     * @param userMapper the user mapper
     */
    public TransferServiceImpl(
    		TransferRepository transferRepositoryy,
    		IUserService userServicee,
			TransferMapper transferMapperr,
			UserMapper userMapperr) {
		super();
		this.transferRepository = transferRepositoryy;
		this.userService = userServicee;
		this.transferMapper = transferMapperr;
		this.userMapper = userMapperr;
	}

    // *******************************************************************

	/**
	 * Find all transfers.
	 *
	 * @return the list
	 */
	@Override
	public List<TransferDTO> findAllTransfers() {

		List<Transfer> listOfTransfers = transferRepository.findAll();

		log.info(" ====> FIND All TRANSFER requested <==== ");

		List<TransferDTO> listOfTransfersDTO = new ArrayList<TransferDTO>();

		for (Transfer transfer : listOfTransfers) {
			listOfTransfersDTO.add(transferMapper.toTransferDTO(transfer));
		}

		log.info(" ====> FIND All TRANSFER Successfull <==== ");

		return listOfTransfersDTO;
	}


	// *************This method is a step for pagable template*************

	/**
	 * Find all transfer by user.
	 *
	 * @param userDTO the user DTO
	 * @return the list
	 */
	@Override
	public List<TransferDTO> findAllTransferByUser(
			final UserDTO userDTO) {
		
		User user = userMapper.toUserDO(userDTO);
		
		List<Transfer> listOfTransfers = transferRepository
				.findAllByUser(user);

		log.info(" ====> FIND All TRANSFER for a user requested <==== ");

		List<TransferDTO> listOfTransfersDTO = new ArrayList<TransferDTO>();

		for (Transfer transfer : listOfTransfers) {
			listOfTransfersDTO.add(transferMapper.toTransferDTO(transfer));
		}
		log.info(" ====> FIND All TRANSFER for a user Successfull"
				+ " : " +  listOfTransfersDTO);

		return listOfTransfersDTO;
	}

    // *********************************************************************



	/**
     * Find all by user type credit.
     *
     * @param userDTO the user DTO
     * @return the list
     */
    @Override
	public List<TransferDTO> findAllByUserTypeCredit(
			final UserDTO userDTO) {
		
		User user = userMapper.toUserDO(userDTO);
		
		List<Transfer> listOfTransfers = transferRepository
				.findAllByUserTypeCredit(user);

		log.info(" ====> FIND All TRANSFER for a user requested <==== ");

		List<TransferDTO> listOfTransfersDTO = new ArrayList<TransferDTO>();

		for (Transfer transfer : listOfTransfers) {
			listOfTransfersDTO.add(transferMapper.toTransferDTO(transfer));
		}
		log.info(" ====> FIND All TRANSFER for a user Successfull"
				+ " : " + listOfTransfersDTO);

		return listOfTransfersDTO;
	}

    // *********************************************************************


	/**
     * Find all by user type debit.
     *
     * @param userDTO the user DTO
     * @return the list
     */
    @Override
	public List<TransferDTO> findAllByUserTypeDebit(final UserDTO userDTO) {

		User user = userMapper.toUserDO(userDTO);

		List<Transfer> listOfTransfers = transferRepository
				.findAllByUserTypeDebit(user);

		log.info(" ====> FIND All TRANSFER for a user requested <==== ");

		List<TransferDTO> listOfTransfersDTO = new ArrayList<TransferDTO>();

		for (Transfer transfer : listOfTransfers) {
			listOfTransfersDTO.add(transferMapper
					.toTransferDTO(transfer));
		}
		log.info(" ====> FIND All TRANSFER for a user Successfull"
				+ " :" + listOfTransfersDTO);

		return listOfTransfersDTO;
	}

    // *********************************************************************	

  /**
     * Find all transfer by user.
     *
     * @param userDTO the user DTO
     * @param pageable the pageable
     * @return the page
     */
    @Override
  public Page<TransferDTO> findAllTransferByUser(
		  final UserDTO userDTO,
		  final Pageable pageable) {

      User user = userMapper.toUserDO(userDTO);

      Page<TransferDTO> pagesTransferDTO = transferRepository
      		.findAllTransferByUser(user, pageable)
      		.map(transferMapper::toTransferDTO);

      return pagesTransferDTO;
  }

    // *********************************************************************
    /**
     * Last three transfers.
     *
     * @param userDTO the user DTO
     * @param pageable the pageable
     * @return the page
     */
    @Override
    public Page<TransferDTO> lastThreeTransfers(
    		final UserDTO userDTO,
    		final Pageable pageable) {

    	User user = userMapper.toUserDO(userDTO);

    	Page<TransferDTO> pagesTransferDTO = transferRepository
        		.lastThreeTransfers(user, pageable)
        		.map(transferMapper::toTransferDTO);

    	return pagesTransferDTO;
    }

    // *********************************************************************

    /**
     * Adds the transfer.
     *
     * @param rib the rib
     * @param amount the amount
     * @param type the type
     * @param user the user
     * @return the transfer DTO
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
    public TransferDTO addTransfer(
    		final String rib,
    		final double amount,
    		final String type,
    		final User user) {

        Transfer transfer = new Transfer(
        		rib,
        		LocalDate.now(),
        		amount,
        		type,
        		user);

        if (tranferDataVerification(transfer)) {

                    if (transfer
                    		.getType()
                    		.equals(TransferType.CREDIT.toString())) {
                        savingCreditAccountOperation(transfer);
                    } else {
                        savingDebitAccountOperation(transfer);
                    }
                return transferMapper
                		.toTransferDTO(transfer);

        } else {
            throw new DataNotConformException("invalid transfer,"
            		+ " data problem !");
        }
    }

    // **********************************************************************
  	/**
     * Saving credit account operation.
     *
     * @param transfer the transfer
     */
  	private void savingCreditAccountOperation(final Transfer transfer) {
  		Double newWallet =  (double) Math.round((transfer
  				.getUser()
  				.getWalletAmount()
  				+ transfer.getAmount()) * 100) / 100;

  		transfer.getUser().setWalletAmount(newWallet);

  		transferRepository.save(transfer);

		userService.saveUser(userMapper
  				.toUserDTO(transfer
  						.getUser()));

  	}

      // *********************************************************************
  	/**
       * Saving debit account operation.
       *
       * @param transfer the transfer
       */
  	private void savingDebitAccountOperation(final Transfer transfer) {
  		if (walletOperation(transfer)) {
  		    Double newWallet =  (double) Math.round((transfer
  		    		.getUser()
  		    		.getWalletAmount() - transfer
  		    		.getAmount()) * 100) / 100;

  		    transfer.getUser().setWalletAmount(newWallet);
  		    transfer.getUser().setModificationDate(LocalDate.now());

  		    transferRepository.save(transfer);

  		    userService.saveUser(userMapper
  		    		.toUserDTO(transfer.getUser()));

  		} else {
  		    throw new DataNotConformException("the amount exceeds the wallet");
  		}
  	}


      // *********************************************************************

      /**
       * Wallet operation.
       *
       * @param transfer the transfer
       * @return the boolean
       */
      private Boolean walletOperation(final Transfer transfer) {

      	if ( transfer.getAmount() == 0) {
              throw new DataNotFoundException(
            		  "Wallet balance cant be less than 1");
          }

      	double wallet = transfer.getUser().getWalletAmount();
          double amount = transfer.getAmount();

          if ( wallet - amount >= 0) {
              return true;
          } else {
              return false;
          }
      }


      // ************verification data transfer valid ************************
      /**
       * Tranfer data verification.
       *
       * @param transfer the transfer
       * @return the boolean
       */
      private Boolean tranferDataVerification(final Transfer transfer) {

      	Boolean result = true;
          result = checkIfTransferValueMoreThanZero(transfer, result);

          result = checkIfUserExistAndNotNull(transfer, result);

          result = checkIfOperationTypeIsWithinCreditOrDebit(transfer, result);

          return result;
      }

      // **********************************************************************
  	/**
       * Check if operation type is within credit or debit.
       *
       * @param transfer the transfer
       * @param result the result
       * @return the boolean
       */
  	private Boolean checkIfOperationTypeIsWithinCreditOrDebit(
  			final Transfer transfer,
  			Boolean result) {

  		String type = transfer.getType();
          if ( type.equals("CREDIT") || type.equals("DEBIT")) {
          } else {
              result = false;
          }
  		return result;
  	}

      // **********************************************************************
  	/**
       * Check if user exist and not null.
       *
       * @param transfer the transfer
       * @param result the result
       * @return the boolean
       */
  	private Boolean checkIfUserExistAndNotNull(
  			final Transfer transfer,
  			Boolean result) {

  		if (transfer.getUser() != null) {
              if (userService.userExistById(
            		  transfer.getUser().getId()) == false) {
                  result = false;
                  }
          } else {
              result = false;
          }
  		return result;
  	}

      // ***********************************************************************
  	/**
       * Check if transfer value more than zero.
       *
       * @param transfer the transfer
       * @param result the result
       * @return the boolean
       */
  	private Boolean checkIfTransferValueMoreThanZero(
  			final Transfer transfer,
  			Boolean result) {

  		if (transfer.getAmount() <= 0 ) {
  			result = false;
  			};
  		return result;
  	}

    // *********************************************************************

}
