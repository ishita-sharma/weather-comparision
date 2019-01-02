package de.ishitasharma.wc.entity;


import java.util.List;

public class ComparisionResult {

    private static final String UNIT = "C";
    private double tempDiff;
    private float humidityDiff;
    private String city1;
    private String city2;
    private List<String> remarks;


    public ComparisionResult(double tempDiff, float humidityDiff, String city1, String city2) {
        this.tempDiff = tempDiff;
        this.humidityDiff = humidityDiff;
        this.city1 = city1;
        this.city2 = city2;
    }

    public static String getUNIT() {
        return UNIT;
    }

    public double getTempDiff() {
        return tempDiff;
    }

    public float getHumidityDiff() {
        return humidityDiff;
    }

    public String getCity1() {
        return city1;
    }

    public String getCity2() {
        return city2;
    }

    public void setRemarks(List<String> remarks) {
        this.remarks = remarks;
    }

    public List<String> getRemarks() {
        return remarks;
    }
}
