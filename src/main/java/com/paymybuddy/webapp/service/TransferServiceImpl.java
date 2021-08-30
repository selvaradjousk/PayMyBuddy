package com.paymybuddy.webapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.webapp.dto.TransferDTO;
import com.paymybuddy.webapp.dto.UserDTO;
import com.paymybuddy.webapp.model.Transfer;
import com.paymybuddy.webapp.repository.TransferRepository;
import com.paymybuddy.webapp.util.TransferMapper;

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

	@Override
	public List<TransferDTO> findAllTransfers() {
		List<Transfer> listOfTransfers = transferRepository.findAll();

		List<TransferDTO> listOfTransfersDTO = new ArrayList<TransferDTO>();

		for (Transfer transfer : listOfTransfers) {
			listOfTransfersDTO.add(transferMapper.toTransferDTO(transfer));
		}
		return listOfTransfersDTO;
	}



}
