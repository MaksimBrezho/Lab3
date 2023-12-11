package org.brejo.jira.service.charts;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.brejo.jira.service.charts.abstractclass.Histogram;

import java.awt.*;
import java.util.Map;

public class HistogramCreatedAndClosedIssue extends Histogram {

    public HistogramCreatedAndClosedIssue(String title, Map<String, Integer> dataCreated,
                                          Map<String, Integer> dataClosed, Map<String, Integer> dataAccumulated) {
        super(title);

        // Создаем датасет с данными
        DefaultCategoryDataset dataset = createDataset(dataCreated, dataClosed, dataAccumulated);

        // Создаем гистограмму
        JFreeChart chart = createChart(dataset, title);

        // Создаем панель для графика
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 400));
        setContentPane(chartPanel);
    }

    private DefaultCategoryDataset createDataset(Map<String, Integer> dataCreated, Map<String, Integer> dataClosed,
                                                 Map<String, Integer> dataAccumulated) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Map.Entry<String, Integer> entry: dataCreated.entrySet()) {
            dataset.addValue(entry.getValue(), "Created", entry.getKey());
        }
        for (Map.Entry<String, Integer> entry: dataClosed.entrySet()) {
            dataset.addValue(entry.getValue(), "Closed", entry.getKey());
        }

        for (Map.Entry<String, Integer> entry: dataAccumulated.entrySet()) {
            dataset.addValue(entry.getValue(), "Accumulated", entry.getKey());
        }

        return dataset;
    }

    private JFreeChart createChart(DefaultCategoryDataset dataset, String title) {
        JFreeChart chart = ChartFactory.createBarChart(
                title,
                "Time",
                "Issue",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                true
        );

        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.white);
        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setAxisLinePaint(Color.darkGray);
        domainAxis.setTickMarkPaint(Color.darkGray);
        domainAxis.setTickLabelPaint(Color.darkGray);
        ValueAxis rangeAxis = plot.getRangeAxis();
        rangeAxis.setAxisLinePaint(Color.darkGray);
        rangeAxis.setTickMarkPaint(Color.darkGray);
        rangeAxis.setTickLabelPaint(Color.darkGray);

        return chart;
    }
}
