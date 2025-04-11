package com.api.bank.dto;

public record EventRequest(String type, Integer origin, Double amount, Integer destination) {
}
