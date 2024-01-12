package org.vaadin.example;

import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.*;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("About")
@Route(value = "about")
public class ParallelCoordinates extends VerticalLayout {

    public ParallelCoordinates() {
        add(getChart());
    }

    public static class ParallelAxes extends AbstractConfigurationObject {
        private Number lineWidth;
        private Number tickAmount;

        public Number getLineWidth() {
            return lineWidth;
        }

        public void setLineWidth(Number lineWidth) {
            this.lineWidth = lineWidth;
        }

        public Number getTickAmount() {
            return tickAmount;
        }

        public void setTickAmount(Number tickAmount) {
            this.tickAmount = tickAmount;
        }
    }

    public static class ParallelChartModel extends ChartModel {
        private boolean parallelCoordinates = true;
        private ParallelAxes parallelAxes = new ParallelAxes();

        public boolean getParallelCoordinates() {
            return parallelCoordinates;
        }

        public void setParallelCoordinates(boolean parallelCoordinates) {
            this.parallelCoordinates = parallelCoordinates;
        }

        public ParallelAxes getParallelAxes() {
            return parallelAxes;
        }

        public void setParallelAxes(ParallelAxes parallelAxes) {
            this.parallelAxes = parallelAxes;
        }
    }

    private Chart getChart() {

        Configuration configuration = new Configuration();
        ParallelChartModel chartModel = new ParallelChartModel();
        chartModel.setType(ChartType.LINE);
        chartModel.setParallelCoordinates(true);
        chartModel.getParallelAxes().setLineWidth(2);
        chartModel.getParallelAxes().setTickAmount(10);
        configuration.setChart(chartModel);

        Chart chart = new Chart();
        chart.setConfiguration(configuration);

        YAxis y1 = new YAxis();
        y1.setMin(0);
        y1.setMax(100);
        configuration.addyAxis(y1);
        YAxis y2 = new YAxis();
        y2.setMin(0);
        y2.setMax(100);
        configuration.addyAxis(y2);
        YAxis y3 = new YAxis();
        y3.setMin(0);
        y3.setMax(100);
        configuration.addyAxis(y3);

        XAxis xAxis = configuration.getxAxis();
        xAxis.setCategories("y1", "y2", "y3");

        configuration.addSeries(new DataSeries(new String[]{"y1", "y2", "y3"}, new Number[]{0, 50, 75}));
        configuration.addSeries(new DataSeries(new String[]{"y1", "y2", "y3"}, new Number[]{25, 10, 75}));
        configuration.addSeries(new DataSeries(new String[]{"y1", "y2", "y3"}, new Number[]{10, 40, 100}));

        return chart;
    }
}
