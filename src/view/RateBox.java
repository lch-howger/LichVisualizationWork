package view;

import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Rate;
import model.Score;
import util.ListUtil;
import util.ParseUtil;
import util.TimeUtil;

import java.util.HashMap;
import java.util.List;

public class RateBox {

    private List<Rate> rateList;
    private String type;

    public RateBox(List<String> typeList, List<String> placeList, List<Rate> rateList, String type) {
        this.rateList = rateList;
        this.type = type;
    }

    public void display() {
        VBox vBox = new VBox();

        CategoryAxis x = new CategoryAxis();
        NumberAxis y = new NumberAxis();
        XYChart<String, Number> chart = new LineChart<>(x, y);

        for (Rate rate : rateList) {
            if (rate.getType().equals(type)) {
                XYChart.Series series = new XYChart.Series();
                series.setName(rate.getPlace());
                for (int i = 0; i < rate.getRateArray().length; i++) {
                    XYChart.Data data = new XYChart.Data(TimeUtil.getYear(i), rate.getRateArray()[i]);
                    series.getData().add(data);
                }
                chart.getData().add(series);
            }
        }

        vBox.getChildren().addAll(chart);
        chart.setPrefWidth(950);
        chart.setPrefHeight(550);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(vBox, 950, 550));
        stage.showAndWait();
    }
}
