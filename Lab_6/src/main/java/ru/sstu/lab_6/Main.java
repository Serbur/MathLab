package ru.sstu.lab_6;

import java.util.function.DoubleFunction;
import java.util.function.DoubleUnaryOperator;

import static java.lang.Double.NEGATIVE_INFINITY;
import static java.lang.Double.POSITIVE_INFINITY;
import static java.lang.Math.*;

public class Main {
    public static final double DELTA_X = 1.0;
    public static final double DX = 0.00000001;
    public static final double EPSILON = 0.01;

    public static void main(String[] args) {

        /* FIRST EQUATION */
        System.out.println("***First equation***");
        DoubleUnaryOperator func = x -> atan(x - 1) + 2 * x;
        double[] criticalPoints = {};
        double[] domainOfFunction = {-10, 10};
        for (double v : new AnalyticalMethod(new Equation(func, criticalPoints, domainOfFunction)).solve()) {
            System.out.printf("X = %6.3f\n", v);
        }
        XYChart demo = new XYChart("First equation", -2.0, 2.0, 0.01, new String[]{"y = arctg(x - 1) + 2 * x", "y = 0"}, func, x -> 0);
        demo.pack();
        demo.setVisible(true);
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("********************************************************************************");
        System.out.println("--------------------------------------------------------------------------------\n\n");

        /* SECOND EQUATION */
        System.out.println("***Second equation***");
        func = x -> 3 * Math.pow(x, 4) + 4 * pow(x, 3) - 12 * pow(x, 2) + 1;
        criticalPoints = new double[]{0.0, 1.0, -2.0};
        domainOfFunction = new double[]{NEGATIVE_INFINITY, POSITIVE_INFINITY};
        for (double v : new AnalyticalMethod(new Equation(func, criticalPoints, domainOfFunction)).solve()) {
            System.out.printf("X = %6.3f\n", v);
        }
        demo = new XYChart("Second equation", -3.0, 1.5, 0.01, new String[]{"y = 3 * x^4 - 4 * x^3 - 12 * x^2 + 1", "y = 0"}, func, x -> 0);
        demo.pack();
        demo.setVisible(true);
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("********************************************************************************");
        System.out.println("--------------------------------------------------------------------------------\n\n");

        /* THIRD EQUATION */
        System.out.println("***Third equation***");
        func = x -> pow(x - 2, 2) * pow(2, x) - 1;
        criticalPoints = new double[]{2.0, -0.855};
        domainOfFunction = new double[]{NEGATIVE_INFINITY, POSITIVE_INFINITY};
        for (double v : new AnalyticalMethod(new Equation(func, criticalPoints, domainOfFunction)).solve()) {
            System.out.printf("X = %6.3f\n", v);
        }
        demo = new XYChart("Third equation", -7.0, 3.0, 0.01, new String[]{"y = (x - 2)^2 * 2^x", "y = 1"}, x -> pow(x - 2, 2) * pow(2, x), x -> 1);
        demo.pack();
        demo.setVisible(true);
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("********************************************************************************");
        System.out.println("--------------------------------------------------------------------------------\n\n");

        /* FOURTH EQUATION */
        System.out.println("***Fourth equation***");
        func = x -> x * x * 20.0 * sin(x);
        criticalPoints = new double[]{-2.299, -8.096, 2.289, 0.0, 5.087, -5.087, 8.096};
        domainOfFunction = new double[]{-10.0, 10.0};
        for (double v : new AnalyticalMethod(new Equation(func, criticalPoints, domainOfFunction)).solve()) {
            System.out.printf("X = %6.3f\n", v);
        }
        demo = new XYChart("Fourth equation", -10.0, 10.0, 0.01, new String[]{"y = 20 * x^2 * sin(x)", "y = 0"}, x -> x * x * 20 * sin(x), x -> 0.0);
        demo.pack();
        demo.setVisible(true);
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("********************************************************************************");
        System.out.println("--------------------------------------------------------------------------------\n\n");
    }
}