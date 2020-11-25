package ru.sstu.lab_7;

import java.util.function.DoubleUnaryOperator;

import static java.lang.Math.*;

public class Main {
    public static final double DELTA_X = 1.0;
    public static final double DX = 0.00000001;
    public static final double EPSILON = 0.001;

    public static void main(String[] args) {

        /* FIRST EQUATION */
        System.out.println("***First equation***");
        DoubleUnaryOperator func = x -> tan((0.4 * x) + 0.3) - (x * x);
        double[] criticalPoints = {};
        double[] domainOfFunction = {-5.0, 5.0};
        for (double v : new AnalyticalMethod(new Equation(func, criticalPoints, domainOfFunction)).solve()) {
            System.out.printf("X = %6.3f\n", v);
        }
        XYChart demo = new XYChart("First equation", -2.0, 2.0, 0.01, new String[]{"y = tg(0.5 * x + 0.2", "y = x^2"}, x -> tan((0.4 * x) + 0.3), x -> (x * x) );
        demo.pack();
        demo.setVisible(true);
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("********************************************************************************");
        System.out.println("--------------------------------------------------------------------------------\n\n");

        /* SECOND EQUATION */
        System.out.println("***Second equation***");
        func = x -> pow(x, 3) - 3 * pow(x, 2) + 6 * x - 2;
        criticalPoints = new double[]{};
        domainOfFunction = new double[]{-2.0, 2.0};
        for (double v : new AnalyticalMethod(new Equation(func, criticalPoints, domainOfFunction)).solve()) {
            System.out.printf("X = %6.3f\n", v);
        }
        demo = new XYChart("Second equation", -2.0, 2.0, 0.01, new String[]{"y = x^3 + 0.2 * x^2 + 0.5 * x - 2", "y = 0"}, func, x -> 0);
        demo.pack();
        demo.setVisible(true);
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("********************************************************************************");
        System.out.println("--------------------------------------------------------------------------------\n\n");
    }
}
