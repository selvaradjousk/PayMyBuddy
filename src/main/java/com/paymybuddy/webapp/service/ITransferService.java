package com.paymybuddy.webapp.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.paymybuddy.webapp.dto.TransferDTO;
import com.paymybuddy.webapp.dto.UserDTO;
import com.paymybuddy.webapp.model.User;

public interface ITransferService {

	List<TransferDTO> findAllTransfers();

	List<TransferDTO> findAllTransferByUser(UserDTO userDTO);
	
	Page<TransferDTO> findAllTransferByUser(UserDTO userDTO, Pageable pageable);
	
	Page<TransferDTO> lastThreeTransfers(UserDTO userDTO, Pageable pageable);

	TransferDTO addTransfer(String rib, double amount, String type, User userBeneficiary);

	List<TransferDTO> findAllByUserTypeCredit(UserDTO userDTO);

	List<TransferDTO> findAllByUserTypeDebit(UserDTO userDTO);

//	TransferDTO addTransfer(String rib, double amount, String type, User user);
}
