package com.eck_analytics.dto.response;


import com.eck_analytics.Model.AnomalyResponse;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ResultAnomayResponse {

    private List<AnomalyResponse> responses = new ArrayList<>();


}
