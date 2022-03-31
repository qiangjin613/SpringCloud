package com.qiangjin.springcloud.service;

import com.qiangjin.springcloud.entity.PaymentDo;

public interface PaymentService {

    int crate(PaymentDo payment);

    PaymentDo getPaymentById(Long id);
}
