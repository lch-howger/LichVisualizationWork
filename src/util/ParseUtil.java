package util;

public class ParseUtil {

    public static int parseInt(String s) {
        try {
            int i = Integer.parseInt(s);
            return i;
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static double parseDouble(String s) {
        try {
            double d = Double.parseDouble(s);
            return d;
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
