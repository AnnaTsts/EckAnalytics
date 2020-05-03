package com.eck_analytics.Utils;

/**
 * used for storing alphabets in the system and all alphabets can easy add here
 * */
public enum Alphabet {
    CYRILLIC ("абвгдеёжзийклмнопрстуфхцчшщъыьэюя".toCharArray()),
    LATIN ("abcdefghijklmnopqrstuvwxyz".toCharArray()),
    TEST_ARRAY(generateCharArray(192,292));


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
        char[] result = new char[end - start];
        int k =0;
        System.out.println("Alfabet");
        for(int i=start;i<end;i++){
            //System.out.println((char)i);
            k=k+1;
        }
        int z = 0;
        for(int i=end;i>start;i--){
            if(z<75)
            System.out.println((char)i);
           z=z+1;
        }

        System.out.println("Alfabet");
        return result;
    }
}
