package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Score;
import util.AlertUtil;
import util.ListUtil;
import util.ParseUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ChartBoxPlace {

    private List<String> typeList;
    private List<String> placeList;
    private List<Score> scoreList;
    private List<Score> list = new ArrayList<>();
    private String type;

    public ChartBoxPlace(List<String> typeList, List<String> placeList, List<Score> scoreList, String type) {
        this.typeList = typeList;
        this.placeList = placeList;
        this.scoreList = scoreList;
        this.type = type;
        for (Score s : scoreList) {
            if (s.getType().equals(type)) {
                list.add(s);
            }
        }
    }

    public void display() {
        VBox vBox = new VBox();

        CategoryAxis x = new CategoryAxis();
        NumberAxis y = new NumberAxis();
        XYChart<String, Number> chart = new LineChart<>(x, y);

        HashMap<String, XYChart.Series> map = new HashMap<>();
        for (String place : placeList) {
            if (ListUtil.containsPlace(place)) {
                XYChart.Series series = new XYChart.Series();
                series.setName(place);
                map.put(place, series);
                chart.getData().add(series);
            }
        }

        for (Score s : list) {
            XYChart.Series series = map.get(s.getPlace());
            XYChart.Data data = new XYChart.Data(s.getTime(), ParseUtil.parseDouble(s.getScore()));
            if (series != null) {
                series.getData().add(data);
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
