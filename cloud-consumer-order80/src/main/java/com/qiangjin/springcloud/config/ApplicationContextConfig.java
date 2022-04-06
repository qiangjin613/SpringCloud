package com.qiangjin.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {

    @Bean
    @LoadBalanced /* 使用 @LoadBalanced 注解赋予 RestTemplate 负载均衡的功能 */
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
