package need;

import utils.ConnectUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NeedDemo1 {
    static Connection hiveConnection = ConnectUtils.getHiveConnection();
    static Connection mysqlConnection = ConnectUtils.getMysqlConnection();
    public static void demo1(){
        try {
            //统计每个用户的消费总数，并找出排名前10的用户
            PreparedStatement hiveps = hiveConnection.prepareStatement("select name,sum(shopPrice)sums from t_mall group by name order by sums desc limit 10");
            //需要返回值
            ResultSet resultSet = hiveps.executeQuery();
            //?叫做占位符
            PreparedStatement mysqlps = mysqlConnection.prepareStatement("insert into user_consume values (?,?)");
            while (resultSet.next()){
                //System.out.println(resultSet.getString(1)+"-->"+resultSet.getDouble(2));
                mysqlps.setString(1,resultSet.getString(1));
                mysqlps.setDouble(2,resultSet.getDouble(2));
                //每一次都要提交
                mysqlps.executeUpdate();
            }

            //关流
            hiveps.close();
            hiveConnection.close();
            mysqlps.close();
            mysqlConnection.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void main(String[] args) {
        demo1();
    }
}
