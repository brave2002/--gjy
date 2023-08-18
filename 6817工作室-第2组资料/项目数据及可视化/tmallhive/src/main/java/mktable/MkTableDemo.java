package mktable;

import utils.ConnectUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MkTableDemo {
    static Connection hiveConnection =ConnectUtils.getHiveConnection();
    /**
     * 把json数据表 融合在一起
     */

    public static void createTmpTable(){
        //创建临时表
        try {
            PreparedStatement ps=hiveConnection.prepareStatement("create external table tmp_tmall(line string)" +
                    "location '/hivedata/tmall'");
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void createTable(){

    }


    public static void main(String[] args){


        createTmpTable();

    }
}
