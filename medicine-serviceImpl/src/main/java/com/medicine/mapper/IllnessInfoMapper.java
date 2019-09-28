package com.medicine.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.medicine.model.IllnessInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zaolaotou
 * @since 2019-09-28
 */
@Repository
public interface IllnessInfoMapper extends BaseMapper<IllnessInfo> {

}
