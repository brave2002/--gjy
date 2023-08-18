package com.xq.tmall.entity;

import lombok.Data;

import java.util.List;

/**
 * 用户
 */
@Data
public class User {
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
    /**
     * 用户头像
     */
    private String user_profile_picture_src;
    /**
     * 删除标识(1删除 0未删除）
     */
    private Integer del_flag=0;
    private List<Review> reviewList;
    private List<ProductOrderItem> productOrderItemList;
    private List<ProductOrder> productOrderList;

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", user_name='" + user_name + '\'' +
                ", user_nickname='" + user_nickname + '\'' +
                ", user_password='" + user_password + '\'' +
                ", user_realname='" + user_realname + '\'' +
                ", user_gender=" + user_gender +
                ", user_birthday='" + user_birthday + '\'' +
                ", user_address=" + user_address +
                ", user_homeplace=" + user_homeplace +
                ", user_profile_picture_src='" + user_profile_picture_src + '\'' +
                ", del_flag=" + del_flag +
                ", reviewList=" + reviewList +
                ", productOrderItemList=" + productOrderItemList +
                ", productOrderList=" + productOrderList +
                '}';
    }
    public String toUserString(){
        return  "user_id=" + user_id +
                ", user_name='" + user_name + '\'' +
                ", user_nickname='" + user_nickname + '\'' +
                ", user_password='" + user_password + '\'' +
                ", user_realname='" + user_realname + '\'' +
                ", user_gender=" + user_gender +
                ", user_birthday='" + user_birthday + '\'' +
                ", user_address=" + user_address +
                ", user_homeplace=" + user_homeplace +
                ", user_profile_picture_src='" + user_profile_picture_src + '\'' +
                ", del_flag=" + del_flag +
                ", reviewList=" + reviewList +
                ", productOrderItemList=" + productOrderItemList +
                ", productOrderList=" + productOrderList
                ;
    }
}
