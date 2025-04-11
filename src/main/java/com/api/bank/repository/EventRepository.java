package com.api.bank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.api.bank.dto.EventDTO;
import com.api.bank.model.Account;
import com.api.bank.model.Event;

public interface EventRepository extends JpaRepository<Event, Long> {

	//Estudos usando query JPA
	@Query("SELECT COUNT(*) FROM Event e WHERE e.destination.id = :destinationId")
	Integer getCountByDestinationId(@Param("destinationId") Integer destinationId);

	//Estudos usando query nativa
	@Query(value = "SELECT COUNT(*) FROM event e WHERE origin_id = :originId", nativeQuery = true)
	Integer getCountByOriginIdNativeQuery(@Param("originId") Integer originId);
	
	/*
	 * Estudos usando nomenclatura Spring
	 * Ação(Oque eu desejo) - count
	 * Condição - By - Destination -> Pode ter mais de uma ex: and/or Origin
	 * */
	Integer countByDestination(Account destination);

	//Estudo Entity apenas alguns campos
	@Query("SELECT new com.api.bank.dto.EventDTO(e.id, e.amount) FROM Event e where e.type = :type")
	List<EventDTO> getEventsByType(@Param("type") String type);
	
	//Estudo mapeando Entity manualmente e apenas alguns campos
	@Query("SELECT e.id, e.amount FROM Event e where e.type = :type")
	List<Object[]> getEventsMappedByType(@Param("type") String type);

}
