package com.medicine.servicecImpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.medicine.mapper.TestMapper;
import com.medicine.medicineService.TestService;
import com.medicine.medicineutil.CacheUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;


@Component
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestMapper testMapper;

    @Autowired
    private CacheUtil cacheUtil;
    @Override
    public String testMethod() {

        cacheUtil.setStringToRedis("userId","500",10, TimeUnit.MINUTES);
        testMapper.getId();
        System.out.println("进到方法来了");
        return "success:" + testMapper.getId();
    }
}
