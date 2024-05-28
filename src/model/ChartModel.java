package model;

public class ChartModel {

    private String month;
    private double sales;

    public ChartModel(String month, double sales) {
        this.month = month;
        this.sales = sales;
    }

    public String getMonth() {
        return month;
    }

    public double getSales() {
        return sales;
    }

    @Override
    public String toString() {
        return month + " - " + sales;
    }
}
