package ru.sstu.lab_7;

import java.util.*;
import java.util.function.DoubleUnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Double.*;

public class AnalyticalMethod implements Solver {
    private Equation equation;
    private Map<Double, Sign> valuesSigns = new LinkedHashMap<>();
    private double[][] segments;

    public AnalyticalMethod(Equation equation) {
        this.equation = equation;
    }

    @Override
    public double[] solve() {
        findSings();
        printSigns();
        findSegments();
        printSegments();
        double[] x = new double[segments.length];
        for (int i = 0; i < segments.length; i++) {
            x[i] = new SecantMethod(equation.getFunc(), segments[i]).solve();
        }
        return x;
    }

    private void findSings() {
        List<Double> list = Stream.concat(Arrays.stream(checkDomainOfFunction()).boxed(),
                Arrays.stream(equation.getDomainOfFunction()).boxed())
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        int i = 0;
        valuesSigns.put(list.get(i), findSign(list.get(i + 1), list.get(i)));

        for (i = 1; i < list.size() - 1; i++) {
            valuesSigns.put(list.get(i), findSign(list.get(i), equation.getFunc()));

        }
        valuesSigns.put(list.get(i), findSign(list.get(i - 1), list.get(i)));
    }

    private Sign findSign(double value, double border) {
        if (border == POSITIVE_INFINITY) {
            return findSign(value + Main.DELTA_X, equation.getDer());
        }
        if (border == NEGATIVE_INFINITY) {
            return findSign(value - Main.DELTA_X, equation.getDer()).reverse();
        }
        return findSign(border, equation.getFunc());
    }

    public void findSegments() {
        double[][] temp = new double[valuesSigns.size() - 1][2];
        int i = 0;
        List<Map.Entry<Double, Sign>> entry = new ArrayList<>(valuesSigns.entrySet());
        for (int j = 0; j < entry.size() - 1; j++) {
            if (entry.get(j).getValue() != entry.get(j + 1).getValue()) {
                temp[i++] = findSegment(entry.get(j).getKey(), entry.get(j + 1).getKey());
            }
        }
        segments = new double[i][2];
        for (int n = 0; n < segments.length; n++) {
            segments[n] = temp[n];
        }
    }

    private double[] findSegment(double left, double right) {
        double delta = Main.DELTA_X;
        double[] segment = new double[2];
        double temp;
        if (left == NEGATIVE_INFINITY) {
            temp = right - delta;
            while (temp > left) {
                if (findSign(temp, equation.getFunc()) != findSign(right, equation.getFunc())) {
                    segment[0] = temp;
                    segment[1] = right;
                    return segment;
                }
                right = temp;
                temp -= delta;
            }
        }
        temp = left;
        while (temp <= right) {
            temp += delta;
            if (findSign(temp, equation.getFunc()) != findSign(left, equation.getFunc())) {
                segment[0] = left;
                segment[1] = temp;
                return segment;
            }
            left = temp;
        }
        return null;
    }

    private double[] checkDomainOfFunction() {
        double leftBorder = Arrays.stream(equation.getDomainOfFunction())
                .min()
                .orElse(NEGATIVE_INFINITY);
        double rightBorder = Arrays.stream(equation.getDomainOfFunction())
                .max()
                .orElse(POSITIVE_INFINITY);
        return Arrays.stream(equation.getCriticalPoints())
                .filter(x -> x >= leftBorder && x <= rightBorder)
                .toArray();
    }

    private Sign findSign(double value, DoubleUnaryOperator func) {
        return isPositive(func.applyAsDouble(value)) ? Sign.PLUS : Sign.MINUS;
    }

    private boolean isPositive(double value) {
        return value >= 0;
    }

    private void printSigns() {
        valuesSigns.forEach((key, value) -> {
            System.out.printf("x = %10.4f,\t sign(f(x)) = ", key);
            System.out.println(value);
        });
        System.out.println("--------------------------------------------------");
    }

    private void printSegments() {
        for (double[] segment : segments) {
            System.out.printf("[%5.2f, %5.2f]\n", segment[0], segment[1]);
        }
        System.out.println("--------------------------------------------------");
    }
}
