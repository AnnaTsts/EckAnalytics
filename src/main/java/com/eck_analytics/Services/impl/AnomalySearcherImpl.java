package com.eck_analytics.Services.impl;

import com.eck_analytics.Model.Anomaly;
import com.eck_analytics.Services.AnomalySearcher;
import com.eck_analytics.Services.AnomalyService;
import com.eck_analytics.Services.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class AnomalySearcherImpl implements AnomalySearcher {
    private AnomalyService anomalyService;
    private ResultService resultService;

    @Autowired
    public AnomalySearcherImpl(AnomalyService anomalyService, ResultService resultService) {
        this.anomalyService = anomalyService;
        this.resultService = resultService;
    }

    @Override
    public void getAnomalyInResult(int idOfResult) {
        List<Anomaly> anomalies = anomalyService.findAllAnomaly();
        String currResultString = resultService.findResult(idOfResult).getResultString();
        getAnomalyInString(currResultString, anomalies);
    }

    /***
     * check every symbol in result without changing elements - the simplest algorithm
     * @param result -string that should be checked
     * @param anomalies -list of anomaly that can be in linguistic chain
     * @return map where int - is start index of firs letter from anomaly and anomaly is info about anomaly
     */
    @Override
    public Map<Integer, Anomaly> getAnomalyInString(String result, List<Anomaly> anomalies) {
        Map<Integer, Anomaly> anomalyMap = new LinkedHashMap<>();
        for (Anomaly anomaly : anomalies) {
            for (int i = 0; i < result.length() - LinguisticChainServiceImpl.CHAR_IN_ANOMALY; i++) {
                String currPartFromResult = result.substring(i, i + LinguisticChainServiceImpl.CHAR_IN_ANOMALY);
                if (currPartFromResult.equals(anomaly)) // TODO Check String to Object equals
                    anomalyMap.put(i, anomaly);
            }
        }
        return anomalyMap;
    }
}


//зберігання