package com.api.bank.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.api.bank.dto.AccountResponse;
import com.api.bank.dto.EventDTO;
import com.api.bank.dto.EventRequest;
import com.api.bank.dto.EventResponse;
import com.api.bank.model.Account;
import com.api.bank.model.Event;
import com.api.bank.repository.EventRepository;

import jakarta.transaction.Transactional;

@Service
public class EventService {

	private final EventRepository eventRepository;
	private final AccountService accountService;

	public EventService(EventRepository eventRepository, AccountService accountService) {
		this.eventRepository = eventRepository;
		this.accountService = accountService;
	}

	public List<Event> findAll() {
		return eventRepository.findAll();
	}

	public Optional<Event> findById(Long id) {
		return eventRepository.findById(id);
	}

	@Transactional
	public EventResponse save(EventRequest eventRequest) {
		EventResponse eventResponse = handleOperation(eventRequest);

		Event event = new Event();
		event.setOrigin(eventResponse.origin() != null ? eventResponse.origin().convertAsAccount() : null);
		event.setDestination(eventResponse.destination() != null ? eventResponse.destination().convertAsAccount() : null);
		event.setAmount(eventRequest.amount());
		event.setType(eventRequest.type());

		eventRepository.save(event);

		return eventResponse;
	}

	public Optional<Event> update(Event event) {
		return eventRepository.findById(event.getId()).map(o -> eventRepository.save(event));
	}

	public void deleteById(Long id) {
		eventRepository.deleteById(id);
	}

	private EventResponse handleOperation(EventRequest eventRequest) {
		if (eventRequest.type().equals("deposit")) {
			AccountResponse accResponse = accountService.deposit(eventRequest.destination(), eventRequest.amount());
			return new EventResponse(null, accResponse);
		} else if (eventRequest.type().equals("withdraw")) {
			AccountResponse accResponse = accountService.withdraw(eventRequest.origin(), eventRequest.amount());
			return new EventResponse(accResponse, null);
		} else {
			AccountResponse accResponseWithdraw = accountService.withdraw(eventRequest.origin(), eventRequest.amount());
			AccountResponse accResponseDeposit = accountService.deposit(eventRequest.destination(), eventRequest.amount());
			return new EventResponse(accResponseWithdraw, accResponseDeposit);
		}
	}
	
	public Integer getCountByDestinationId(Integer destinationId) {
		return eventRepository.getCountByDestinationId(destinationId);
	}
	
	public Integer getCountByOriginIdNativeQuery(Integer originId) {
		return eventRepository.getCountByOriginIdNativeQuery(originId);
	}
	
	public Integer countByDestination(Account account) {
		return eventRepository.countByDestination(account);
	}
	
	public List<EventDTO> getEventsByType(String type) {
		return eventRepository.getEventsByType(type);
	}
	
	public List<EventDTO> getEventsMappedByType(String type){
		List<Object[]> results = eventRepository.getEventsMappedByType(type);
		List<EventDTO> resultsDTO = new ArrayList<>();
		
		for(Object[] row : results) {
			Long id = (Long) row[0];
			double amount = (double) row[1];
			
			resultsDTO.add(new EventDTO(id, amount));
		}
		
		return resultsDTO;
	}
}
