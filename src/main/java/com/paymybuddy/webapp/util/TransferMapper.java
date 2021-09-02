package com.paymybuddy.webapp.util;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.paymybuddy.webapp.dto.TransferDTO;
import com.paymybuddy.webapp.model.Transfer;

/**
 * The Class TransferMapper.
 */
@Component
public class TransferMapper {

	LocalDate createDate = LocalDate.now();	

    /**
 * To transfer DO.
 *
 * @param transferDTO the transfer DTO
 * @return the transfer
 */
public Transfer toTransferDO(TransferDTO transferDTO) {
        Transfer transfer = new Transfer();
        if (transferDTO !=null){
        transfer.setIdTransfer(transferDTO.getIdTransfer());
        transfer.setRib(transferDTO.getRib());
        transfer.setCreateDate(transferDTO.getCreateDate());
        transfer.setAmount(transferDTO.getAmount());
        transfer.setType(transferDTO.getType());
        transfer.setUser(transferDTO.getUser());

        return transfer;
        	} else{
        		return null;
        	}
     }

    /**
     * To transfer DTO.
     *
     * @param transfer the transfer
     * @return the transfer DTO
     */
    public TransferDTO toTransferDTO(Transfer transfer) {
        TransferDTO transferDTO = new TransferDTO();

        if (transfer !=null){
        transferDTO.setIdTransfer(transfer.getIdTransfer());
        transferDTO.setRib(transferDTO.getRib());
        transferDTO.setCreateDate(transfer.getCreateDate());
        transferDTO.setAmount(transfer.getAmount());
        transferDTO.setType(transfer.getType());
        transferDTO.setUser(transfer.getUser());

        return transferDTO;
        } else{
            return null;
            }
    }
}