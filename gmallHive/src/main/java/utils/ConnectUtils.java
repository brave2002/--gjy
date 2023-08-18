package utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectUtils {

    static Connection hiveconnection;
    static Connection mysqlconnection;
    /**
     * 获取hive的连接对象
     */
    static{
        //只执行一次  执行时机：在class类加载的时候

        try {
            Properties properties = new Properties();
            properties.load(ConnectUtils.class.getClassLoader().getResourceAsStream("application.properties"));
            //1.加载 驱动
            Class.forName("org.apache.hive.jdbc.HiveDriver");
            Class.forName("com.mysql.jdbc.Driver");

            //2.获取hive的连接对象
            try {
                  hiveconnection = DriverManager.getConnection(properties.getProperty("hive.url"),properties.getProperty("hive.username"),properties.getProperty("hive.password"));
                  mysqlconnection = DriverManager.getConnection(properties.getProperty("mysql.url"),properties.getProperty("mysql.username"),properties.getProperty("mysql.password"));

//                //本地模式，为了加快速度
//                  PreparedStatement ps = hiveconnection.prepareStatement("set hive.exec.mode.local.auto=true");
//                //提交
//                  ps.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//
//    /**
//     * 创建表
//     */
//    public static void mkTable(){
//        try {
//            PreparedStatement ps = hiveconnection.prepareStatement("create table test2(id int,name string)");
//            //一定记得提交
//            ps.executeUpdate();
//            //关流
//            ps.close();
//            hiveconnection.close();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }
//


    /**
     * 对外暴露一个获取hive连接对象的方法
     */
    public static Connection getHiveConnection(){
        return hiveconnection;
    }

    /**
     * 对外暴露一个获取mysql连接对象的方法
     */
    public static Connection getMysqlConnection(){
        return mysqlconnection;
    }

}

