package ru.sstu.lab_7;

import java.util.function.DoubleUnaryOperator;

import static ru.sstu.lab_7.Main.*;

public class Equation {
    private DoubleUnaryOperator func;
    private double[] criticalPoints;
    private double[] domainOfFunction;
    private DoubleUnaryOperator der;

    private static DoubleUnaryOperator derive(DoubleUnaryOperator f) {
        return (x) -> (f.applyAsDouble(x + DX) - f.applyAsDouble(x)) / DX;
    }


    public Equation() {
    }

    public Equation(DoubleUnaryOperator func, double[] criticalPoints, double[] domainOfFunction) {
        this.func = func;
        this.criticalPoints = criticalPoints;
        this.domainOfFunction = domainOfFunction;
        der = derive(func);
    }

    public DoubleUnaryOperator getFunc() {
        return func;
    }


    public double[] getCriticalPoints() {
        return criticalPoints;
    }

    public double[] getDomainOfFunction() {
        return domainOfFunction;
    }

    public DoubleUnaryOperator getDer() {
        return der;
    }
}
