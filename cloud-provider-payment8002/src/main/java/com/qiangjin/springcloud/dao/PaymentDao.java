package com.qiangjin.springcloud.dao;

import com.qiangjin.springcloud.entity.PaymentDo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentDao {

    int crate(PaymentDo payment);

    PaymentDo getPaymentById(Long id);
}
