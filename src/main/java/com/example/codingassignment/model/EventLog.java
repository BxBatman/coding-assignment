package com.example.codingassignment.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "event_logs")
public class EventLog {
    @Id
    private String id;
    @Column
    private long duration;
    @Column
    private String type;
    @Column
    private String host;
    @Column
    private boolean alert;
}
