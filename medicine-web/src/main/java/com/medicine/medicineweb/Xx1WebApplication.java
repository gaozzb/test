package com.medicine.medicineweb;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@EnableDubboConfiguration
public class Xx1WebApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Xx1WebApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Xx1WebApplication.class).properties("spring.config.name=application").bannerMode(Banner.Mode.OFF);
    }


}
