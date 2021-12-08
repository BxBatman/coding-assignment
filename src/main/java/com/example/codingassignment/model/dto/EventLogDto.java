package com.example.codingassignment.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EventLogDto {
    private String id;
    private String state;
    private String type;
    private String host;
    private long timestamp;
}
