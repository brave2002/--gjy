package need;

import utils.ConnectUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class needDmeo6 {
    static Connection hiveConnection = ConnectUtils.getHiveConnection();
    static Connection mysqlConnection =ConnectUtils.getMysqlConnection();
    //
    //2.统计每个商品的购买次数，并找出热卖前5的商品
    public static void demo1(){
        try {
            PreparedStatement hiveps=hiveConnection.prepareStatement("select b.category cate,(case\n" +
                    "when   b.category='1' then '女装/大衣'\n" +
                    "when  b.category='2' then '男装/运动户外'\n" +
                    "when  b.category='3' then '女鞋/男鞋/箱包'\n" +
                    "when  b.category='4' then '美妆/个人护理'\n" +
                    "when  b.category='5' then '腕表/眼镜/珠宝饰品'\n" +
                    "when  b.category='6' then '手机/数码/电脑办公'\n" +
                    "when  b.category='8' then '零食/茶酒/进口食品'\n" +
                    "when  b.category='10' then '大家电/生活电器'\n" +
                    "end)product_category,sum(counts)sums from\n" +
                    "(select  a.product_category category,a.product_name name,count(a.product_name)counts from\n" +
                    "(select\n" +
                    "get_json_object(line,'$.product_category.category_id')as product_category,\n" +
                    "get_json_object(line,'$.product_name')as product_name\n" +
                    "from tmp_tmall)a group by a.product_category,a.product_name)b group by b.category\n" +
                    "order by sums");
            ResultSet resultSet=hiveps.executeQuery();
            //?叫做占位符
            PreparedStatement mysqlps=mysqlConnection.prepareStatement("insert into table6 values (?,?,?)");
            while(resultSet.next()){
                mysqlps.setInt(1,resultSet.getInt(1));
                mysqlps.setString(2,resultSet.getString(2));
                mysqlps.setInt(3,resultSet.getInt(3));
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

