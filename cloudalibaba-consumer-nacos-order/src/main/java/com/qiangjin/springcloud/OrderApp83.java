package com.qiangjin.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class OrderApp83 {
    public static void main(String[] args) {
        SpringApplication.run(OrderApp83.class, args);
    }
}
