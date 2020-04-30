package util;

import constant.StringValue;
import javafx.geometry.Insets;
import javafx.scene.control.Label;

public class LabelUtil {

    public static Label initLabel(String s) {
        javafx.scene.control.Label text = new javafx.scene.control.Label(s);
        text.setPadding(new Insets(30, 0, 0, 0));
        return text;
    }
}
