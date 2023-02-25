package com.stonezpl.hr;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.stonezpl.hr.mapper")
public class HeartRecordApplication {

    public static void main(String[] args) {
        SpringApplication.run(HeartRecordApplication.class, args);
    }

}
