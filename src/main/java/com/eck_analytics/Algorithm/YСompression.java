package com.eck_analytics.Algorithm;

public class YСompression implements Сompression {
    @Override
    public char[] release(char[] source, double size) {

        char[] result = new char[source.length];
        for (int i = 0; i < source.length; i++) {
            result[i] = (char) (source[i] * size);
        }
        return result;
    }
}
