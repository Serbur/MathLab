package ru.sstu.lab_6;

import java.util.function.DoubleUnaryOperator;

public class BisectionMethod {
    private DoubleUnaryOperator func;
    private double[] segment;

    public BisectionMethod(DoubleUnaryOperator func, double[] segment) {
        this.func = func;
        this.segment = segment;
    }

    public double solve() {
        double left = segment[0];
        double right = segment[1];
        double delta = (right - left) / 2;
        double temp = left;
        while (delta > Main.EPSILON) {
            temp += delta;
            if (func.applyAsDouble(left) * func.applyAsDouble(temp) > 0) {
                left = temp;
            } else {
                temp = left;
            }
            delta/=2;
        }
        return temp;
    }
}