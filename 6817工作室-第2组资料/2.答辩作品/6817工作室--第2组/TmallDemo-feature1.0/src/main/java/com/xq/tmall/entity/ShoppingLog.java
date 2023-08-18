package com.xq.tmall.entity;

import java.util.List;

public class ShoppingLog {
    private Integer product_id;
    /**
     * 产品名称
     */
    private String product_name;
    /**
     * 产品标题
     */
    private String product_title;
    /**
     * 原价
     */
    private Double product_price;
    /**
     * 促销价
     */
    private Double product_sale_price;
    /**
     * 创建日期
     */
    private String product_create_date;
    /**
     * 类别id
     */
    private Category product_category;

    //销量数
    private Integer product_sale_count;
    //评价数
    private Integer product_review_count;
    public Object setProduct_sale_co;
    private Integer user_id;
    /**
     * 用户名
     */
    private String user_name;
    /**
     * 昵称
     */
    private String user_nickname;
    /**
     * 密码
     */
    private String user_password;
    /**
     * 姓名
     */
    private String user_realname;
    /**
     * 性别
     */
    private Byte user_gender;
    /**
     * 出生日期
     */
    private String user_birthday;
    /**
     * 所在地地址
     */
    private Address user_address;
    /**
     * 家乡
     */
    private Address user_homeplace;
    private String times;

    public ShoppingLog(Integer product_id, String product_name, String product_title, Double product_price, Double product_sale_price, String product_create_date, Category product_category, Integer product_sale_count, Integer product_review_count, Object setProduct_sale_co, Integer user_id, String user_name, String user_nickname, String user_password, String user_realname, Byte user_gender, String user_birthday, Address user_address, Address user_homeplace,String times) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_title = product_title;
        this.product_price = product_price;
        this.product_sale_price = product_sale_price;
        this.product_create_date = product_create_date;
        this.product_category = product_category;
        this.product_sale_count = product_sale_count;
        this.product_review_count = product_review_count;
        this.setProduct_sale_co = setProduct_sale_co;
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_nickname = user_nickname;
        this.user_password = user_password;
        this.user_realname = user_realname;
        this.user_gender = user_gender;
        this.user_birthday = user_birthday;
        this.user_address = user_address;
        this.user_homeplace = user_homeplace;
        this.times=times;
    }


    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_title() {
        return product_title;
    }

    public void setProduct_title(String product_title) {
        this.product_title = product_title;
    }

    public Double getProduct_price() {
        return product_price;
    }

    public void setProduct_price(Double product_price) {
        this.product_price = product_price;
    }

    public Double getProduct_sale_price() {
        return product_sale_price;
    }

    public void setProduct_sale_price(Double product_sale_price) {
        this.product_sale_price = product_sale_price;
    }

    public String getProduct_create_date() {
        return product_create_date;
    }

    public void setProduct_create_date(String product_create_date) {
        this.product_create_date = product_create_date;
    }

    public Category getProduct_category() {
        return product_category;
    }

    public void setProduct_category(Category product_category) {
        this.product_category = product_category;
    }

    public Integer getProduct_sale_count() {
        return product_sale_count;
    }

    public void setProduct_sale_count(Integer product_sale_count) {
        this.product_sale_count = product_sale_count;
    }

    public Integer getProduct_review_count() {
        return product_review_count;
    }

    public void setProduct_review_count(Integer product_review_count) {
        this.product_review_count = product_review_count;
    }

    public Object getSetProduct_sale_co() {
        return setProduct_sale_co;
    }

    public void setSetProduct_sale_co(Object setProduct_sale_co) {
        this.setProduct_sale_co = setProduct_sale_co;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_nickname() {
        return user_nickname;
    }

    public void setUser_nickname(String user_nickname) {
        this.user_nickname = user_nickname;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_realname() {
        return user_realname;
    }

    public void setUser_realname(String user_realname) {
        this.user_realname = user_realname;
    }

    public Byte getUser_gender() {
        return user_gender;
    }

    public void setUser_gender(Byte user_gender) {
        this.user_gender = user_gender;
    }

    public String getUser_birthday() {
        return user_birthday;
    }

    public void setUser_birthday(String user_birthday) {
        this.user_birthday = user_birthday;
    }

    public Address getUser_address() {
        return user_address;
    }

    public void setUser_address(Address user_address) {
        this.user_address = user_address;
    }

    public Address getUser_homeplace() {
        return user_homeplace;
    }

    public void setUser_homeplace(Address user_homeplace) {
        this.user_homeplace = user_homeplace;
    }

    public String getTimes() {
        return times;
    }

    @Override
    public String toString() {
        return "ShoppingLog{" +
                "product_id=" + product_id +
                ", product_name='" + product_name + '\'' +
                ", product_title='" + product_title + '\'' +
                ", product_price=" + product_price +
                ", product_sale_price=" + product_sale_price +
                ", product_create_date='" + product_create_date + '\'' +
                ", product_category=" + product_category +
                ", product_sale_count=" + product_sale_count +
                ", product_review_count=" + product_review_count +
                ", setProduct_sale_co=" + setProduct_sale_co +
                ", user_id=" + user_id +
                ", user_name='" + user_name + '\'' +
                ", user_nickname='" + user_nickname + '\'' +
                ", user_password='" + user_password + '\'' +
                ", user_realname='" + user_realname + '\'' +
                ", user_gender=" + user_gender +
                ", user_birthday='" + user_birthday + '\'' +
                ", user_address=" + user_address +
                ", user_homeplace=" + user_homeplace +
                ", times='" + times + '\'' +
                '}';
    }
}
