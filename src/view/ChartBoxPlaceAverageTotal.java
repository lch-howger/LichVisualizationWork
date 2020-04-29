package view;

import javafx.collections.transformation.SortedList;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Score;
import util.ListUtil;
import util.ParseUtil;
import util.SortUtil;
import util.StringUtil;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class ChartBoxPlaceAverageTotal {

    private List<String> typeList;
    private List<String> placeList;
    private List<Score> scoreList;
    private List<Score> list = new ArrayList<>();

    public ChartBoxPlaceAverageTotal(List<String> typeList, List<String> placeList, List<Score> scoreList, String type) {
        this.typeList = typeList;
        this.placeList = placeList;
        this.scoreList = scoreList;
        double totalScore = 0;
        ArrayList<Score> temp = new ArrayList<>();
        for (int i = 0; i < scoreList.size(); i++) {
            Score s = scoreList.get(i);
            totalScore += ParseUtil.parseDouble(s.getScore());
            if (i > 0 && (i+1) % 17 == 0) {
                String score = StringUtil.toString(totalScore / 17d);
                Score average = new Score("" + list.size(), s.getCode(), s.getType(), s.getPlace(), "average", score);
                temp.add(average);
                totalScore = 0;
            }
        }

        totalScore = 0;
        for (int i = 0; i < temp.size(); i++) {
            Score s = temp.get(i);
            totalScore += ParseUtil.parseDouble(s.getScore());
            if (i > 0 && (i + 1) % 10 == 0) {
                String score = StringUtil.toString(totalScore / 10d);
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

        XYChart.Series series = new XYChart.Series();
        for (Score s : list) {
            XYChart.Data data = new XYChart.Data(s.getType(), ParseUtil.parseDouble(s.getScore()));
            series.getData().add(data);
        }
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
