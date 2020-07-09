package com.accesscontrol.manage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableTransactionManagement
@MapperScan("com.accesscontrol.manage.mapper")
public class AccesscontrolApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccesscontrolApplication.class, args);
    }

}
