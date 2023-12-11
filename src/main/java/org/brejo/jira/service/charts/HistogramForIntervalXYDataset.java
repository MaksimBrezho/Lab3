package org.brejo.jira.service.charts;

import org.brejo.jira.service.charts.abstractclass.Histogram;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import java.awt.*;
import java.util.Map;

public class HistogramForIntervalXYDataset extends Histogram {

    public HistogramForIntervalXYDataset(String title, Map<Integer, Integer> data) {
        super(title);

        // Создаем датасет с данными
        IntervalXYDataset dataset = createDataset(data);

        // Создаем гистограмму
        JFreeChart chart = createChart(dataset, title);

        // Создаем панель для графика
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 400));
        setContentPane(chartPanel);
    }

    private IntervalXYDataset createDataset(Map<Integer, Integer> data) {
        XYSeries series = new XYSeries("Issue");
        for (Map.Entry<Integer, Integer> entry: data.entrySet()) {
            series.add(entry.getKey(), entry.getValue());
        }

        return new XYSeriesCollection(series);
    }

    private JFreeChart createChart(IntervalXYDataset dataset, String title) {
        JFreeChart chart = ChartFactory.createXYBarChart(
                title,
                "Time in hours",
                false,
                "Issue",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                false,
                false
        );

        XYPlot plot = (XYPlot) chart.getPlot();
        XYBarRenderer renderer = (XYBarRenderer) plot.getRenderer();
        renderer.setMargin(0.1);
        Color myColor = new Color(255, 0, 128);
        plot.setBackgroundPaint(Color.white);
        plot.setDomainGridlinePaint(Color.lightGray);
        plot.setRangeGridlinePaint(Color.lightGray);
        renderer.setSeriesPaint(0, Color.blue);
        chart.setBackgroundPaint(Color.white);
        plot.setDomainPannable(true);
        plot.setRangePannable(true);
        plot.getDomainAxis().setLowerBound(0);

        return chart;
    }
}