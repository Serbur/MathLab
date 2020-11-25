package ru.sstu.lab_7;

import java.util.function.DoubleUnaryOperator;

public class SecantMethod {
    private DoubleUnaryOperator func;
    private double[] segment;

    public SecantMethod(DoubleUnaryOperator func, double[] segment) {
        this.func = func;
        this.segment = segment;
    }

    public double solve() {
        double left = segment[0];
        double right = segment[1];
        double current = left;
        double previous = right;
        while (Math.abs(current - previous) > Main.EPSILON) {
            if (func.applyAsDouble(current) * func.applyAsDouble(left) > 0) {
                left = current;
            } else {
                right = current;
            }
            previous = current;
            current = right - func.applyAsDouble(right) / (func.applyAsDouble(right) - func.applyAsDouble(left)) * (right - left);
        }
        return current;
    }
}
