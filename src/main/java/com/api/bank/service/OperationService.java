package com.api.bank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.api.bank.model.Operation;
import com.api.bank.repository.OperationRepository;

@Service
public class OperationService {

	private final OperationRepository operationRepository;

	public OperationService(OperationRepository operationRepository) {
		this.operationRepository = operationRepository;
	}

	public List<Operation> findAll() {
		return operationRepository.findAll();
	}

	public Optional<Operation> findById(Long id) {
		return operationRepository.findById(id);
	}

	public Operation save(Operation operation) {
		return operationRepository.save(operation);
	}

	public Optional<Operation> update(Operation operation) {
		return operationRepository.findById(operation.getId()).map(o -> operationRepository.save(operation));
	}

	public void deleteById(Long id) {
		operationRepository.deleteById(id);
	}
}
