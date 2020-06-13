package com.eck_analytics;

import com.eck_analytics.Services.ExampleService;
import com.eck_analytics.Services.LinguisticChainService;
import com.eck_analytics.Services.impl.ExampleServiceImpl;
import com.eck_analytics.Services.impl.LinguisticChainServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.similarity.HammingDistance;
import org.apache.commons.text.similarity.JaroWinklerDistance;
import org.apache.commons.text.similarity.LevenshteinDistance;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileNotFoundException;

@Slf4j
public class MainTest {
    public int priceToDelete =30;
//    @Autowired
//    ExampleService exampleService;
//    @Autowired
//    LinguisticChainServiceImpl linguisticChainService;

    public static void main(String[] args) throws FileNotFoundException {

//        linguisticChainService.getExamplesFromFiles("/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/101.csv","/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/101annotations.txt");
//         linguisticChainService.getExamplesFromFiles("/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/100.csv","/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/100annotations.txt");
//        LevenshteinDistance levenshteinDistance = new LevenshteinDistance();
//        Integer apply = levenshteinDistance.apply("olom", "oqoy");
//        JaroWinklerDistance jaroWinklerDistance = new JaroWinklerDistance();
//        Double jaro = jaroWinklerDistance.apply("olom", "olom");
//        HammingDistance hammingDistance = new HammingDistance();
//        Integer haming = hammingDistance.apply("olom", "oqoy");
//
//        log.debug("Apply: {}", apply);
//        log.debug("Jaro: {}", jaro);
//        log.debug("Haming: {}", haming);
//
//
//        linguisticChainService.saveChainIntoFile("/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/101.csv","/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/101annotations.txt","1");
//
//        System.out.println(apply);
//        System.out.println(jaro);
//        System.out.println(haming);
    }



}


