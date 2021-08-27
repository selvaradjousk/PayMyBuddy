package com.paymybuddy.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paymybuddy.webapp.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {


}
