package com.api.bank.dto;

public record EventRequest(String type, Integer origin, Integer amount, Integer destination) {
}
