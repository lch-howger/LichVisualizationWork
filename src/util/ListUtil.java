package util;

import java.util.ArrayList;
import java.util.List;

public class ListUtil {

    public static boolean containsPlace(String s) {
        ArrayList<String> list = new ArrayList<>();
        //list.add("ENGLAND AND WALES");
        //list.add("ENGLAND");
        list.add("North East");
        list.add("North West");
        list.add("Yorkshire and The Humber");
        list.add("East Midlands");
        list.add("West Midlands");
        list.add("East");
        list.add("London");
        list.add("South East");
        list.add("South West");
        list.add("WALES");
        if (list.contains(s)) {
            return true;
        } else {
            return false;
        }
    }
}
