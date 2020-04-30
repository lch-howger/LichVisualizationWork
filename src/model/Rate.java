package model;

import java.util.Arrays;

public class Rate {
    private String id;
    private String code;
    private String type;
    private String place;
    private double[] rateArray;
    private double totalRate;
    private double averageRate;

    public Rate(String id, String code, String type, String place, double[] rateArray, double totalRate, double averageRate) {
        this.id = id;
        this.code = code;
        this.type = type;
        this.place = place;
        this.rateArray = rateArray;
        this.totalRate = totalRate;
        this.averageRate = averageRate;
    }

    @Override
    public String toString() {
        return "Rate{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", type='" + type + '\'' +
                ", place='" + place + '\'' +
                ", rateArray=" + Arrays.toString(rateArray) +
                ", totalRate=" + totalRate +
                ", averageRate=" + averageRate +
                '}';
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

    public double[] getRateArray() {
        return rateArray;
    }

    public double getTotalRate() {
        return totalRate;
    }

    public double getAverageRate() {
        return averageRate;
    }
}
