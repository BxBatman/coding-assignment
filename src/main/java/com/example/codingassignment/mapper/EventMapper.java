package com.example.codingassignment.mapper;

import com.example.codingassignment.model.EventLog;
import com.example.codingassignment.model.dto.EventLogDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class EventMapper {
    public List<EventLog> mapFromDto(List<EventLogDto> eventLogDtoList){
        Map<String, List<EventLogDto>> segregateEventsById = segregateEventsById(eventLogDtoList);
        return mapEvents(segregateEventsById);
    }

    private List<EventLog> mapEvents(Map<String, List<EventLogDto>> eventDtoList) {
        List<EventLog> eventLogs = new ArrayList<>();
        for (Map.Entry<String, List<EventLogDto>> entry : eventDtoList.entrySet()) {
            if (entry.getValue().size() == 2) {
                eventLogs.add(mapEvent(entry.getValue()));
            }

        }
        return eventLogs;
    }

    private EventLog mapEvent(List<EventLogDto> values){
        EventLogDto starter = null;
        EventLogDto finisher = null;
        String host = null;
        String type = null;
        for (EventLogDto eventLogDto : values) {
            if (eventLogDto.getState().equals(State.STARTED.name())) {
                starter = eventLogDto;
            } else if (eventLogDto.getState().equals(State.FINISHED.name())) {
                finisher = eventLogDto;
            }
        }

        if (starter != null && starter.getHost() != null) {
            host = starter.getHost();
        }

        if (starter != null && starter.getType() != null) {
            type = starter.getType();
        }

        if (finisher != null && finisher.getHost() != null) {
            host = finisher.getHost();
        }

        if (finisher != null && finisher.getType() != null) {
            type = finisher.getType();
        }



        long duration = finisher.getTimestamp() - starter.getTimestamp();


        return EventLog.builder()
                .host(host)
                .type(type)
                .duration(duration)
                .alert(duration > 4)
                .id(starter.getId())
                .build();
    }


    private Map<String, List<EventLogDto>> segregateEventsById(List<EventLogDto> eventLogDtoList){
        Map<String, List<EventLogDto>> segregatedEvents = new HashMap<>();
        for (EventLogDto eventLogDto : eventLogDtoList) {
                if (segregatedEvents.containsKey(eventLogDto.getId())) {
                    List<EventLogDto> eventLogDtos = segregatedEvents.get(eventLogDto.getId());
                    eventLogDtos.add(eventLogDto);
                    segregatedEvents.put(eventLogDto.getId(), eventLogDtos);
                } else {
                    List<EventLogDto> eventLogDtos = new ArrayList<>();
                    eventLogDtos.add(eventLogDto);
                    segregatedEvents.put(eventLogDto.getId(), eventLogDtos);
                }
        }
        return segregatedEvents;
    }
}
