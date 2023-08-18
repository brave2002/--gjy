package com.example.tmallecharts.entity;

/**
 * @author 周
 */
public class tables6 {
    private String product_cate;
    private int sums;

    public int getCate() {
        return cate;
    }

    public void setCate(int cate) {
        this.cate = cate;
    }

    private int cate;

    public String getProduct_cate() {
        return product_cate;
    }

    public void setProduct_cate(String product_cate) {
        this.product_cate = product_cate;
    }

    public int getSums() {
        return sums;
    }

    public void setSums(int sums) {
        this.sums = sums;
    }

    @Override
    public String toString() {
        return "tables6{" +
                "product_cate='" + product_cate + '\'' +
                ", sums=" + sums +
                '}';
    }
}
