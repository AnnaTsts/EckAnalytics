package com.eck_analytics.Services;

import com.eck_analytics.Model.Anomaly;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AnomalySearcher {
    private AnomalyService anomalyService = new AnomalyService();
    private ResultService resultService = new ResultService();

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
    public Map<Integer, Anomaly> getAnomalyInString(String result, List<Anomaly> anomalies) {
        Map<Integer, Anomaly> anomalyMap = new LinkedHashMap<>();
        for (Anomaly anomaly : anomalies) {
            for (int i = 0; i < result.length() - LinguisticChainService.CHAR_IN_ANOMALY; i++) {
                String currPartFromResult = result.substring(i, i + LinguisticChainService.CHAR_IN_ANOMALY);
                if (currPartFromResult.equals(anomaly))
                    anomalyMap.put(i, anomaly);
            }
        }
        return anomalyMap;
    }

}


//зберігання