package com.swpu.apply_server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.swpu.apply_server.mapper")
public class ApplyServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApplyServerApplication.class,args);
    }
}
