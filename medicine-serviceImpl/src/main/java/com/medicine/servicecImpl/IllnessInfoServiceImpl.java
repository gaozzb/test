package com.medicine.servicecImpl;


import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.medicine.mapper.IllnessInfoMapper;
import com.medicine.medicineService.IllnessInfoService;
import com.medicine.model.IllnessInfo;
import org.springframework.stereotype.Component;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zaolaotou
 * @since 2019-09-28
 */
@Service
@Component
public class IllnessInfoServiceImpl extends ServiceImpl<IllnessInfoMapper, IllnessInfo> implements IllnessInfoService {

    @Override
    public void Test(IllnessInfo info) {
        this.baseMapper.insert(info);
        System.err.println("Test");
    }
}
