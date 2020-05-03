package com.eck_analytics.FileSystem;

import com.eck_analytics.Model.AnomalyType;
import com.eck_analytics.Model.Example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


public class SimpleFileReader extends java.io.FileReader {

    private static final Pattern CLEAR_PATTERN = Pattern.compile("[\\s]+");

    public SimpleFileReader(InputFile file) throws FileNotFoundException {
        super(file.getPathToFile());
    }

    /***
     *return not a lot of example that can be stored in program memory
     * @param file - only txt file
     * @return all examples from this file
     */
    public static List<Example> readFile(InputFile file) {
        List<Example> examples = new ArrayList<>();
        if (getFileExtension(file.getPathToFile()).equals("txt")) {
            try {
                BufferedReader fileStream = new BufferedReader(new SimpleFileReader(file));
                fileStream.readLine();
                String nextLineInFile = fileStream.readLine();

                try {
                    while (nextLineInFile != null) {
                        examples.add(constructExampleFromStringTxt(nextLineInFile));
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
        return examples;
    }


    /***
     * function for parsing string according to the pattern
     * time    id    typeOfAnomaly   sub     chan    num
     * @param line - one line from txt file
     * @return new example with values from txt file
     */
    private static Example constructExampleFromStringTxt(String line) {

        CLEAR_PATTERN.matcher(line).replaceAll(" ").trim();
        String[] informationAboutExample1 = line.split(" ");
        ArrayList<String> informationAboutExample = new ArrayList<>();
        for (String s : informationAboutExample1
        ) {
            if (!s.equals(""))
                informationAboutExample.add(s);
        }

        //time mm:ss.SSS
        String[] time = informationAboutExample.get(0).split(":");
        int minute = Integer.parseInt(time[0]);
        char[] seconds = time[1].toCharArray();
        String secondsStr = "";
        String millisecondsStr = "";
        secondsStr += seconds[0];
        secondsStr += seconds[1];
        millisecondsStr += seconds[3];
        millisecondsStr += seconds[4];
        millisecondsStr += seconds[5];
        String numStr;
        int second = Integer.parseInt(secondsStr);
        int millisecond = Integer.parseInt(millisecondsStr);
        int millisecondSum = minute * 60000 + second * 1000 + millisecond;

        // get all information from string in correctWay
        int previousId = Integer.parseInt(informationAboutExample.get(1));
        String typeOfAnomaly = informationAboutExample.get(2);
        int anomalyTime = AnomalyType.getTypeId(typeOfAnomaly);
        int sub = Integer.parseInt(informationAboutExample.get(3));
        int chan = Integer.parseInt(informationAboutExample.get(4));

        if (informationAboutExample.get(5).contains("\t")) {
            numStr = informationAboutExample.get(5).split("\t")[0];
        } else numStr = informationAboutExample.get(5);
        int num = Integer.parseInt(numStr);

        return new Example(millisecondSum, anomalyTime, sub, chan, num, previousId);
    }

    /***
     * function for parsing string according to the pattern
     * id   first_val=mlii second_val =v5
     * @param line -one line from csv file
     * @return new example with values from cvn file
     */
    public static Example constructExampleFromStringCsv(String line) {
        String[] informationAboutExample = line.split(",");
        int previousId = Integer.parseInt(informationAboutExample[0]);
        int mlii = Integer.parseInt(informationAboutExample[1]);
        int v5 = Integer.parseInt(informationAboutExample[2]);
        return new Example(previousId, mlii, v5);
    }


    public static String getFileExtension(String fullName) {
        String fileName = new File(fullName).getName();
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1);
    }
}