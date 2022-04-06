package com.qiangjin.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerMain7003 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerMain7003.class, args);
    }
}
