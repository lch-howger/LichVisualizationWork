package util;

import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

public class AlertUtil {

    /**
     * @param message
     */
    public static void alert(String message) {
        Alert dialog = new Alert(Alert.AlertType.INFORMATION);
        dialog.setTitle("Alert");
        dialog.setHeaderText("");
        Label label = new Label(message);
        label.setWrapText(true);
        dialog.getDialogPane().setContent(label);
        dialog.getDialogPane().setPadding(new Insets(20, 20, 20, 20));
        dialog.showAndWait();
    }
}
