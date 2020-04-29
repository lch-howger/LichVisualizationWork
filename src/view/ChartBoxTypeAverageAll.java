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
import util.SortUtil;
import util.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ChartBoxTypeAverageAll {

    private List<String> typeList;
    private List<Score> scoreList;
    private List<Score> list = new ArrayList<>();

    public ChartBoxTypeAverageAll(List<String> typeList, List<String> placeList, List<Score> scoreList, String type) {
        this.typeList = typeList;
        this.scoreList = scoreList;
        double totalScore = 0;
        for (int i = 0; i < scoreList.size(); i++) {
            Score s = scoreList.get(i);
            totalScore += ParseUtil.parseDouble(s.getScore());
            if (i > 0 && (i+1) % 17 == 0) {
                String score = StringUtil.toString(totalScore / 17d);
                Score average = new Score("" + list.size(), s.getCode(), s.getType(), s.getPlace(), "average", score);
                list.add(average);
                totalScore = 0;
            }
        }

        SortUtil.sort(list);
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
            XYChart.Data data = new XYChart.Data(s.getPlace(), ParseUtil.parseDouble(s.getScore()));
            if (series != null) {
                series.getData().add(data);
            }
        }

        vBox.getChildren().addAll(chart);
        chart.setPrefWidth(950);
        chart.setPrefHeight(500);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(vBox, 950, 550));
        stage.showAndWait();
    }
}
