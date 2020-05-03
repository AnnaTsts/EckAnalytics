package com.eck_analytics.Model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "tact")
public class Tact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "start_time")
    private Timestamp startTime;

    @Column(name = "end_time")
    private Integer endTime;

    @Column(name = "tact_string")
    private Integer tactString;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "result_id")
    private Result result;

}
