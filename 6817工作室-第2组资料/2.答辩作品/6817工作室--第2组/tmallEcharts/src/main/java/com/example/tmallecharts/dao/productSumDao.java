package com.example.tmallecharts.dao;


import com.example.tmallecharts.entity.productSum;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 周
 */
@Mapper
@Component("productSumsMapper")
public interface productSumDao {
    @Select("select * from product_sums")
    List<productSum> getDemo1Data();
}
