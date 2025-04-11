package com.api.bank.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.api.bank.dto.EventDTO;
import com.api.bank.model.Account;
import com.api.bank.repository.EventRepository;

@ExtendWith(MockitoExtension.class)
public class EventServiceTest {

	@Mock
	private EventRepository eventRepository;

	@InjectMocks
	private EventService eventService;

	private Account testAcccount;

	@BeforeEach
	void setup() {
		testAcccount = new Account(1L, 100.0);
	}

	@Test
	void testGetCountByDestinationId() {
		when(eventRepository.getCountByDestinationId(1)).thenReturn(3);

		Integer result = eventService.getCountByDestinationId(1);

		assertThat(result).isEqualTo(3);
	}

	@Test
	void testGetCountByOriginIdNativeQuery() {
		when(eventRepository.getCountByOriginIdNativeQuery(2)).thenReturn(5);

		Integer result = eventService.getCountByOriginIdNativeQuery(2);

		assertThat(result).isEqualTo(5);
	}

	@Test
	void testCountByDestination() {
		when(eventRepository.countByDestination(testAcccount)).thenReturn(4);

		Integer result = eventService.countByDestination(testAcccount);

		assertThat(result).isEqualTo(4);
	}

	@Test
	void testGetEventsByType() {
		List<EventDTO> mockList = List.of(new EventDTO(1L, 100.0), new EventDTO(2L, 200.0));

		when(eventRepository.getEventsByType("deposit")).thenReturn(mockList);

		List<EventDTO> result = eventService.getEventsByType("deposit");

		assertThat(result).hasSize(2);
		assertThat(result.get(0).eventId()).isEqualTo(1L);
		assertThat(result.get(0).amount()).isGreaterThan(50.0);
		assertThat(result.get(1).amount()).isLessThan(250.0);
	}

	@Test
	void testGetEventsMappedByType() {
		List<Object[]> mockList = List.of(new Object[] { 1L, 100.0 }, new Object[] { 2L, 200.0 });
		
		when(eventRepository.getEventsMappedByType("withdraw")).thenReturn(mockList);
		
		List<EventDTO> result = eventService.getEventsMappedByType("withdraw");
		
		assertThat(result).hasSize(2);
		assertThat(result.get(0).eventId()).isEqualTo(1L);
		assertThat(result.get(0).amount()).isGreaterThan(50.0);
		assertThat(result.get(1).amount()).isLessThan(250.0);
	}
}
