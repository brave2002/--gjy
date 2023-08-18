package com.example.tmallecharts.dao;

import com.example.tmallecharts.entity.table3;
import com.example.tmallecharts.entity.tables5;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 周
 */
@Mapper
@Component("table3Mapper")
public interface table3Dao {
    @Select("select * from table3")
    List<table3> getDemo3Data();
}
