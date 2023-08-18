package com.example.tmallecharts.controller;


import com.example.tmallecharts.dao.productSumDao;
import com.example.tmallecharts.dao.table3Dao;
import com.example.tmallecharts.dao.table5Dao;
import com.example.tmallecharts.dao.table6Dao;
import com.example.tmallecharts.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 周
 */
@Controller
public class EchartsController {


    @Autowired
     productSumDao psDao;
    @Autowired
    table6Dao table6Dao;

    @Autowired
    table5Dao table5Dao;
    @Autowired
    table3Dao table3Dao;

    /**
     * 处理的页面的跳转
     * @return
     */
    @RequestMapping("/charts/productSum")
    public String getproductSum(){

        return "charts/productSum.jsp";
    }

    /**
     * /charts/getChargeData
     */
    @RequestMapping("/charts/getChargeData1")
    @ResponseBody
    public productSumList getChargeData1(){
        productSumList sumList = new productSumList();
        //创建俩个集合
        ArrayList<String> names = new ArrayList<>();
        ArrayList<Integer> values = new ArrayList<>();
        //从mysql中获取数据
        List<productSum> demo1Data = psDao.getDemo1Data();
        //把回去到的数据赋值给这个实体类
        for (productSum demo1Datum : demo1Data) {
            names.add(demo1Datum.getUser_name());
            values.add(demo1Datum.getCounts());
        }
        //
        sumList.setNames(names);
        sumList.setValues(values);
        //把值返回给echarts界面
        return sumList;
    }

    @RequestMapping("/charts/table6")
    public String getTable6(){
        return "charts/table6.jsp";
    }

    /**
     * /charts/getChargeData6
     */
    @RequestMapping("/charts/getChargeData6")
    @ResponseBody
    public  List<ValueName> getChargeData6(){
        //创建集合
        ArrayList<ValueName> valueNames = new ArrayList<>();
        //从数据库获取值
        List<tables6> demo6Data = table6Dao.getDemo6Data();
        for (tables6 demo6Datum : demo6Data) {
            valueNames.add(new ValueName(demo6Datum.getSums(),demo6Datum.getProduct_cate()));
        }
        return valueNames;

    }
    @RequestMapping("/charts/table5")
    public String getTable5(){
        return "charts/table5.jsp";
    }

    /**
     * /charts/getChargeData5
     */
    @RequestMapping("/charts/getChargeData5")
    @ResponseBody
    public  List<ValueName> getChargeData5(){
        //创建集合
        ArrayList<ValueName> valueNames = new ArrayList<>();
        //从数据库获取值
        List<tables5> demo5Data = table5Dao.getDemo5Data();
        for (tables5 demo5Datum : demo5Data) {
            valueNames.add(new ValueName(demo5Datum.getCounts(),demo5Datum.getGender()));
        }
        return valueNames;

    }
    @RequestMapping("/charts/table3")
    public String getTable3(){
        return "charts/table3.jsp";
    }

    @RequestMapping("/charts/getChargeData3")
    @ResponseBody
    public  List<ValueName> getChargeData3(){
        //创建集合
        ArrayList<ValueName> valueNames = new ArrayList<>();
        //从数据库获取值
        List<table3> demo3Data = table3Dao.getDemo3Data();
        for (table3 demo3Datum : demo3Data) {
            valueNames.add(new ValueName((int) demo3Datum.getPrice_sums(),demo3Datum.getGender()));
        }
        return valueNames;

    }
    @RequestMapping("/charts/table7")
    public String getTable4(){
        return "charts/table7.jsp";
    }
}
