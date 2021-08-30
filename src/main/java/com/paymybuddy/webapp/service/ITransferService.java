package com.paymybuddy.webapp.service;

import java.util.List;

import com.paymybuddy.webapp.dto.TransferDTO;

public interface ITransferService {

	List<TransferDTO> findAllTransfers();

}
