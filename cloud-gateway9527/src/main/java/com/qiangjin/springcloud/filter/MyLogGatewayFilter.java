package com.qiangjin.springcloud.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class MyLogGatewayFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("come in MyLogGatewayFilter.filter ...");
        String username = exchange.getRequest().getQueryParams().getFirst("username");
        // 当用户名为 null 时，拦截
        if (username == null) {
            log.info("username 为 null，不允许通过");
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return exchange.getResponse().setComplete();
        }
        log.info("MyLogGatewayFilter.filter 执行结束");
        // 放行
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0; /* 数值越小优先级越高 */
    }
}
