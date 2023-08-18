package com.example.tmallecharts.entity;

import java.util.List;

/**
 * @author 周
 */
public class productSumList {
    private List<String> names;

    private List<Integer> values;

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    public List<Integer> getValues() {
        return values;
    }

    public void setValues(List<Integer> values) {
        this.values = values;
    }

    @Override
    public String toString() {
        return "productSumList{" +
                "names=" + names +
                ", values=" + values +
                '}';
    }
}
