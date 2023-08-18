package need;

import utils.ConnectUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NeedDemo3 {
    static Connection hiveConnection = ConnectUtils.getHiveConnection();
    static Connection mysqlConnection = ConnectUtils.getMysqlConnection();
    public static void demo3(){
        try {
            //统计购买手机的用户占全部用户的比例
            PreparedStatement hiveps = hiveConnection.prepareStatement("select round(b.counts/a.sums,2) from(select count(distinct name) sums from t_mall) a ,(select count(distinct name) counts from t_mall where title like '%手机%')b");
            //需要返回值
            ResultSet resultSet = hiveps.executeQuery();
            //?叫做占位符
            PreparedStatement mysqlps = mysqlConnection.prepareStatement("insert into phone_par values (?)");
            while (resultSet.next()){
                //System.out.println(resultSet.getString(1)+"-->"+resultSet.getDouble(2));
                mysqlps.setFloat(1,resultSet.getFloat(1));
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
        demo3();
    }
}
