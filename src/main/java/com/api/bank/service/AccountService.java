package com.api.bank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.api.bank.model.Account;
import com.api.bank.repository.AccountRepository;

@Service
public class AccountService {
	private final AccountRepository accountRepository;

	public AccountService(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	public List<Account> findAll() {
		return accountRepository.findAll();
	}

	public Optional<Account> findById(Long id) {
		return accountRepository.findById(id);
	}

	public Account save(Account account) {
		return accountRepository.save(account);
	}

	public Optional<Account> update(Account account) {
		return accountRepository.findById(account.getId()).map(o -> accountRepository.save(account));
	}

	public void deleteById(Long id) {
		accountRepository.deleteById(id);
	}
}
