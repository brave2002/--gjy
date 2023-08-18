package com.example.tmallecharts.entity;




/**
 * @author 周
 */
public class ValueName {
    private int value;
    private String name;

    public ValueName(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public ValueName() {
    }

    @Override
    public String toString() {
        return "ValueName{" +
                "value=" + value +
                ", name='" + name + '\'' +
                '}';
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
