package com.api.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.bank.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
