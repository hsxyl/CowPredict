package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.demo.mapper")
public class MainApplication {

    public static void main(String[] args) {

        SpringApplication.run(MainApplication.class, args);
        System.out.println("**************************");
        System.out.println("**************************");
        System.out.println("***                    ***");
        System.out.println("*** Application Start! ***");
        System.out.println("***                    ***");
        System.out.println("**************************");
        System.out.println("**************************");

    }
}
