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

@Log4j2
@Service
public class TransferServiceImpl implements ITransferService {

	// **************************** TODOs LIST ***********************************

	// Method:
	// --> getAllTransfers(): List(TransfersDTO) served by
	// TransferMapper.toDTO(transfer)
	// --> getAllTransfersByUser(UserDTO); TransferDTO by pages
	// --> addTransfer(transferDTO): transferDTO
	// --> pagination of transfer list
	// --> Before validation of Transfer, Transaction approval done by checking the
	// account balance amount

	@Autowired
	TransferRepository transferRepository;

    @Autowired
    IUserService userService;
	
	public TransferMapper transferMapper = new TransferMapper();

	public UserMapper userMapper = new UserMapper();

	// *******************************************************************
	
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
	
	@Override
	public List<TransferDTO> findAllTransferByUser(UserDTO userDTO) {
		
		User user = userMapper.toUserDO(userDTO);
		
		List<Transfer> listOfTransfers = transferRepository.findAllByUser(user);

		log.info(" ====> FIND All TRANSFER for a user requested <==== ");

		List<TransferDTO> listOfTransfersDTO = new ArrayList<TransferDTO>();

		for (Transfer transfer : listOfTransfers) {
			listOfTransfersDTO.add(transferMapper.toTransferDTO(transfer));
		}
		log.info(" ====> FIND All TRANSFER for a user Successfull <==== ");
		return listOfTransfersDTO;
	}

    // ************************************************************************

  @Override
  public Page<TransferDTO> findAllTransferByUser(UserDTO userDTO, Pageable pageable) {


      User user = userMapper.toUserDO(userDTO);

      Page<TransferDTO> pagesTransferDTO = transferRepository
      		.findAllTransferByUser(user, pageable)
      		.map(transferMapper::toTransferDTO);

      return pagesTransferDTO;
  }



    // ************************************************************************
    @Override
    public Page<TransferDTO> lastThreeTransfers(
    		UserDTO userDTO,
    		Pageable pageable) {

    	User user = userMapper.toUserDO(userDTO);

    	Page<TransferDTO> pagesTransferDTO = transferRepository
        		.lastThreeTransfers(user, pageable)
        		.map(transferMapper::toTransferDTO);

    	return pagesTransferDTO;
    }

    // ************************************************************************
    
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
    public TransferDTO addTransfer(
    		String rib,
    		double amount,
    		String type,
    		User user) {
        
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

        }else{
            throw new DataNotConformException("invalid transfer,"
            		+ " data problem !");
        }
    }
    
    // *************************************************************************
  	/**
  	 * @param transfer
  	 */
  	private void savingCreditAccountOperation(Transfer transfer) {
  		Double newWallet =  (double)Math.round((transfer
  				.getUser()
  				.getWalletAmount()
  				+ transfer.getAmount())*100)/100;

  		transfer.getUser().setWalletAmount(newWallet);

  		transferRepository.save(transfer);

		userService.saveUser(userMapper
  				.toUserDTO(transfer
  						.getUser()));

  	}

      // ************************************************************************
  	/**
  	 * @param transfer
  	 */
  	private void savingDebitAccountOperation(Transfer transfer) {
  		if (walletOperation(transfer)) {
  		    Double newWallet =  (double)Math.round((transfer
  		    		.getUser()
  		    		.getWalletAmount() - transfer
  		    		.getAmount())*100)/100;
  		    
  		    transfer.getUser().setWalletAmount(newWallet);
  		    transfer.getUser().setModificationDate(LocalDate.now());

  		    transferRepository.save(transfer);


  		    userService.saveUser(userMapper
  		    		.toUserDTO(transfer.getUser()));


  		} else {
  		    throw new DataNotConformException("the amount exceeds the wallet");
  		}
  	}

      
    
  	
  	
  	

      // ************************************************************************
      
      private Boolean walletOperation(Transfer transfer) {

      	if ( transfer.getAmount() == 0) {
              throw new DataNotFoundException("Wallet balance cant be less than 1");
          }

      	double wallet = transfer.getUser().getWalletAmount();
          double amount = transfer.getAmount();

          if( wallet - amount >= 0) {
              return true;
          }else {
              return false;
          }
      }

      // ************verification data transfer valid **************************
      private Boolean tranferDataVerification(Transfer transfer){

      	Boolean resultat = true;
          resultat = checkIfTransferValueMoreThanZero(transfer, resultat);

          resultat = checkIfUserExistAndNotNull(transfer, resultat);

          resultat = checkIfOperationTypeIsWithinCreditOrDebit(transfer, resultat);

          return resultat;
      }

      // ************************************************************************
  	/**
  	 * @param transfer
  	 * @param resultat
  	 * @return
  	 */
  	private Boolean checkIfOperationTypeIsWithinCreditOrDebit(Transfer transfer, Boolean resultat) {
  		String type = transfer.getType();
          if ( type.equals("CREDIT") || type.equals("DEBIT")) {
          }else{
              resultat=false;
          }
  		return resultat;
  	}

      // ************************************************************************
  	/**
  	 * @param transfer
  	 * @param resultat
  	 * @return
  	 */
  	private Boolean checkIfUserExistAndNotNull(Transfer transfer, Boolean resultat) {
  		if (transfer.getUser() !=null) {
              if (userService.userExistById(transfer
              		.getUser().getId()) == false) {
                  resultat = false;
                  }
          }else {
              resultat = false;
          }
  		return resultat;
  	}

      // ************************************************************************
  	/**
  	 * @param transfer
  	 * @param resultat
  	 * @return
  	 */
  	private Boolean checkIfTransferValueMoreThanZero(
  			Transfer transfer,
  			Boolean resultat) {

  		if (transfer.getAmount() <= 0 ) {
              resultat = false;};
  		return resultat;
  	}

      // ************************************************************************ 
    
	


    // ************************************************************************



	
	


}
