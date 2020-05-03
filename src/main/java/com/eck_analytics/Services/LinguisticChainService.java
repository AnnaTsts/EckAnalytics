package com.eck_analytics.Services;

import com.eck_analytics.FileSystem.FileType;
import com.eck_analytics.FileSystem.InputFile;
import com.eck_analytics.FileSystem.SimpleFileReader;
import com.eck_analytics.Model.Anomaly;
import com.eck_analytics.Model.Example;
import com.eck_analytics.Model.Result;
import com.eck_analytics.Utils.Alphabet;
import com.eck_analytics.Utils.LinguisticChainBuilder;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LinguisticChainService {

    public static final int CHAR_IN_ANOMALY = 1000;
    private ExampleService exampleService = new ExampleService();
    private ResultService resultService = new ResultService();
    private AnomalyService anomalyService = new AnomalyService();
    private boolean isAnomalyNow = false;
    private int typeOfAnomaly = 0;


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
        currExample.setResult(result);
        if (!(LinguisticChainBuilder.getLetter(currExample.getV5(), Alphabet.TEST_ARRAY) == ' ')) {
            currExample.setLetter(LinguisticChainBuilder.getLetter(currExample.getV5(), Alphabet.TEST_ARRAY));
            result.setResultString(result.getResultString() + currExample.getLetter());
            exampleService.saveExample(currExample);
            resultService.updateResult(result);
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


