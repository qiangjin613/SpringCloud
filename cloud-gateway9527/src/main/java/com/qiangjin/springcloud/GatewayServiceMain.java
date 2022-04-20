package com.qiangjin.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class GatewayServiceMain {
    public static void main(String[] args) {
        SpringApplication.run(GatewayServiceMain.class, args);
    }
}
