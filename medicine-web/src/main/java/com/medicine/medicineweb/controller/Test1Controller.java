package com.medicine.medicineweb.controller;


import com.medicine.medicineapi1.service.TestService;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Controller
public class Test1Controller {

    @Value("${spring.profiles.active}")
    String str;

    @Reference
    private TestService testService;

    @RequestMapping("test1")
    public String method() {
        String s = testService.testMethod();
        System.out.println(s);
        return s;
    }

}
