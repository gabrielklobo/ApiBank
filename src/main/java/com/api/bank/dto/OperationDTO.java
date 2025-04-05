package com.api.bank.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record OperationDTO(AccountDTO origin, AccountDTO destination) {
}
