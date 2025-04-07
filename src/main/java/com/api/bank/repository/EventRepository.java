package com.api.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.bank.model.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
}
