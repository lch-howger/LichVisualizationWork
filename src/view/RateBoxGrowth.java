package view;

import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Rate;
import util.TimeUtil;

import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class RateBoxGrowth {

    private List<Rate> rateList;
    private String place;

    public RateBoxGrowth(List<String> typeList, List<String> placeList, List<Rate> rateList, String place) {
        this.rateList = rateList;
        this.place = place;
    }

    public void display() {
        VBox vBox = new VBox();

        CategoryAxis x = new CategoryAxis();
        NumberAxis y = new NumberAxis();
        XYChart<String, Number> chart = new BarChart<>(x, y);

        XYChart.Series<String,Number> series = new XYChart.Series();
        series.setName(place);
        for (Rate rate : rateList) {
            if (rate.getPlace().equals(place)) {
                double[] rateArray = rate.getRateArray();
                double growth = (rateArray[16] - rateArray[0]) / 16d;
                XYChart.Data<String, Number> data = new XYChart.Data<>(rate.getType(),growth);
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
