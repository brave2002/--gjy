package need;

import utils.ConnectUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class needDmeo1 {
    static Connection hiveConnection = ConnectUtils.getHiveConnection();
    static Connection mysqlConnection =ConnectUtils.getMysqlConnection();
    //1.统计每人购买的商品总数，找出前五
    public static void demo1(){
        try {
            PreparedStatement hiveps=hiveConnection.prepareStatement("select name, count(product_name) counts\n" +
                    "from (select\n" +
                    "get_json_object(line,'$.user_name')as name,\n" +
                    "get_json_object(line,'$.product_name')as product_name\n" +
                    "from tmp_tmall)a \n" +
                    "group by name \n" +
                    "order by counts desc limit 10");
            ResultSet resultSet=hiveps.executeQuery();
            //?叫做占位符
            PreparedStatement mysqlps=mysqlConnection.prepareStatement("insert into product_sums values (?,?)");
            while(resultSet.next()){
                mysqlps.setString(1,resultSet.getString("name"));
                mysqlps.setInt(2,resultSet.getInt("counts"));
                //提交
                mysqlps.executeUpdate();
            }
            hiveps.close();
            hiveConnection.close();
            mysqlps.close();
            mysqlConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args){
        demo1();
    }

}

