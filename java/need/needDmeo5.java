package need;

import utils.ConnectUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class needDmeo5 {
    static Connection hiveConnection = ConnectUtils.getHiveConnection();
    static Connection mysqlConnection =ConnectUtils.getMysqlConnection();
    //
    //2.统计每个商品的购买次数，并找出热卖前5的商品
    public static void demo1(){
        try {
            PreparedStatement hiveps=hiveConnection.prepareStatement("select b.count1 female,c.count2 male\n" +
                    "from (select count(distinct(user_name)) count1\n" +
                    "from (select \n" +
                    "get_json_object(line,'$.user_gender') as gender,\n" +
                    "get_json_object(line,'$.user_name') as user_name\n" +
                    "from tmp_tmall)a where gender=1)b,\n" +
                    "(select count(distinct(user_name)) count2\n" +
                    "from (select \n" +
                    "get_json_object(line,'$.user_gender') as gender,\n" +
                    "get_json_object(line,'$.user_name') as user_name\n" +
                    "from tmp_tmall)a where gender=0)c");
            ResultSet resultSet=hiveps.executeQuery();
            //?叫做占位符
            PreparedStatement mysqlps=mysqlConnection.prepareStatement("insert into table5 values (?,?)");
            while(resultSet.next()){
                mysqlps.setInt(1,resultSet.getInt(1));
                mysqlps.setDouble(2,resultSet.getInt(2));
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

