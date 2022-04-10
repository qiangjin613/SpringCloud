package com.qiangjin.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 负载规则的自定义配置类不能放在 @ComponentScan 所扫描的当前包下以及子包下，
 * 否则我们自定义的这个配置类就会被所有的Ribbon客户端所共享，
 * 达不到特殊化定制的目的了。
 */
@Configuration
public class MySelfRule {

    @Bean
    public IRule myRule() {
        /* 负载规则替换为：随机 */
        return new RandomRule();
    }
}
