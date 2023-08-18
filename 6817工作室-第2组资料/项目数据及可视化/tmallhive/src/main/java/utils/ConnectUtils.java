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
        try {
            Properties properties=new Properties();
            properties.load(ConnectUtils.class.getClassLoader().getResourceAsStream("application.properties"));
            //1.加载驱动
            Class.forName("org.apache.hive.jdbc.HiveDriver");
            //Class.forName("com.mysql.jdbc.Driver");
            //2.获取hive的连接对象
            try {
                hiveconnection=DriverManager.getConnection(properties.getProperty("hive.url"),properties.getProperty("hive.username"),properties.getProperty("hive.password"));
                mysqlconnection=DriverManager.getConnection(properties.getProperty("mysql.url"),properties.getProperty("mysql.username"),properties.getProperty("mysql.password"));
                //本地模式，提高速度
                 PreparedStatement ps=hiveconnection.prepareStatement("set hive.exec.mode.local.auto=true");

                // System.out.println(mysqlconnection);
                //提交
                // ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 对外 暴露一个 获取hive连接对象的方法
     */
    public static Connection getHiveConnection(){
        return hiveconnection;
    }


    /**
     * 对外 暴露一个 获取mysql连接对象的方法
     */

    public static Connection getMysqlConnection(){
        return mysqlconnection;
    }


    public static void main(String[] args) {

    }
}
