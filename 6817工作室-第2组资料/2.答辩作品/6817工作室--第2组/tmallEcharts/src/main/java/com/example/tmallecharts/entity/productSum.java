package com.example.tmallecharts.entity;

public class productSum {

    private String user_name;
    private int counts;

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getCounts() {
        return counts;
    }

    public void setCounts(int counts) {
        this.counts = counts;
    }

    @Override
    public String toString() {
        return "productSum{" +
                "user_name='" + user_name + '\'' +
                ", count=" + counts +
                '}';
    }
}
