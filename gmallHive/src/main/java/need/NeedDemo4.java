package need;

import utils.ConnectUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NeedDemo4 {
    static Connection hiveConnection = ConnectUtils.getHiveConnection();
    static Connection mysqlConnection = ConnectUtils.getMysqlConnection();
    public static void demo4(){
        try {
            //统计消费排名前5的同学都购买了哪些相同的商品
            PreparedStatement hiveps = hiveConnection.prepareStatement("select e.dbtitle from\n" +
                    "(select d.btitle dbtitle,count(*)counts from\n" +
                    "(select b.name,b.title btitle from \n" +
                    "(select name,count(*)sums from t_mall \n" +
                    "group by name order by sums desc limit 5)a\n" +
                    "join t_mall b on a.name=b.name\n" +
                    "group by b.name,b.title)d\n" +
                    "group by d.btitle)e\n" +
                    "where counts=5");
            //需要返回值
            ResultSet resultSet = hiveps.executeQuery();
            //?叫做占位符
            PreparedStatement mysqlps = mysqlConnection.prepareStatement("insert into product_common values (?)");
            while (resultSet.next()){
                //System.out.println(resultSet.getString(1)+"-->"+resultSet.getDouble(2));
                mysqlps.setString(1,resultSet.getString(1));
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
        demo4();
    }
}
