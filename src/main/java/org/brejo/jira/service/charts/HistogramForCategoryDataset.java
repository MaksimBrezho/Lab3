package org.brejo.jira.service.charts;

import org.brejo.jira.service.charts.abstractclass.Histogram;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import java.awt.*;
import java.util.Map;

public class HistogramForCategoryDataset extends Histogram {

    public HistogramForCategoryDataset(String title, Map<String, Integer> data) {
        super(title);

        // Создаем датасет с данными
        DefaultCategoryDataset dataset = createDataset(data);

        // Создаем гистограмму
        JFreeChart chart = createChart(dataset, title);

        // Создаем панель для графика
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 400));
        setContentPane(chartPanel);
    }

    private DefaultCategoryDataset createDataset(Map<String, Integer> data) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Map.Entry<String, Integer> entry: data.entrySet()) {
            dataset.addValue(entry.getValue(), entry.getKey(), entry.getKey());
        }

        return dataset;
    }

    private JFreeChart createChart(DefaultCategoryDataset dataset, String title) {
        JFreeChart chart = ChartFactory.createBarChart(
                title,
                "Users",
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
        domainAxis.setAxisLinePaint(Color.black);
        domainAxis.setTickMarkPaint(Color.black);
        domainAxis.setTickLabelPaint(Color.black);
        ValueAxis rangeAxis = plot.getRangeAxis();
        rangeAxis.setAxisLinePaint(Color.black);
        rangeAxis.setTickMarkPaint(Color.black);
        rangeAxis.setTickLabelPaint(Color.black);

        return chart;
    }

}