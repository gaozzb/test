package com.medicine.medicineservice1.servicecImpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.medicine.medicineapi1.service.TestService;
import org.springframework.stereotype.Component;

@Component
@Service
public class TestServiceImpl implements TestService {

    @Override
    public String testMethod() {
        return "success";
    }
}
