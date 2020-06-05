package com.eck_analytics.dto.response;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ResultAnomayResponse {

    private List<AnomalyResponse> responses = new ArrayList<>();


    @Data
    public class AnomalyResponse{
        private List<Integer> numbers;
        private List<Character> letters;
        private String type;
        private Double probability;
    }
}
