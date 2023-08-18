package need;

import utils.ConnectUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NeedDemo2 {
    static Connection hiveConnection = ConnectUtils.getHiveConnection();
    static Connection mysqlConnection = ConnectUtils.getMysqlConnection();
    public static void demo2(){
        try {
            //统计每个商品的购买次数，并找出热卖前5的商品
            PreparedStatement hiveps = hiveConnection.prepareStatement("select title,count(*)counts from t_mall group by title order by counts desc limit 5");
            //需要返回值
            ResultSet resultSet = hiveps.executeQuery();
            //?叫做占位符
            PreparedStatement mysqlps = mysqlConnection.prepareStatement("insert into product_count values (?,?)");
            while (resultSet.next()){
                //System.out.println(resultSet.getString(1)+"-->"+resultSet.getDouble(2));
                mysqlps.setString(1,resultSet.getString(1));
                mysqlps.setInt(2,resultSet.getInt(2));
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
        demo2();
    }
}
