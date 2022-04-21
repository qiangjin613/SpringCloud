package com.qiangjin.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    /**
     * 配置路由映射：
     *      http://localhost:9527/bilibili  --> https://www.bilibili.com/
     *      http://localhost:9527/docs      --> https://docs.oracle.com/javase/8/docs/api/index.html
     */
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        RouteLocatorBuilder.Builder routes = builder.routes();
        routes.route(
                "bilibiliRoute",
                r -> r.path("/bilibili").uri("https://www.bilibili.com")
        );
        routes.route(
                "javaDocRoute",
                r -> r.path("/docs").uri("https://docs.oracle.com")
        );
        return routes.build();
    }
}
