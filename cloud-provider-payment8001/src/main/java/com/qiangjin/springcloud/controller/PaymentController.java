package com.qiangjin.springcloud.controller;

import com.qiangjin.springcloud.entity.CommonResult;
import com.qiangjin.springcloud.entity.PaymentDo;
import com.qiangjin.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody PaymentDo paymentDo) {
        int result = paymentService.crate(paymentDo);
        log.info("create result:{}", result);
        if (result > 0) {
            return new CommonResult(200, "成功", result);
        } else {
            return new CommonResult(444, "失败");
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult<PaymentDo> getPaymentById(@PathVariable("id") Long id) {
        PaymentDo payment = paymentService.getPaymentById(id);
        if (payment != null) {
            return new CommonResult<PaymentDo>(200, "成功", payment);
        } else {
            return new CommonResult(444, "无对应记录，id=" + id);
        }
    }

}
