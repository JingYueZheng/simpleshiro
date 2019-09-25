package com.jyz.springbootshiro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan("com.jyz.springbootshiro.mvc.dao")
public class SpringbootshiroApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootshiroApplication.class, args);
    }
}
