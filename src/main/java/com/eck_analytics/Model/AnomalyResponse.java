package com.eck_analytics.Model;

import lombok.Data;

import java.util.List;

@Data
public class AnomalyResponse{
    private List<Integer> numbers;
    private char[] letters;
    private String type;
    private Double probability;

    public AnomalyResponse(List<Integer> numbers,String currString,String type,Double probability){
        this.numbers  = numbers;
        letters = currString.toCharArray();
        this.type = type;
        this.probability = probability;
    }
}