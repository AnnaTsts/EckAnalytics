package com.eck_analytics.Utils;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Setter
@Getter
public class LinguisticChainBuilder {
    //TODO Move constants to one Constants file
    public static final int MIN_CARDIO_VALUE = 800;
    public static final int MAX_CARDIO_VALUE = 1200;


    /**
     * @param  currentList - list that should be transformed
     * @param  alphabet    - alphabet that used to transformation
     * @return - letters that match first list
     * */
    public static StringBuilder getLinguisticChain(ArrayList<Double> currentList, Alphabet alphabet) {
        double delta = (MAX_CARDIO_VALUE - MIN_CARDIO_VALUE) / (double)alphabet.numberOfLetters();
        StringBuilder linguisticChain = new StringBuilder();
        for (int i = 0; i < currentList.size(); i++) {
            int numberOfLetter = (int) Math.floor((currentList.get(i)- MIN_CARDIO_VALUE) / delta);
            linguisticChain.append(alphabet.getLetters()[numberOfLetter]);
            System.out.print((char)(numberOfLetter+192));
        }
        System.out.println();
        return linguisticChain;
    }

    public static char getLetter(int value, Alphabet alphabet ){
        //alphabet.getLetters()
//        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//        for (char c :alphabet.getLetters()
//             ) {
//            System.out.println(c+" ");
//        }
        //IntStream.range(192,4444).forEach(System.out::println);
//        System.out.println(IntStream.range(192,4444).mapToObj(x-> Stream.of((char) x)).toString());//.forEach(System.out::println);

        if(value<MIN_CARDIO_VALUE||value>MAX_CARDIO_VALUE-1)
            return ' ';
        double delta = (double) (MAX_CARDIO_VALUE - MIN_CARDIO_VALUE) / alphabet.numberOfLetters();
        int numberOfLetter = (int) Math.floor((value- MIN_CARDIO_VALUE )/ delta);
        return alphabet.getLetters()[numberOfLetter];
    }
}
