package need;

import utils.ConnectUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class needDmeo4 {
    static Connection hiveConnection = ConnectUtils.getHiveConnection();
    static Connection mysqlConnection =ConnectUtils.getMysqlConnection();
    //
    //4.每天购买金额总数排名前三的用户及金额数。
    public static void demo1(){
        try {
            PreparedStatement hiveps=hiveConnection.prepareStatement("select c.atimes times,c.auser_name user_name,round(c.sums,0) price_sums from \n" +
                    "(select b.atimes,b.auser_name,b.sums,row_number()over(partition by b.atimes order by b.sums desc)flag from\n" +
                    "(select a.times atimes,a.user_name auser_name,sum(a.product_sale_price)sums\n" +
                    "from (select\n" +
                    "get_json_object(line,'$.times')as times,\n" +
                    "get_json_object(line,'$.user_name')as user_name,\n" +
                    "get_json_object(line,'$.product_sale_price')as product_sale_price from tmp_tmall)a group by a.user_name,a.times)b)c \n" +
                    "where c.flag<=3");
            ResultSet resultSet=hiveps.executeQuery();
            //?叫做占位符
            PreparedStatement mysqlps=mysqlConnection.prepareStatement("insert into table4 values (?,?,?)");
            while(resultSet.next()){
                mysqlps.setString(1,resultSet.getString(1));
                mysqlps.setString(2,resultSet.getString(2));
                mysqlps.setDouble(3,resultSet.getDouble(3));
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

