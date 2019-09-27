package com.medicine.medicine;


import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@EnableDubboConfiguration
@SpringBootApplication
@MapperScan("com.medicine.medicine.mapper")
public class MedicineApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(MedicineApplication.class).properties("spring.config.name=application").bannerMode(Banner.Mode.OFF).run(args);
    }
}
