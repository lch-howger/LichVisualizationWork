package view;

import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Rate;
import util.TimeUtil;

import java.util.List;

public class RateBoxPlace {

    private List<Rate> rateList;
    private String place;

    public RateBoxPlace(List<String> typeList, List<String> placeList, List<Rate> rateList, String place) {
        this.rateList = rateList;
        this.place = place;
    }

    public void display() {
        VBox vBox = new VBox();

        CategoryAxis x = new CategoryAxis();
        NumberAxis y = new NumberAxis();
        XYChart<String, Number> chart = new AreaChart<>(x, y);

        for (Rate rate : rateList) {
            if (rate.getPlace().equals(place)) {
                XYChart.Series series = new XYChart.Series();
                series.setName(rate.getType());
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
