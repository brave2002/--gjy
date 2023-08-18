package mltable;

import utils.ConnectUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MkTableDemo {
   static Connection hiveConnection = ConnectUtils.getHiveConnection();
    /**
     * 把三天的json数据表融合到一起
     */
    public static void createTmpTable(){
        try {
            PreparedStatement ps = hiveConnection.prepareStatement("create external table tm_mall(line string) location '/hivedata/gmall'");
            ps.executeUpdate();
            ps.close();
            hiveConnection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static void createTable(){
        try {
            PreparedStatement ps = hiveConnection.prepareStatement("create table t_mall as (select json_tuple(line,'name','phoneNumber','productId','shopPrice','times','title')as(name,phoneNumber,procuctId,shopPrice,times,title)from tm_mall)");
            ps.executeUpdate();
            ps.close();
            hiveConnection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static void main(String[] args) {
        createTable();
    }
}
