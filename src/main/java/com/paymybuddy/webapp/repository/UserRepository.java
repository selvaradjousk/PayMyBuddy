package com.paymybuddy.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paymybuddy.webapp.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
