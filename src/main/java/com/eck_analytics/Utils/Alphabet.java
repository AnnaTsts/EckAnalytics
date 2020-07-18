package com.eck_analytics.Utils;

import lombok.extern.slf4j.Slf4j;

/**
 * used for storing alphabets in the system and all alphabets can easy add here
 * */
@Slf4j
public enum Alphabet {
    CYRILLIC ("абвгдеёжзийклмнопрстуфхцчшщъыьэюя".toCharArray()),
    LATIN ("abcdefghijklmnopqrstuvwxyz".toCharArray()),
    TEST_ARRAY(generateCharArray(192,267));


    private final int numberOfLetters;
    private final char[] letters;

    Alphabet(char[] letters) {
        this.numberOfLetters = letters.length;
        this.letters = letters;
    }

    public int numberOfLetters() {
        return numberOfLetters;
    }

    public char[] getLetters(){
        return letters;
    }

    public static char[] generateCharArray(int start,int end){
        char[] result = new char[(end - start)+1];
        int k =0;
        for(int i=start;i<end;i++){
            k=k+1;
            result[k]=(char)i;
        }

        return result;
    }
}
