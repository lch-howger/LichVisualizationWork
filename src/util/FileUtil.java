package util;

import model.Score;

import java.io.*;
import java.util.ArrayList;

public class FileUtil {

    public static ArrayList[] initData(File file) {
        ArrayList<String> typeList = new ArrayList<>();
        ArrayList<String> placeList = new ArrayList<>();
        ArrayList<Score> list = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            int count = 0;
            while ((line = reader.readLine()) != null) {
                String[] split = line.split(",");
                if (split.length == 22 && !split[2].equals(":")) {
                    String type = split[0].trim();
                    String code = split[1].trim();
                    String place = split[2].trim();
                    if (!ListUtil.containsPlace(place)) {
                        continue;
                    }
                    if (!code.startsWith("E12")&&!code.startsWith("W92")) {
                        continue;
                    }
                    if (!typeList.contains(type)) {
                        typeList.add(type);
                    }
                    if (!placeList.contains(place)) {
                        placeList.add(place);
                    }
                    for (int i = 3; i < split.length-2; i++) {
                        String time = getTime(i);
                        Score score = new Score(
                                "" + count,
                                code,
                                type,
                                place,
                                time,
                                split[i].trim()
                        );
                        list.add(score);
                        if (time.equals("2002") && type.contains("Total")) {
                            System.out.println(score.toString());
                        }
                        count++;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList[]{typeList, placeList, list};
    }

    private static String getTime(int i) {
        int year = 1999 + i;
        return "" + year;
    }
}
