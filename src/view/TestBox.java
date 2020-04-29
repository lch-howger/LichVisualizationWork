package view;

import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Score;
import util.ListUtil;
import util.ParseUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestBox {

    public void display() {

        VBox vBox = new VBox();

        ScrollPane s1 = new ScrollPane();
        s1.setPrefHeight(200);

        VBox vBox1 = new VBox();
        s1.setContent(vBox1);

        for (int i = 0; i < 10; i++) {
            Label label = new Label("fdjskfdjsk\naaaaaaaaaa\nbbbbbbbb");
            vBox1.getChildren().add(label);
        }

        vBox.getChildren().addAll(s1,new Label("fjdkfjdks"));

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(vBox, 950, 550));
        stage.showAndWait();
    }
}
