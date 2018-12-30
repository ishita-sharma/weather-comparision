package de.ishitasharma.wc.entity;


import java.util.List;

public class ComparisionResult {

    private static final String UNIT = "C";
    private double temp_diff;
    private float humidity_diff;
    private String city1;
    private String city2;
    private List<String> remarks;


    public ComparisionResult(double temp_diff, float humidity_diff, String city1, String city2) {
        this.temp_diff = temp_diff;
        this.humidity_diff = humidity_diff;
        this.city1 = city1;
        this.city2 = city2;
    }

    public static String getUNIT() {
        return UNIT;
    }

    public double getTemp_diff() {
        return temp_diff;
    }

    public float getHumidity_diff() {
        return humidity_diff;
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
