package com.eck_analytics.Model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "example")
public class Example {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //special values
    @Column(name = "mlii")
    private int mlii; //first row from csv\

    @Column(name = "v5")
    private int v5; //second row from csv

    @Column(name = "example_time")
    private int timeOfExample;

    @Column(name = "type")
    private int type;

    @Column(name = "sub")
    private int sub;

    @Column(name = "chan")
    private int chan;

    @Column(name = "num")
    private int num;

    @Column(name = "letter")
    private char letter;

    //@Column(name = "result_id")
    //private int resultId ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "result_id")
    private Result result;

    private int previous_id;

    public Example() {

    }

    public Example(int time, int type, int sub, int chan, int num, int previous_id) {
        timeOfExample = time;
        this.type = type;
        this.sub = sub;
        this.chan = chan;
        this.previous_id = previous_id;
    }

    public Example(int previous_id, int mlii, int v5) {
        this.previous_id = previous_id;
        this.mlii = mlii;
        this.v5 = v5;
    }


}
