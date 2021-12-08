package com.example.codingassignment.processor;

import com.example.codingassignment.mapper.EventMapper;
import com.example.codingassignment.model.EventLog;
import com.example.codingassignment.model.dto.EventLogDto;
import com.example.codingassignment.repository.EventLogRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class FileProcessor {
    private final EventMapper eventMapper;
    private final EventLogRepository eventLogRepository;

    public FileProcessor(EventMapper eventMapper, EventLogRepository eventLogRepository) {
        this.eventMapper = eventMapper;
        this.eventLogRepository = eventLogRepository;
    }

    public void processFile(String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<EventLogDto> eventLogDtoList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                EventLogDto eventLogDto = objectMapper.readValue(line, EventLogDto.class);
                eventLogDtoList.add(eventLogDto);
            }
        }

        saveToDb(eventMapper.mapFromDto(eventLogDtoList));

    }

    private void saveToDb(List<EventLog> eventLogs) {
        for (EventLog eventLog : eventLogs) {
            eventLogRepository.save(eventLog);
        }
    }
}
