package view;

import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Score;
import util.ListUtil;
import util.ParseUtil;
import util.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ChartBoxPlaceAverage {

    private List<String> typeList;
    private List<String> placeList;
    private List<Score> scoreList;
    private List<Score> list = new ArrayList<>();
    private String type;

    public ChartBoxPlaceAverage(List<String> typeList, List<String> placeList, List<Score> scoreList, String type) {
        this.typeList = typeList;
        this.placeList = placeList;
        this.scoreList = scoreList;
        this.type = type;
        double totalScore = 0;
        for (int i = 0; i < scoreList.size(); i++) {
            Score s = scoreList.get(i);
            if (!s.getType().equals(type)) {
                continue;
            }
            totalScore += ParseUtil.parseDouble(s.getScore());

            if (i > 0 && (i + 1) % 17 == 0) {
                String score = StringUtil.toString(totalScore / 10);
                Score average = new Score("" + list.size(), s.getCode(), s.getType(), s.getPlace(), "average", score);
                list.add(average);
                totalScore = 0;
            }
        }
    }

    public void display() {
        VBox vBox = new VBox();

        CategoryAxis x = new CategoryAxis();
        NumberAxis y = new NumberAxis();
        XYChart<String, Number> chart = new BarChart<>(x, y);
        XYChart.Series series = new XYChart.Series();
        series.setName(type);

        for (Score s : list) {
            XYChart.Data data = new XYChart.Data(s.getPlace(), ParseUtil.parseDouble(s.getScore()));
            series.getData().add(data);
        }
        chart.getData().add(series);

        vBox.getChildren().add(chart);
        chart.setPrefWidth(900);
        chart.setPrefHeight(500);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(vBox, 950, 550));
        stage.showAndWait();
    }
}
