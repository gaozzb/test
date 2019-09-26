package com.medicine.medicineservice1;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@EnableDubboConfiguration
//@DubboComponentScan("com.medicine.medicineservice1.servicecImpl")
@SpringBootApplication
public class MedicineService1Application {

    public static void main(String[] args) {
        new SpringApplicationBuilder(MedicineService1Application.class).properties("spring.config.name=application").bannerMode(Banner.Mode.OFF).run(args);
    }
}
