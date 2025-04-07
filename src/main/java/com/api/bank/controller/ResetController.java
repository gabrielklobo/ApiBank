package com.api.bank.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.bank.service.ResetService;

@RestController
@RequestMapping("/reset")
public class ResetController {

	private ResetService resetService;
	
	public ResetController(ResetService resetService) {
		this.resetService = resetService;
	}
	
	@PostMapping
	public ResponseEntity<String> reset() {
		resetService.resetAll();
		return ResponseEntity.ok().body("OK");
	}
}
