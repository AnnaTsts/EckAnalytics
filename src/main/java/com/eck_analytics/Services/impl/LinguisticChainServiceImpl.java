package com.eck_analytics.Services.impl;

import com.eck_analytics.FileSystem.FileType;
import com.eck_analytics.FileSystem.InputFile;
import com.eck_analytics.FileSystem.SimpleFileReader;
import com.eck_analytics.Model.Anomaly;
import com.eck_analytics.Model.Example;
import com.eck_analytics.Model.Result;
import com.eck_analytics.Services.AnomalyService;
import com.eck_analytics.Services.ExampleService;
import com.eck_analytics.Services.LinguisticChainService;
import com.eck_analytics.Services.ResultService;
import com.eck_analytics.Utils.Alphabet;
import com.eck_analytics.Utils.LinguisticChainBuilder;
import org.apache.commons.csv.writer.CSVWriter;
import org.junit.jupiter.params.shadow.com.univocity.parsers.csv.Csv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class LinguisticChainServiceImpl implements LinguisticChainService {
    public static final int CHAR_IN_ANOMALY = 1000;

    private ExampleService exampleService;
    private ResultService resultService;
    private AnomalyService anomalyService;
    private boolean isAnomalyNow;
    private int typeOfAnomaly;

    @Autowired
    public LinguisticChainServiceImpl(ExampleService exampleService, ResultService resultService, AnomalyService anomalyService) {
        this.exampleService = exampleService;
        this.resultService = resultService;
        this.anomalyService = anomalyService;

        this.isAnomalyNow = false;
        this.typeOfAnomaly = 0;
    }

    @Override
    public void getExamplesFromFiles(String fileCSVPath, String fileTxtPath) {

        InputFile inputCsv = SimpleFileReader.getFileExtension(fileCSVPath).equals("csv") ? new InputFile(FileType.EXAMPLE_CSV, fileCSVPath) : null;
        InputFile inputTxt = SimpleFileReader.getFileExtension(fileTxtPath).equals("txt") ? new InputFile(FileType.STATISTIC_TXT, fileTxtPath) : null;
        List<Example> examplesTxt = SimpleFileReader.readFile(inputTxt);
        //save here last 10 examples and when we find anomaly save 10 after anomaly and save them into bd
        List<Example> examplesForAnomaly = new ArrayList<>();


        Result result = new Result("", 0);
        int resultId = resultService.saveResult(result);
        result.setId(resultId);

        try {
            BufferedReader fileStream = new BufferedReader(new SimpleFileReader(inputCsv));
            fileStream.readLine();
            String nextLineInFile = fileStream.readLine();

            try {
                while (nextLineInFile != null) {
                    Example currExample = saveActionForExample(nextLineInFile, result, examplesTxt);
                    if (currExample != null)
                        checkAnomaly(currExample, examplesForAnomaly, result);
                    nextLineInFile = fileStream.readLine();
                }
                fileStream.close();

            } catch (FileNotFoundException e) {
                System.out.println("No file was read");
            } catch (IOException e) {
                System.out.println("There was a problem reading the file");
            }
        } catch (
                IOException ex) {
            ex.printStackTrace();
        }
    }

    public void saveChainIntoFile(String fileCSVPath, String fileTxtPath,String fileName) throws IOException {
        InputFile inputCsv = SimpleFileReader.getFileExtension(fileCSVPath).equals("csv") ? new InputFile(FileType.EXAMPLE_CSV, fileCSVPath) : null;
        InputFile inputTxt = SimpleFileReader.getFileExtension(fileTxtPath).equals("txt") ? new InputFile(FileType.STATISTIC_TXT, fileTxtPath) : null;
        List<Example> examplesTxt = SimpleFileReader.readFile(inputTxt);
        //save here last 10 examples and when we find anomaly save 10 after anomaly and save them into bd
        List<Example> examplesForAnomaly = new ArrayList<>();


        Result result = new Result("", 0);


        String csv = "Example_1.csv";
        FileWriter writer = new FileWriter(csv);
        File csvOutputFile = new File(fileName+".csv");
        List<String> dataLines = new ArrayList<>();
        PrintWriter pw = new PrintWriter(csvOutputFile);

        try {
            BufferedReader fileStream = new BufferedReader(new SimpleFileReader(inputCsv));
            fileStream.readLine();
            String nextLineInFile = fileStream.readLine();

            try {
                while (nextLineInFile != null) {
                    System.out.println(nextLineInFile);
                    Example currExample = saveActionForExample(nextLineInFile, result, examplesTxt);
                    if (currExample != null)
                        {   String record = "";
                            record=record+(String.valueOf(currExample.getV5())+",");
                            record= record+currExample.getLetter();
                            record=record+(",");
                            if(currExample.getType()>2){
                                record=record+("+,");
                            }
                            else record=record+("-,");
                            record=record+(String.valueOf(currExample.getType())+"");
                            pw.println(record);
                            writer.write(record);
                        }

                    nextLineInFile = fileStream.readLine();
                }
                writer.close();
                fileStream.close();

            } catch (FileNotFoundException e) {
                System.out.println("No file was read");
            } catch (IOException e) {
                System.out.println("!!!!");
                System.out.println("There was a problem reading the file");
                System.out.println("exaption"+e.getMessage());
            }
        } catch (
                IOException ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
        System.out.println("Operation is done");



    }

    /***
     * provide searching in list from txt file example with similar id
     * also set all params from this txt file
     * @param examples - list of all examples from txt file
     * @param example - current example
     */
    private void getSimilarExampleByPrevId(List<Example> examples, Example example) {
        Example exampleFromTxt = null;
        //System.out.println(examples.size());
        for (Example e : examples
        ) {
            if (e.getPrevious_id() == example.getPrevious_id())
                exampleFromTxt = e;
        }


        if (exampleFromTxt != null) {
            example.setChan(exampleFromTxt.getChan());
            example.setNum(exampleFromTxt.getNum());
            example.setSub(exampleFromTxt.getSub());
            example.setTimeOfExample(exampleFromTxt.getTimeOfExample());
            example.setType(exampleFromTxt.getType());
        } else {
            example.setChan(0);
            example.setNum(0);
            example.setSub(0);
            example.setTimeOfExample(0);
            example.setType(0);
        }
    }

    private Example saveActionForExample(String nextLineInFile, Result result, List<Example> examplesTxt) {
        Example currExample = SimpleFileReader.constructExampleFromStringCsv(nextLineInFile);
        getSimilarExampleByPrevId(examplesTxt, currExample);
        //currExample.setResult(result);
        System.out.println(currExample.getV5());
        if (LinguisticChainBuilder.getLetter(currExample.getV5(), Alphabet.TEST_ARRAY) != ' ') {
            currExample.setLetter(LinguisticChainBuilder.getLetter(currExample.getV5(), Alphabet.TEST_ARRAY));
            //result.setResultString(result.getResultString() + currExample.getLetter());
            //exampleService.saveExample(currExample);
            //resultService.updateResult(result);
        } else return null;
        return currExample;
    }

    private void checkAnomaly(Example currExample, List<Example> examplesForAnomaly, Result result) {
        //TODO Move examplesForAnomaly.add(currExample); here as it is present in every branch of this method
        if (currExample.getType() > 2) {

            isAnomalyNow = true;
            typeOfAnomaly = currExample.getType();
            examplesForAnomaly.add(currExample);
            result.setAnomaly(result.getAnomaly() + 1);
            resultService.updateResult(result);
        } else {
            if (!isAnomalyNow) {
                if (examplesForAnomaly.size() >= CHAR_IN_ANOMALY / 2) {
                    examplesForAnomaly.remove(0);
                    examplesForAnomaly.add(currExample);
                } else examplesForAnomaly.add(currExample);
            } else {
                if (examplesForAnomaly.size() >= CHAR_IN_ANOMALY - 1) {
                    examplesForAnomaly.add(currExample);
                    StringBuilder anomaly = new StringBuilder();
                    System.out.println();
                    for (Example e : examplesForAnomaly) {

                        anomaly.append(e.getLetter());
                        if (e.getType() > 2)
                            System.out.println("!!!!");
                        System.out.println(e.getV5() + "  ");// + e.getLetter());//+" "+
                        // e.getLetter());
                        // System.out.println( e.getLetter());
                    }

                    anomalyService.saveAnomaly(new Anomaly(anomaly.toString(), typeOfAnomaly));
                    isAnomalyNow = false;
                    typeOfAnomaly = 0;
                } else examplesForAnomaly.add(currExample);
            }
        }
    }


}


