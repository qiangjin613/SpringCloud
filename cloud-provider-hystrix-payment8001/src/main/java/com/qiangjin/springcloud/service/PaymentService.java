package com.qiangjin.springcloud.service;

public interface PaymentService {

    String paymentInfo(Long id);

    String paymentInfoTimeout(Long id);

    String paymentCircuitBreaker(Long id);
}
