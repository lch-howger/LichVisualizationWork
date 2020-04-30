package factory;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import util.AlertUtil;

public class MenuFactory {

    /**
     * create menu
     *
     * @param s
     * @return
     */
    public static Menu createMenu(String s) {
        Menu menu = new Menu(s);
        if (s.equals("File")) {
            MenuItem report = createMenuItem("Exit");
            menu.getItems().addAll(report);
        } else if (s.equals("Edit")) {
            MenuItem add = createMenuItem("Add");
            MenuItem delete = createMenuItem("Delete");
            menu.getItems().addAll(add, delete);
        } else if (s.equals("Help")) {
            MenuItem about = createMenuItem("About");
            menu.getItems().addAll(about);
        }
        return menu;
    }

    /**
     * create menu item
     *
     * @param s
     * @return
     */
    public static MenuItem createMenuItem(String s) {
        MenuItem menuItem = new MenuItem(s);
        if (s.equals("Exit")) {

        } else if (s.equals("Add")) {
            menuItem.setOnAction(actionEvent -> {
                AlertUtil.alert("hello world");
            });
        } else if (s.equals("Delete")) {
            menuItem.setOnAction(actionEvent -> {
                AlertUtil.alert("hello world");
            });
        } else if (s.equals("Help")) {
            menuItem.setOnAction(actionEvent -> {
                AlertUtil.alert("help");
            });
        } else if (s.equals("About")) {
            menuItem.setOnAction(actionEvent -> {
                AlertUtil.alert("Thank you for using this program.\n" +
                        "The data of program are based on www.ons.gov.uk.");
            });
        }
        return menuItem;
    }

}
