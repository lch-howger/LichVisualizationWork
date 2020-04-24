package view;

import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Score;
import util.ListUtil;
import util.ParseUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ChartBoxType {

    private List<String> typeList;
    private List<String> placeList;
    private List<Score> scoreList;
    private List<Score> list = new ArrayList<>();
    private String place;

    public ChartBoxType(List<String> typeList, List<String> placeList, List<Score> scoreList, String place) {
        this.typeList = typeList;
        this.placeList = placeList;
        this.scoreList = scoreList;
        this.place = place;
        for (Score s : scoreList) {
            if (s.getPlace().equals(place)) {
                list.add(s);
            }
        }
    }

    public void display() {
        VBox vBox = new VBox();

        CategoryAxis x = new CategoryAxis();
        NumberAxis y = new NumberAxis();
        XYChart<String, Number> chart = new BarChart<>(x, y);

        HashMap<String, XYChart.Series> map = new HashMap<>();
        for (String type : typeList) {
            XYChart.Series series = new XYChart.Series();
            series.setName(type);
            map.put(type, series);
            chart.getData().add(series);
        }

        for (Score s : list) {
            XYChart.Series series = map.get(s.getType());
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
