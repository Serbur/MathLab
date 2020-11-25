package ru.sstu.lab_7;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYPointerAnnotation;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleUnaryOperator;

public class XYChart extends ApplicationFrame {

    private static final long serialVersionUID = 3114198779331053753L;

    public XYChart(final String title, double min, double max, double delta, String[] names, DoubleUnaryOperator... functions) {
        super(title);
        final JFreeChart chart = createChart(createDataset(min, max, delta, names, functions));
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(1500, 700));
        setContentPane(chartPanel);
    }

    private XYSeriesCollection createDataset(double min, double max, double delta, String[] names, DoubleUnaryOperator... functions) {
        double minY = Double.POSITIVE_INFINITY;
        double maxY = Double.NEGATIVE_INFINITY;
        XYSeriesCollection collection = new XYSeriesCollection();
        for (int i = 0; i < functions.length; i++) {
            XYSeries s = new XYSeries(names[i]);
            for (double j = min; j <= max; j += delta) {
                s.add(j, functions[i].applyAsDouble(j));
            }
            minY = Math.min(s.getMinY(), minY);
            maxY = Math.max(s.getMaxY(), maxY);
            collection.addSeries(s);
        }
        XYSeries x = new XYSeries("");
        x.add(min, 0);
        x.add(max, 0);
        collection.addSeries(x);
        XYSeries y = new XYSeries(" ");
        y.add(0,minY);
        y.add(0,maxY);
        collection.addSeries(y);
        return collection;
    }

    private JFreeChart createChart(XYSeriesCollection dataset) {
        return ChartFactory.createXYLineChart("", "x", "y", dataset, PlotOrientation.VERTICAL, true, true, true);
    }
}