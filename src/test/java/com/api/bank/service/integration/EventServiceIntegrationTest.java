package com.api.bank.service.integration;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.api.bank.dto.EventDTO;
import com.api.bank.model.Account;
import com.api.bank.model.Event;
import com.api.bank.repository.AccountRepository;
import com.api.bank.repository.EventRepository;
import com.api.bank.service.EventService;

@SpringBootTest
@Transactional
public class EventServiceIntegrationTest {

    @Autowired
    private EventService eventService;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private AccountRepository accountRepository;

    private Account origin;
    private Account destination;

    @BeforeEach
    void setup() {
        origin = accountRepository.save(new Account(244L, 500.0));
        destination = accountRepository.save(new Account(233L, 1000.0));

        eventRepository.save(new Event("deposit", origin, destination, 100.0));
        eventRepository.save(new Event("deposit", origin, destination, 200.0));
        eventRepository.save(new Event("withdraw", destination, origin, 50.0));
    }

    @Test
    void testGetCountByDestinationId() {
        Integer count = eventService.getCountByDestinationId(destination.getId().intValue());
        assertThat(count).isEqualTo(2);
    }

    @Test
    void testGetCountByOriginIdNativeQuery() {
        Integer count = eventService.getCountByOriginIdNativeQuery(origin.getId().intValue());
        assertThat(count).isEqualTo(2);
    }

    @Test
    void testCountByDestination() {
        Integer count = eventService.countByDestination(destination);
        assertThat(count).isEqualTo(2);
    }

    @Test
    void testGetEventsByType() {
        List<EventDTO> events = eventService.getEventsByType("deposit");
        assertThat(events).hasSize(2);
    }

    @Test
    void testGetEventsMappedByType() {
        List<EventDTO> events = eventService.getEventsMappedByType("withdraw");
        assertThat(events).hasSize(1);
        assertThat(events.get(0).amount()).isEqualTo(50.0);
    }
}
