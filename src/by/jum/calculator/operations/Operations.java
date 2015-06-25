package by.jum.calculator.operations;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by Vlad on 10.06.2015.
 */
public class Operations {
    double multiplication(Double x, Double y) {
        return convert(x * y);
    }

    double div(Double x, Double y) {
        return convert(x / y);
    }

    double add(Double x, Double y) {
        return convert(x + y);
    }

    double diff(Double x, Double y) {
        return convert(x - y);
    }

    double sin(Double x) {
        return convert(Math.sin(x));
    }

    double cos(Double x) {
        return convert(Math.cos(x));
    }

    double tg(Double x) {
        return convert(Math.tan(x));
    }

    double ctg(Double x) {
        return convert((1 / Math.tan(x)));
    }

    double sinh(Double x) {
        return convert(Math.sinh(x));
    }

    double cosh(Double x) {
        return convert(Math.cosh(x));
    }

    double tgh(Double x) {
        return convert(Math.tanh(x));
    }

    double ctgh(Double x) {
        return convert(1 / Math.tanh(x));
    }

    double sqrt(Double x) {
        return convert(Math.sqrt(x));
    }

    double pow(Double x, Double y) {
        return convert(Math.pow(x, y));
    }

    double percent(Double x, Double y) {
        return convert(x * y / 100);
    }

    private double convert(double answer){
        return new BigDecimal(answer).setScale(3, RoundingMode.UP).doubleValue();
    }

}
