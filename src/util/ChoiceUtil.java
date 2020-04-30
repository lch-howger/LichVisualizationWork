package util;

import javafx.scene.control.ChoiceBox;

import java.util.List;

public class ChoiceUtil {

    public static ChoiceBox initChoice(List<String> placeList) {
        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        for (String s : placeList) {
            choiceBox.getItems().add(s);
        }
        choiceBox.getSelectionModel().select(0);
        return choiceBox;
    }
}
