package com.example.tmallecharts.dao;

import com.example.tmallecharts.entity.tables6;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 周
 */
@Mapper
@Component("table6Mapper")
public interface table6Dao {
    @Select("select * from table6")
    List<tables6> getDemo6Data();
}
