package view;

import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Score;
import util.ParseUtil;
import util.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class ChartBoxTypeAverage {

    private List<String> typeList;
    private List<String> placeList;
    private List<Score> scoreList;
    private List<Score> list = new ArrayList<>();
    private String place;

    public ChartBoxTypeAverage(List<String> typeList, List<String> placeList, List<Score> scoreList, String place) {
        this.typeList = typeList;
        this.placeList = placeList;
        this.scoreList = scoreList;
        this.place = place;
        double totalScore = 0;
        for (int i = 0; i < scoreList.size(); i++) {
            Score s = scoreList.get(i);
            if (!s.getPlace().equals(place)) {
                continue;
            }
            totalScore += ParseUtil.parseDouble(s.getScore());

            if (i > 0 && (i + 1) % 17 == 0) {
                String score = StringUtil.toString(totalScore / 17);
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
        series.setName(place);

        for (Score s : list) {
            XYChart.Data data = new XYChart.Data(s.getType(), ParseUtil.parseDouble(s.getScore()));
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
