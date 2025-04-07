package com.api.bank.controller;

import java.util.NoSuchElementException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.bank.dto.EventRequest;
import com.api.bank.dto.EventResponse;
import com.api.bank.service.EventService;

@RestController
@RequestMapping("/event")
public class EventController {
	private EventService eventService;

	public EventController(EventService eventService) {
		this.eventService = eventService;
	}

	@PostMapping
	public ResponseEntity<Object> event(@RequestBody EventRequest eventRequest) {
		try {
			EventResponse eventResponse = eventService.save(eventRequest);

			return ResponseEntity.status(201).body(eventResponse);
		} catch (NoSuchElementException nsee) {
			return ResponseEntity.status(404).body(0);
		} catch (Exception e) {
			return ResponseEntity.status(500).build();
		}
	}

}
