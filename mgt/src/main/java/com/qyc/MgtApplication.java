package com.qyc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication(scanBasePackages = "com.qyc.service")
//@MapperScan(basePackages = "com.qyc.mapper")
@SpringBootApplication
public class MgtApplication {

    public static void main(String[] args) {
        SpringApplication.run(MgtApplication.class, args);
    }
}