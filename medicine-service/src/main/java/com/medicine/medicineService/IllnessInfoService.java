package com.medicine.medicineService;


import com.baomidou.mybatisplus.service.IService;
import com.medicine.model.IllnessInfo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zaolaotou
 * @since 2019-09-28
 */
public interface IllnessInfoService extends IService<IllnessInfo> {

    void Test(IllnessInfo info);
}
