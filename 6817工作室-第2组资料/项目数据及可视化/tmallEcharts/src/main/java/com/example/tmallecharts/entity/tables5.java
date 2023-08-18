package com.example.tmallecharts.entity;

public class tables5 {

        private String  gender;
        private int counts;

    public String getGender() {
        return gender;
    }

    public void setGender(String  gender) {
        this.gender =gender;
    }

    public int getCounts() {
        return counts;
    }

    public void setCounts(int counts) {
        this.counts = counts;
    }

    @Override
    public String toString() {
        return "tables5{" +
                "gender=" + gender +
                ", counts=" + counts +
                '}';
    }
}
