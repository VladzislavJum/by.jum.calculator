package by.jum.calculator.operations;

/**
 * Created by Vlad on 10.06.2015.
 */
public class Operations {
    Double multiplication(Double x, Double y) {
        return x * y;
    }


    Double div(Double x, Double y) {
        return x / y;
    }

    Double add(Double x, Double y) {
        return x + y;
    }

    Double diff(Double x, Double y) {
        return x - y;
    }

    Double sin(Double x) {
        return Math.sin(x);
    }

    Double cos(Double x) {
        return Math.cos(x);
    }

    Double tg(Double x) {
        return Math.tan(x);
    }

    Double ctg(Double x) {
        return 1 / Math.tan(x);
    }

    Double sinh(Double x) {
        return Math.sinh(x);
    }

    Double cosh(Double x) {
        return Math.cosh(x);
    }

    Double tgh(Double x) {
        return Math.tanh(x);
    }

    Double ctgh(Double x) {
        return 1 / Math.tanh(x);
    }

    Double sqrt(Double x) {
        return Math.sqrt(x);
    }

    Double pow(Double x, Double y) {
        return Math.pow(x, y);
    }

}
