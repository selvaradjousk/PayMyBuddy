package com.paymybuddy.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paymybuddy.webapp.model.Transfer;
import com.paymybuddy.webapp.model.User;

public interface TransferRepository extends JpaRepository<Transfer, Integer> {

	Transfer findByUser(User user);

}
