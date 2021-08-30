package com.paymybuddy.webapp.service;

import java.util.List;

import com.paymybuddy.webapp.dto.TransferDTO;
import com.paymybuddy.webapp.dto.UserDTO;

public interface ITransferService {

	List<TransferDTO> findAllTransfers();

	List<TransferDTO> findAllTransferByUser(UserDTO userDTO);
}
