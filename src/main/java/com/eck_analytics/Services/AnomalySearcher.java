package com.eck_analytics.Services;

import com.eck_analytics.Model.Anomaly;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public interface AnomalySearcher {
    void getAnomalyInResult(int idOfResult);

    /***
     * check every symbol in result without changing elements - the simplest algorithm
     * @param result -string that should be checked
     * @param anomalies -list of anomaly that can be in linguistic chain
     * @return map where int - is start index of firs letter from anomaly and anomaly is info about anomaly
     */
    Map<Integer, Anomaly> getAnomalyInString(String result, List<Anomaly> anomalies);
}