package com.eck_analytics.Model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "person")
public class Person {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int age;
    private int weight;
    private int height;
    private int sex;

    public Person(){
    }

    public Person(int id){
        this.id = id;
    }

    public Person(int id, int age, int weight, int height, int sex) {
        this.id = id;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.sex = sex;
    }

    //@OneToMany(mappedBy = "result", cascade = CascadeType.DETACH, orphanRemoval = true)
    //private List<Result> results;
}
