package com.paymybuddy.webapp.util;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.paymybuddy.webapp.dto.TransferDTO;
import com.paymybuddy.webapp.model.Transfer;

@Component
public class TransferMapper {

	LocalDate createDate = LocalDate.now();	

    public Transfer toTransferDO(TransferDTO transferDTO) {
        Transfer transfer = new Transfer();

        transfer.setIdTransfer(transferDTO.getIdTransfer());
        transfer.setRib(transferDTO.getRib());
        transfer.setCreateDate(transferDTO.getCreateDate());
        transfer.setAmount(transferDTO.getAmount());
        transfer.setType(transferDTO.getType());
        transfer.setUser(transferDTO.getUser());

        return transfer;
     }

    public TransferDTO toTransferDTO(Transfer transfer) {
        TransferDTO transferDTO = new TransferDTO();

        transferDTO.setIdTransfer(transfer.getIdTransfer());
        transferDTO.setRib(transferDTO.getRib());
        transferDTO.setCreateDate(transfer.getCreateDate());
        transferDTO.setAmount(transfer.getAmount());
        transferDTO.setType(transfer.getType());
        transferDTO.setUser(transfer.getUser());

        return transferDTO;
    }
}