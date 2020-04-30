package view;

import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Rate;

import java.util.Comparator;
import java.util.List;

public class RateBoxGrowthPlace {

    private List<Rate> rateList;
    private String type;

    public RateBoxGrowthPlace(List<String> typeList, List<String> placeList, List<Rate> rateList, String type) {
        this.rateList = rateList;
        this.type = type;
    }

    public void display() {
        VBox vBox = new VBox();

        CategoryAxis x = new CategoryAxis();
        NumberAxis y = new NumberAxis();
        XYChart<String, Number> chart = new BarChart<>(x, y);

        XYChart.Series<String,Number> series = new XYChart.Series();
        series.setName(type);
        for (Rate rate : rateList) {
            if (rate.getType().equals(type)) {
                double[] rateArray = rate.getRateArray();
                double growth = (rateArray[16] - rateArray[0]) / 16d;
                XYChart.Data<String, Number> data = new XYChart.Data<>(rate.getPlace(),growth);
                series.getData().add(data);
            }
        }
        series.getData().sort(new Comparator<XYChart.Data<String, Number>>() {
            @Override
            public int compare(XYChart.Data<String, Number> d1, XYChart.Data<String, Number> d2) {
                double v1 = d1.getYValue().doubleValue();
                double v2 = d2.getYValue().doubleValue();
                return (int) ((v2 - v1)*100d);
            }
        });
        chart.getData().add(series);

        vBox.getChildren().addAll(chart);
        chart.setPrefWidth(950);
        chart.setPrefHeight(550);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(vBox, 950, 550));
        stage.showAndWait();
    }
}
