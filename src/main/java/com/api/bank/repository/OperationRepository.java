package com.api.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.bank.model.Operation;

public interface OperationRepository extends JpaRepository<Operation, Long> {
}
