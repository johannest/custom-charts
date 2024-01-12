package org.vaadin.example;

import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.*;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("scatter")
public class Scatter extends VerticalLayout {

    public Scatter() {
        add(getChart());
    }

    public static class Jitter extends AbstractConfigurationObject {
        private Number x = 0;
        private Number y = 0;

        public Number getX() {
            return x;
        }

        public void setX(Number x) {
            this.x = x;
        }

        public Number getY() {
            return y;
        }

        public void setY(Number y) {
            this.y = y;
        }
    }

    public static class CustomScatterPlotOptions extends PlotOptionsScatter {
        private Jitter jitter = new Jitter();

        public Jitter getJitter() {
            return jitter;
        }

        public void setJitter(Jitter jitter) {
            this.jitter = jitter;
        }
    }

    private Chart getChart() {
        Chart chart = new Chart(ChartType.SCATTER);
        Configuration configuration = chart.getConfiguration();

        XAxis xAxis = configuration.getxAxis();
        xAxis.setTitle("Height (cm)");
        xAxis.setStartOnTick(true);
        xAxis.setEndOnTick(true);
        xAxis.setShowLastLabel(true);

        Legend legend = configuration.getLegend();
        legend.setLayout(LayoutDirection.VERTICAL);
        legend.setAlign(HorizontalAlign.LEFT);
        legend.setVerticalAlign(VerticalAlign.TOP);
        legend.setX(100);
        legend.setY(70);
        legend.setFloating(true);

        PlotOptionsScatter plotOptionsScatter = new PlotOptionsScatter();
        SeriesTooltip scatterTooltip = plotOptionsScatter.getTooltip();
        scatterTooltip.setHeaderFormat("<b>{series.name}</b><br>\",\"pointFormat\":\"{point.x} cm, {point.y} kg");

        DataSeries femaleSeries = new DataSeries();
        femaleSeries.setName("Female");

        CustomScatterPlotOptions femalePlotOptions = new CustomScatterPlotOptions();
        femalePlotOptions.getJitter().setX(1.0);
        femalePlotOptions.getJitter().setY(1.0);
        femalePlotOptions.setClassName("femaleSeries");
        femaleSeries.setPlotOptions(femalePlotOptions);

        Number[][] femaleData = getFemaleData();
        for (int i = 0; i < femaleData.length; i++) {
            DataSeriesItem item = new DataSeriesItem(femaleData[i][1], femaleData[i][0]);
            femaleSeries.add(item);
        }
        configuration.addSeries(femaleSeries);

        DataSeries maleSeries = new DataSeries();
        maleSeries.setName("Male");

        CustomScatterPlotOptions malePlotOptions = new CustomScatterPlotOptions();
        malePlotOptions.getJitter().setX(1.0);
        malePlotOptions.getJitter().setY(1.0);
        malePlotOptions.setClassName("maleSeries");
        maleSeries.setPlotOptions(malePlotOptions);

        Number[][] maleData = getMaleData();
        for (int i = 0; i < maleData.length; i++) {
            DataSeriesItem item = new DataSeriesItem(maleData[i][1], maleData[i][0]);
            maleSeries.add(item);
        }
        configuration.addSeries(maleSeries);
        return chart;
    }

    private Number[][] getFemaleData() {
        return new Number[][]{
                {160, 50}, {170, 60}, {160, 50}, {160, 60}, {160, 50},
                {170, 60}, {160, 50}, {170, 70}, {180, 70}, {160.2, 70},
        };
    }

    private Number[][] getMaleData() {
        return new Number[][]{
                {170, 70}, {180, 70}, {190, 80}, {190, 70}, {190, 80},
                {180, 70}, {180, 90}, {180, 80}, {180, 60}, {180, 80}
        };
    }

}
