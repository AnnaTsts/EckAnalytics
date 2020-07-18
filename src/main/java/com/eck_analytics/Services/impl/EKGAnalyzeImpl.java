package com.eck_analytics.Services.impl;


import com.eck_analytics.Algorithm.DamerauLevenshtein;
import com.eck_analytics.Algorithm.XСompression;
import com.eck_analytics.Algorithm.YСompression;
import com.eck_analytics.Model.Anomaly;
import com.eck_analytics.Model.AnomalyResponse;
import com.eck_analytics.Model.AnomalyType;
import com.eck_analytics.Services.AnomalySearcher;
import com.eck_analytics.Services.AnomalyService;
import com.eck_analytics.Services.EKGAnalyze;
import com.eck_analytics.Services.LinguisticChainService;
import com.eck_analytics.Utils.Alphabet;
import com.eck_analytics.Utils.Constants;
import com.eck_analytics.Utils.LinguisticChainBuilder;
import com.eck_analytics.dto.response.ResultAnomayResponse;
import io.micrometer.core.instrument.util.JsonUtils;
import org.apache.commons.text.similarity.HammingDistance;
import org.apache.commons.text.similarity.JaroWinklerDistance;
import org.apache.commons.text.similarity.LevenshteinDistance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class EKGAnalyzeImpl implements EKGAnalyze {
    LevenshteinDistance levenshtein = new LevenshteinDistance();
    JaroWinklerDistance jaroWinkler = new JaroWinklerDistance();
    HammingDistance hammingDistance = new HammingDistance();
    DamerauLevenshtein damerauLevenshtein = new DamerauLevenshtein();
    YСompression yСompression = new YСompression();
    XСompression xСompression = new XСompression();
    int anomalyType =0;
    @Autowired
    private AnomalyService anomalyService;

    List<Anomaly> allAnomaly;

    @Override
    public ResultAnomayResponse getEKGresult(MultipartFile file) throws IOException {
        allAnomaly = anomalyService.findAllAnomaly();
        System.out.println("Start operation");
        String currString = "";
        List<Integer> numbers = new ArrayList<>();
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

                    numbers.add(value);

                } else {
                    double probabilityOfAnomaly = probabilityOfAnomaly(currString);

                    if (probabilityOfAnomaly > 0.87) {
                        resultAnomayResponse.getResponses().add(new AnomalyResponse(numbers, currString, AnomalyType.getTypeString(anomalyType), probabilityOfAnomaly));
                        currString = "";
                        numbers = new ArrayList<>();
                    } else {
                        int length = currString.length();
                        currString = currString.substring(1, length );
                        currString = currString + LinguisticChainBuilder.getLetter(value, Alphabet.TEST_ARRAY);
                        numbers.remove(0);
                        numbers.add(value);


                    }
                }

            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

//        for (AnomalyResponse r : resultAnomayResponse.getResponses()
//        ) {
//            System.out.println(r.getLetters());
//            System.out.println(r.getNumbers());
//            System.out.println(r.getProbability());
//            System.out.println("!!!!!!!!!!!!");
//        }

        return resultAnomayResponse;
    }

    public double probabilityOfAnomaly(String curr) {

        String currString = curr;

        double maxComparison = 0;
        for (Anomaly anomaly : allAnomaly
        ) {
            if (anomaly.getAnomalyString().compareTo(curr) == 0)
            {
                anomalyType = anomaly.getAnomalyType();
                return 1.0;
            }
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
//           X  COMPARISON
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
            List<Double> compareWithDistance = compareWithDistance(currString, anomaly);
            Double d = compareWithDistance.get(0);
            if (d > maxComparison&& d>0.6) {
                maxComparison = d;
                anomalyType = compareWithDistance.get(1).intValue();
            }
        }

        return maxComparison;
    }

    private List<Double> compareWithDistance(String curr, Anomaly anomaly) {
//        System.out.println(anomaly.length());
//        System.out.println(curr.length());
        String compare=anomaly.getAnomalyString();
        if (compare.length() > 50)
             compare = anomaly.getAnomalyString().substring(0, 50);

        if (curr.length() == compare.length()) {
            Double d1 = jaroWinkler.apply(curr, compare);
//            Double d2 = 1 - ((double) levenshtein.apply(curr, anomaly) / curr.length());
//            Double d3 = 1 - ((double) hammingDistance.apply(curr, anomaly) / curr.length());
//            Double d4 = 1 - ((double) damerauLevenshtein.calculateDistance(curr, anomaly) / curr.length());
//            System.out.println(d1+"   "+ d2+"   "+d3+"   "+d4);
//            System.out.println(levenshtein.apply(curr, anomaly));
//            System.out.println(d1 + "   " + d2 + "   " + d3 + "   " + d4 + "   ");
//            System.out.println(compare(curr,anomaly));
            //            return (d1 + d2 + d3 + d4) / 4;
            ArrayList<Double> result = new ArrayList<>();
            result.add(d1);
            result.add(Double.valueOf(anomaly.getAnomalyType()));
            return result;

        }
        ArrayList<Double> result = new ArrayList<>();
        result.add(0.0);
        result.add(Double.valueOf(anomaly.getAnomalyType()));
        return result;

    }

    public double compare(String curr, String anomaly) {
        int sum =0;
        for (int i = 0; i < curr.length(); i++) {
            sum+= Math.abs(curr.toCharArray()[i]-anomaly.toCharArray()[i]);
        }

        return ((double) sum)/(curr.length()*100);
    }

}
