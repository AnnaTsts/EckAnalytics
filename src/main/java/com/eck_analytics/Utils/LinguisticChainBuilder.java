package com.eck_analytics.Utils;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Setter
@Getter
public class LinguisticChainBuilder {
    public static final int MIN_CARDIO_VALUE = Constants.LinguisticConstant.MIN;
    public static final int MAX_CARDIO_VALUE = Constants.LinguisticConstant.MAX;


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
        if(value<MIN_CARDIO_VALUE||value>MAX_CARDIO_VALUE)
            return ' ';
        double delta = (double) (MAX_CARDIO_VALUE - MIN_CARDIO_VALUE) / alphabet.numberOfLetters();
        int numberOfLetter = (int) Math.floor((value- MIN_CARDIO_VALUE )/ delta);
        if(numberOfLetter==alphabet.numberOfLetters())
            numberOfLetter=numberOfLetter-1;
        return alphabet.getLetters()[numberOfLetter];
    }
}
