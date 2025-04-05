package com.api.bank.service;

import org.springframework.stereotype.Service;

import com.api.bank.repository.AccountRepository;
import com.api.bank.repository.OperationRepository;

@Service
public class ResetService {

	private final AccountRepository accountRepository;
	private final OperationRepository operationRepository;
	
	public ResetService(AccountRepository accountRepository, OperationRepository operationRepository) {
		this.accountRepository = accountRepository;
		this.operationRepository = operationRepository;
	}
	
	public void resetAll() {
		operationRepository.deleteAll();
		accountRepository.deleteAll();
	}
}
