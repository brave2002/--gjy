package need;

import utils.ConnectUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NeedDemo5 {
    static Connection hiveConnection = ConnectUtils.getHiveConnection();
    static Connection mysqlConnection = ConnectUtils.getMysqlConnection();
    public static void demo5(){
        try {
            //统计每个用户的消费总数，并找出排名前10的用户
            PreparedStatement hiveps = hiveConnection.prepareStatement("select * from \n" +
                    "(select a.timess,a.name,a.phoneNumber,a.counts,\n" +
                    "row_number()over(partition by a.timess order by a.counts desc) flag\n" +
                    "from\n" +
                    "(select split(times,\" \")[0] timess,name,phoneNumber,count(*)counts\n" +
                    "from t_mall group by split(times,\" \")[0],name,phoneNumber)a )b\n" +
                    "where b.flag<=3");
            //需要返回值
            ResultSet resultSet = hiveps.executeQuery();
            //?叫做占位符
            PreparedStatement mysqlps = mysqlConnection.prepareStatement("insert into buy_day values (?,?,?,?)");
            while (resultSet.next()){
                //System.out.println(resultSet.getString(1)+"-->"+resultSet.getDouble(2));
                mysqlps.setString(1,resultSet.getString(1));
                mysqlps.setString(2,resultSet.getString(2));
                mysqlps.setString(3,resultSet.getString(3));
                mysqlps.setInt(4,resultSet.getInt(4));

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
        demo5();
    }
}
