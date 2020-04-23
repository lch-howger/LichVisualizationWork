package sample;

import factory.MenuFactory;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import util.AlertUtil;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    private List<Label> leftItems = new ArrayList<>();
    private List<VBox> rightItems = new ArrayList<>();
    private VBox right;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
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
        hBox.getChildren().addAll(left, right);
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
        Label item01 = initLeftItem("Crime Severity Score");
        Label item02 = initLeftItem("Offence Rate");

        vBox.getChildren().addAll(item01, item02);
        vBox.setPrefWidth(200);
        vBox.setStyle("-fx-background-color: #fff");
        return vBox;
    }

    private Label initLeftItem(String text) {
        Label label = new Label(text);
        label.setId("" + leftItems.size());
        label.setPrefWidth(200);
        label.setPrefHeight(70);
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

        VBox item01 = initRightItem();
        VBox item02 = initRightItem();

        vBox.getChildren().addAll(item01);
        return vBox;
    }

    private VBox initRightItem() {
        VBox vBox = new VBox();
        vBox.setPrefWidth(800);
        vBox.setPrefHeight(600);
        vBox.setId("" + rightItems.size());
        vBox.getChildren().addAll(new Button("" + rightItems.size()));
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

}
