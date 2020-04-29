package sample;

import constant.StringValue;
import factory.MenuFactory;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.Rate;
import model.Score;
import util.AlertUtil;
import util.FileUtil;
import view.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    private List<String> typeList;
    private List<String> placeList;
    private List<Score> scoreList;
    private List<Rate> rateList = new ArrayList<>();
    private List<Label> leftItems = new ArrayList<>();
    private List<VBox> rightItems = new ArrayList<>();
    private VBox right;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        initData();
        BorderPane layout = initLayout();

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(layout, 1000, 600));
        primaryStage.show();
    }

    private BorderPane initLayout() {
        BorderPane layout = new BorderPane();
        layout.setStyle("-fx-background-color: #eee");

        MenuBar menuBar = initMenu();
        layout.setTop(menuBar);

        HBox hBox = new HBox();
        VBox left = initLeft();
        right = initRight();
        resetLeftItem("0");

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(right);
        hBox.getChildren().addAll(left, scrollPane);
        layout.setCenter(hBox);

        return layout;
    }

    private MenuBar initMenu() {
        //create menus
        Menu file = MenuFactory.createMenu("File");
        Menu help = MenuFactory.createMenu("Help");

        //create menu bar
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(file, help);

        return menuBar;
    }

    private VBox initLeft() {
        VBox vBox = new VBox();
        Label item01 = initLeftItem("Crime Severity Score\n(Offence Group)");
        Label item02 = initLeftItem("Crime Severity Score (Regions)");
        Label item03 = initLeftItem("Offence Rate (Offence Group)");
        Label item04 = initLeftItem("Offence Rate (Regions)");

        vBox.getChildren().addAll(item01, item02, item03, item04);
        vBox.setPrefWidth(250);
        vBox.setStyle("-fx-background-color: #fff");
        return vBox;
    }

    private Label initLeftItem(String text) {
        Label label = new Label(text);
        label.setId("" + leftItems.size());
        label.setPrefWidth(250);
        label.setPrefHeight(80);
        label.setStyle("-fx-background-color: #fff");
        label.setPadding(new Insets(10, 10, 10, 10));
        label.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                resetLeftItem(label.getId());
            }
        });
        leftItems.add(label);
        return label;
    }

    private VBox initRight() {
        VBox vBox = new VBox();

        VBox item01 = initRightItem(0);
        VBox item02 = initRightItem(1);
        VBox item03 = initRightItem(2);
        VBox item04 = initRightItem(3);

        vBox.getChildren().addAll(item01);
        return vBox;
    }

    private VBox initRightItem(int id) {
        VBox vBox = new VBox();
        vBox.setPrefWidth(800);
        vBox.setId("" + rightItems.size());
        initRightView(vBox, id);
        rightItems.add(vBox);
        return vBox;
    }

    private void resetLeftItem(String id) {
        for (Label l : leftItems) {
            if (l.getId().equals(id)) {
                l.setStyle("-fx-background-color: #eee");
            } else {
                l.setStyle("-fx-background-color: #fff");
            }
        }

        right.getChildren().remove(0);
        for (VBox vBox : rightItems) {
            if (vBox.getId().equals(id)) {
                right.getChildren().add(vBox);
            }
        }
    }

    private void initData() {
        File file = new File("data/data_score.csv");
        ArrayList[] lists = FileUtil.initData(file);
        typeList = lists[0];
        placeList = lists[1];
        scoreList = lists[2];
    }

    private void initRightView(VBox vBox, int id) {
        if (id == 0) {
            Label label = new Label("View Chart of Offence Group");

            ChoiceBox<String> choiceBox = new ChoiceBox<>();
            for (String s : typeList) {
                choiceBox.getItems().add(s);
            }
            choiceBox.getSelectionModel().select(0);

            Button viewChart = new Button("View Chart");
            viewChart.setOnAction(actionEvent -> {
                String type = choiceBox.getValue();
                ChartBoxPlace chartBox = new ChartBoxPlace(typeList, placeList, scoreList, type);
                chartBox.display();
            });

            Label text = new Label(StringValue.data_analysis);
            text.setPadding(new Insets(30, 0, 0, 0));

            ChoiceBox<String> choiceBox2 = new ChoiceBox<>();
            for (String s : typeList) {
                choiceBox2.getItems().add(s);
            }
            choiceBox2.getSelectionModel().select(0);

            Button viewChart2 = new Button("View Annual Average Chart");
            viewChart2.setOnAction(actionEvent -> {
                String type = choiceBox2.getValue();
                ChartBoxPlaceAverage chartBox = new ChartBoxPlaceAverage(typeList, placeList, scoreList, type);
                chartBox.display();
            });

            Label text2 = new Label(StringValue.all_average);
            text2.setPadding(new Insets(30, 0, 0, 0));

            Button viewChart3 = new Button("View Annual Average Chart of All Groups");
            viewChart3.setOnAction(actionEvent -> {
                String type = choiceBox2.getValue();
                ChartBoxPlaceAverageAll chartBox = new ChartBoxPlaceAverageAll(typeList, placeList, scoreList, type);
                chartBox.display();
            });

            Label text3 = new Label(StringValue.total_group);
            text3.setPadding(new Insets(30, 0, 0, 0));

            Button viewChart4 = new Button("View Chart of Total Score");
            viewChart4.setOnAction(actionEvent -> {
                String type = choiceBox2.getValue();
                ChartBoxPlaceAverageTotal chartBox = new ChartBoxPlaceAverageTotal(typeList, placeList, scoreList, type);
                chartBox.display();
            });

            vBox.getChildren().addAll(label, choiceBox, viewChart, text, choiceBox2, viewChart2, text2, viewChart3, text3, viewChart4);
            vBox.setSpacing(10);
            vBox.setPadding(new Insets(20, 20, 60, 20));

        } else if (id == 1) {
            Label label = new Label("View Chart of Regions");

            ChoiceBox<String> choiceBox = new ChoiceBox<>();
            for (String s : placeList) {
                choiceBox.getItems().add(s);
            }
            choiceBox.getSelectionModel().select(0);

            Button viewChart = new Button("View Chart");
            viewChart.setOnAction(actionEvent -> {
                String place = choiceBox.getValue();
                ChartBoxType chartBox = new ChartBoxType(typeList, placeList, scoreList, place);
                chartBox.display();
            });

            Label text = new Label(StringValue.data_analysis2);
            text.setPadding(new Insets(30, 0, 0, 0));

            ChoiceBox<String> choiceBox2 = new ChoiceBox<>();
            for (String s : placeList) {
                choiceBox2.getItems().add(s);
            }
            choiceBox2.getSelectionModel().select(0);

            Button viewChart2 = new Button("View Annual Average Chart");
            viewChart2.setOnAction(actionEvent -> {
                String place = choiceBox2.getValue();
                ChartBoxTypeAverage chartBox = new ChartBoxTypeAverage(typeList, placeList, scoreList, place);
                chartBox.display();
            });

            Label text2 = new Label(StringValue.all_average);
            text2.setPadding(new Insets(30, 0, 0, 0));

            Button viewChart3 = new Button("View Annual Average Chart of All Groups");
            viewChart3.setOnAction(actionEvent -> {
                String type = choiceBox2.getValue();
                ChartBoxPlaceAverageAll chartBox = new ChartBoxPlaceAverageAll(typeList, placeList, scoreList, type);
                chartBox.display();
            });

            Label text3 = new Label(StringValue.total_group);
            text3.setPadding(new Insets(30, 0, 0, 0));

            Button viewChart4 = new Button("View Chart of Total Score");
            viewChart4.setOnAction(actionEvent -> {
                String type = choiceBox2.getValue();
                ChartBoxPlaceAverageTotal chartBox = new ChartBoxPlaceAverageTotal(typeList, placeList, scoreList, type);
                chartBox.display();
            });

            vBox.getChildren().addAll(label, choiceBox, viewChart, text, choiceBox2, viewChart2, text2, viewChart3, text3, viewChart4);
            vBox.setSpacing(10);
            vBox.setPadding(new Insets(20, 20, 60, 20));
        }
    }
}
