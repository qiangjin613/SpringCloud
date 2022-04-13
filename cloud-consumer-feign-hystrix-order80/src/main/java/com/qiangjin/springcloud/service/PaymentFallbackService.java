package com.qiangjin.springcloud.service;

import org.springframework.stereotype.Component;


public class PaymentFallbackService implements PaymentFeignService {

    @Override
    public String paymentInfo(Long id) {
        return "统一的服务降级的方法";
    }

    @Override
    public String paymentInfoTimeout(Long id) {
        return "统一的服务降级的方法";
    }
}
