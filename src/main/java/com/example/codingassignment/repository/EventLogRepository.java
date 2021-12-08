package com.example.codingassignment.repository;

import com.example.codingassignment.model.EventLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventLogRepository extends JpaRepository<EventLog, String> {
}
