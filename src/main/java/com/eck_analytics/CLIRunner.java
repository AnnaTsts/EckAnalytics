package com.eck_analytics;

import com.eck_analytics.Model.Example;
import com.eck_analytics.Services.ExampleService;
import com.eck_analytics.Services.LinguisticChainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@Slf4j
@SpringBootApplication
public class CLIRunner implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(CLIRunner.class, args);
    }

    @PostConstruct
    void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    @Autowired
    ExampleService exampleService;

    @Autowired
    LinguisticChainService linguisticChainService;

    @Override
    public void run(String ... args) throws Exception {
        //exampleService.saveExamples(linguisticChainService.getExamplesFromFiles("/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/101.csv","/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/101annotations.txt"));
        //linguisticChainService.getExamplesFromFiles("/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/100.csv","/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/100annotations.txt");
        //Example e = new Example(10000,10000,10000,10000,10000,10000);
        //ExampleService.sa
        try {
            linguisticChainService.saveChainIntoFile("/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/107.csv","/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/107annotations.txt","/Users/annatsytsyluik/Documents/КПИ/Example_7");

        }
        catch (Exception e){}

    }
}
