package com.medicine.medicine.mapper;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author 高壮壮
 * @Date 2019/9/27
 */
@Repository
public interface TestMapper {

    //    @Select("select id from member limit 0,1")
    int getId();
}
