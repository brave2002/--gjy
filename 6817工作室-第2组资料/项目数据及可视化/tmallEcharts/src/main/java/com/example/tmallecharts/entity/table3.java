package com.example.tmallecharts.entity;

public class table3 {
    private String time;
    private double price_sums;
    private String gender;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getPrice_sums() {
        return price_sums;
    }

    public void setPrice_sums(double price_sums) {
        this.price_sums = price_sums;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "table3{" +
                "time='" + time + '\'' +
                ", price_sums=" + price_sums +
                ", gender='" + gender + '\'' +
                '}';
    }
}
