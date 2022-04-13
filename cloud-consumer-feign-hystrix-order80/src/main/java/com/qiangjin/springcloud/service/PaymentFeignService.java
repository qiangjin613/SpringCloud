package com.qiangjin.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT-SERVICE")
@RequestMapping("/hystrix/payment")
public interface PaymentFeignService {

    @GetMapping("/info/{id}")
    String paymentInfo(@PathVariable("id") Long id);

    @GetMapping("/info/timeout/{id}")
    String paymentInfoTimeout(@PathVariable("id") Long id);
}
