package need;

import utils.ConnectUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class needDmeo3 {
    static Connection hiveConnection = ConnectUtils.getHiveConnection();
    static Connection mysqlConnection =ConnectUtils.getMysqlConnection();
    //

    public static void demo1(){
        try {
            PreparedStatement hiveps=hiveConnection.prepareStatement("\n" +
                    "select b.times times,round(sum(b.price)) price_sum,\n" +
                    "(case  when b.gender=1 then '女'\n" +
                    "else '男' end) as gender\n" +
                    "from \n" +
                    "(select *,\n" +
                    "cast(user_gender as char(4)) as gender \n" +
                    "from (select\n" +
                    "get_json_object(line,'$.product_sale_price')as price,\n" +
                    "get_json_object(line,'$.times')as times,\n" +
                    "get_json_object(line,'$.user_gender')as user_gender\n" +
                    "from tmp_tmall)a)b\n" +
                    "group by b.gender,b.times\n" +
                    "order by b.times");
            ResultSet resultSet=hiveps.executeQuery();
            //?叫做占位符
            PreparedStatement mysqlps=mysqlConnection.prepareStatement("insert into table3 values (?,?,?)");
            while(resultSet.next()){
                mysqlps.setString(1,resultSet.getString(1));
                mysqlps.setDouble(2,resultSet.getDouble(2));
                mysqlps.setString(3,resultSet.getString(3));
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

