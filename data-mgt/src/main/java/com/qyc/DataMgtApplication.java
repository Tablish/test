package com.qyc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.qyc")
//@MapperScan("com.qyc.mapper.dc.ProductMapper")
//@MapperScan(basePackages = "com.qyc.mapper", sqlSessionTemplateRef = "dcSqlSessionTemplate")
//@MapperScan(basePackages = "com.qyc.mapper")
public class DataMgtApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataMgtApplication.class, args);
    }

}
