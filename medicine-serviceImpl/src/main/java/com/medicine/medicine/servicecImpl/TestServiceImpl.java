package com.medicine.medicine.servicecImpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.medicine.medicineapi1.service.TestService;
import com.medicine.medicine.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestMapper testMapper;

    @Override
    public String testMethod() {
        System.out.println("进到方法来了");
        return "success:" + testMapper.getId();
    }
}
