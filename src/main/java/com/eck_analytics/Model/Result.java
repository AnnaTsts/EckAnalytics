package com.eck_analytics.Model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "result")
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "result_string")
    private String resultString;

    private int anomaly;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private Person person;

    public Result(String resultString,int anomaly){
        this.resultString = resultString;
        this.anomaly = anomaly;
    }
}
