package need;

import utils.ConnectUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class needDmeo2 {
    static Connection hiveConnection = ConnectUtils.getHiveConnection();
    static Connection mysqlConnection =ConnectUtils.getMysqlConnection();
    //
    //2.统计每个商品的购买次数，并找出热卖前5的商品
    public static void demo1(){
        try {
            PreparedStatement hiveps=hiveConnection.prepareStatement("select product_name, count(product_name)counts\n" +
                    "from (select\n" +
                    "get_json_object(line,'$.product_name')as product_name\n" +
                    "from tmp_tmall)a\n" +
                    "group by product_name \n" +
                    "order by counts desc limit 5");
            ResultSet resultSet=hiveps.executeQuery();
            //?叫做占位符
            PreparedStatement mysqlps=mysqlConnection.prepareStatement("insert into table2 values (?,?)");
            while(resultSet.next()){
                mysqlps.setString(1,resultSet.getString("product_name"));
                mysqlps.setDouble(2,resultSet.getInt("counts"));
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

