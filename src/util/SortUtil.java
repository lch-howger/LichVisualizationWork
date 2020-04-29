package util;

import model.Score;

import java.util.Comparator;
import java.util.List;

public class SortUtil {

    public static void sort(List<Score> list) {
        list.sort(new Comparator<Score>() {
            @Override
            public int compare(Score score, Score s2) {
                double v = ParseUtil.parseDouble(score.getScore()) - ParseUtil.parseDouble(s2.getScore());
                return -(int) Math.ceil(v);
            }
        });
    }
}
