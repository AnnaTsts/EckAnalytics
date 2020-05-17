package com.eck_analytics.Model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "anomaly")
public class Anomaly {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "anomaly_string")
    private String anomalyString;

    @Column(name = "anomaly_type_id")
    private int anomalyType;

    public Anomaly() {
    }

    public Anomaly(String anomalyString, int anomalyType) {
        this.anomalyString = anomalyString;
        this.anomalyType = anomalyType;
    }
}

