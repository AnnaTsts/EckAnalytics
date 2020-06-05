package com.eck_analytics.Services.impl;


import com.eck_analytics.Algorithm.DamerauLevenshtein;
import com.eck_analytics.Algorithm.XСompression;
import com.eck_analytics.Algorithm.YСompression;
import com.eck_analytics.Model.Anomaly;
import com.eck_analytics.Services.AnomalySearcher;
import com.eck_analytics.Services.AnomalyService;
import com.eck_analytics.Services.EKGAnalyze;
import com.eck_analytics.Services.LinguisticChainService;
import com.eck_analytics.Utils.Alphabet;
import com.eck_analytics.Utils.Constants;
import com.eck_analytics.Utils.LinguisticChainBuilder;
import com.eck_analytics.dto.response.ResultAnomayResponse;
import org.apache.commons.text.similarity.HammingDistance;
import org.apache.commons.text.similarity.JaroWinklerDistance;
import org.apache.commons.text.similarity.LevenshteinDistance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;


public class EKGAnalyzeImpl implements EKGAnalyze {
    LevenshteinDistance levenshtein = new LevenshteinDistance();
    JaroWinklerDistance jaroWinkler = new JaroWinklerDistance();
    HammingDistance hammingDistance = new HammingDistance();
    DamerauLevenshtein damerauLevenshtein = new DamerauLevenshtein();
    YСompression yСompression = new YСompression();
    XСompression xСompression = new XСompression();

    @Autowired
    private AnomalyService anomalyService;

    @Override
    public ResultAnomayResponse getEKGresult(MultipartFile file) throws IOException {
        String currString = "";
        ResultAnomayResponse resultAnomayResponse = new ResultAnomayResponse();

        BufferedReader br;
        try {

            String line;
            InputStream is = file.getInputStream();
            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                //LINE PROCESSING
                Integer value = Integer.valueOf(line);
                if (currString.length() < Constants.LinguisticConstant.ANOMALYSIZE) {
                    currString = currString + LinguisticChainBuilder.getLetter(value, Alphabet.TEST_ARRAY);
                } else {


                    int length = currString.length();
                    currString = currString.substring(0, length - 1);
                    currString = currString + LinguisticChainBuilder.getLetter(value, Alphabet.TEST_ARRAY);
                }

            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return resultAnomayResponse;
    }

    public double probabilityOfAnomaly(String curr) {
        double maxComparison =0 ;

        List<Anomaly> allAnomaly = anomalyService.findAllAnomaly();
        for (Anomaly anomaly : allAnomaly
        ) {
            if (anomaly.getAnomalyString().compareTo(curr) == 0)
                return 1.0;
        }
        //   Y  COMPARISON
        for (Anomaly anomaly : allAnomaly
        ) {
            char[] releaseAnomaly = yСompression.release(anomaly.getAnomalyString().toCharArray(), 1.2);
            if (releaseAnomaly.toString().compareTo(curr) == 0)
                return 1.0;
            releaseAnomaly = yСompression.release(anomaly.getAnomalyString().toCharArray(), 0.8);
            if (releaseAnomaly.toString().compareTo(curr) == 0)
                return 1.0;
        }
        //   X  COMPARISON
        for (Anomaly anomaly : allAnomaly
        ) {
            String anomalyString = "";
            double border = anomaly.getAnomalyString().length() * 0.1;
            char[] releaseAnomaly = xСompression.release(anomaly.getAnomalyString().toCharArray(), 1.2);
            for (int i = ((int) border); i < releaseAnomaly.length - border; i++)
                anomalyString = anomalyString + releaseAnomaly[i];
            if (anomalyString.compareTo(curr) == 0)
                return 1.0;

            border = curr.length() * 0.1;
            anomalyString = "";
            char[] currStr = curr.toCharArray();
            releaseAnomaly = xСompression.release(anomaly.getAnomalyString().toCharArray(), 0.8);
            for (int i = ((int) border); i < curr.length() - border; i++)
                anomalyString = anomalyString + currStr[i];
            if (releaseAnomaly.toString().compareTo(anomalyString) == 0)
                return 1.0;
        }
        for (Anomaly anomaly : allAnomaly
        ) {
            double compareWithDistance = compareWithDistance(anomaly.getAnomalyString(), curr);
            if (compareWithDistance>maxComparison){
                maxComparison = compareWithDistance;
            }
        }

        return maxComparison;
    }

    private double compareWithDistance(String curr, String anomaly) {
        Double d1 = jaroWinkler.apply(curr, anomaly);
        Double d2 = 1 - ((double) levenshtein.apply(curr, anomaly) / curr.length());
        Double d3 = 1 - ((double) hammingDistance.apply(curr, anomaly) / curr.length());
        Double d4 = 1 - ((double) damerauLevenshtein.calculateDistance(curr, anomaly) / curr.length());

        return (d1 + d2 + d3 + d4) / 4;

    }

}
