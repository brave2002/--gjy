package com.example.tmallecharts.dao;

import com.example.tmallecharts.entity.tables5;
import com.example.tmallecharts.entity.tables6;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 周
 */
@Mapper
@Component("table5Mapper")
public interface table5Dao {
    @Select("select * from table5")
    List<tables5> getDemo5Data();
}
