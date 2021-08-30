package com.paymybuddy.webapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.paymybuddy.webapp.dto.TransferDTO;
import com.paymybuddy.webapp.dto.UserDTO;
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



	
	


}
