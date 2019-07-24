package com.baizhi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan("com.baizhi.dao")
@SpringBootApplication
public class CmfzDemo1Application {

    public static void main(String[] args) {
        SpringApplication.run(CmfzDemo1Application.class, args);
    }

}
