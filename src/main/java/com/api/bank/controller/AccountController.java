package com.api.bank.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.bank.service.AccountService;

@RestController
@RequestMapping("/balance")
public class AccountController {

	private AccountService accountService;

	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}

	@GetMapping
	public ResponseEntity<Integer> getBalance(@RequestParam("account_id") Long id) {
		return accountService.findById(id)
				.map(acc -> ResponseEntity.ok().body(acc.getBalance()) )	
				.orElseGet(() -> ResponseEntity.status(404).body(0));
	}
}
