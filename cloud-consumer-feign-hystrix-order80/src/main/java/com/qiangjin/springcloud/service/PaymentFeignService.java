package com.qiangjin.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT-SERVICE", fallback = PaymentFallbackService.class)
//@RequestMapping("/hystrix/payment") 如果将 @RequestMapping 在这里使用，并且配置了fallback，将导致项目启动报错！
public interface PaymentFeignService {

    @GetMapping("/hystrix/payment/info/{id}")
    String paymentInfo(@PathVariable("id") Long id);

    @GetMapping("/hystrix/payment/info/timeout/{id}")
    String paymentInfoTimeout(@PathVariable("id") Long id);
}
