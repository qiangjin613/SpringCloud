package com.qiangjin.springcloud.service.impl;

import com.qiangjin.springcloud.dao.PaymentDao;
import com.qiangjin.springcloud.entity.PaymentDo;
import com.qiangjin.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentDao paymentDao;

    public int crate(PaymentDo payment) {
        return paymentDao.crate(payment);
    }

    @Override
    public PaymentDo getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
