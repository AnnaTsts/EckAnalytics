package com.eck_analytics.Algorithm;

import org.apache.commons.math3.analysis.interpolation.LinearInterpolator;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;

public class XСompression implements Сompression {
    @Override
    public char[] release(char[] source, double size) {
        PolynomialSplineFunction interpolate = getPolynomialFunction(source);
        int resultSize = (int) (source.length * size);
        char[] result = new char[resultSize];
        double j = 0;

        for (int i = 0; i < resultSize; i++) {
            result[i] = (char) interpolate.value(j);
            j += size;
        }

        return result;
    }

    private PolynomialSplineFunction getPolynomialFunction(char[] source) {
        LinearInterpolator linearInterpolator = new LinearInterpolator();
        double[] x = new double[source.length];
        double[] y = new double[source.length];
        for (int i = 0; i < source.length; i++) {
            double currValue = source[i];
            x[i] = i;
            y[i] = currValue;
        }
        return linearInterpolator.interpolate(x, y);
    }
}
