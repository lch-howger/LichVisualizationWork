package util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class StringUtil {

    public static boolean isEmpty(String s) {
        if (s == null || s.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public static String toString(int i) {
        BigDecimal bigDecimal = new BigDecimal(i);
        return bigDecimal.toString();
    }

    public static String toString(double d) {
        BigDecimal bigDecimal = new BigDecimal(d);
        bigDecimal = bigDecimal.setScale(2, RoundingMode.HALF_UP);
        return bigDecimal.toString();
    }
}
