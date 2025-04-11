package com.api.bank.dto;

import com.api.bank.model.Account;
import com.fasterxml.jackson.annotation.JsonFormat;

public record AccountResponse(@JsonFormat(shape = JsonFormat.Shape.STRING) Long id, Double balance) {

	public Account convertAsAccount() {
		return new Account(this.id, this.balance);
	}
}
