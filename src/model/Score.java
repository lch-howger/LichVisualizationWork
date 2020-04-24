package model;

public class Score {
    private String id;
    private String code;
    private String type;
    private String place;
    private String time;
    private String score;

    public Score(String id, String code, String type, String place, String time, String score) {
        this.id = id;
        this.code = code;
        this.type = type;
        this.place = place;
        this.time = time;
        this.score = score;
    }

    public String getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getType() {
        return type;
    }

    public String getPlace() {
        return place;
    }

    public String getTime() {
        return time;
    }

    public String getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "Score{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", type='" + type + '\'' +
                ", place='" + place + '\'' +
                ", time='" + time + '\'' +
                ", score='" + score + '\'' +
                '}';
    }
}
