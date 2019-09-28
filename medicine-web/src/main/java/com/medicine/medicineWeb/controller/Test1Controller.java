package com.medicine.medicineWeb.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.medicine.medicineService.IllnessInfoService;
import com.medicine.medicineService.TestService;
import com.medicine.model.IllnessInfo;
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

    @Reference
    private IllnessInfoService illnessInfoService;

    @RequestMapping("test1")
    public String method() {
//        IllnessInfo ii = new IllnessInfo();
//        ii.setNeurotrophicDrugs("99999999");
//        illnessInfoService.Test(ii);
        String s = testService.testMethod();
        System.out.println(s);
        return "success";
    }

}
