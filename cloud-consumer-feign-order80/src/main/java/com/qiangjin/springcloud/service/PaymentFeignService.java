package com.qiangjin.springcloud.service;

import com.qiangjin.springcloud.entity.CommonResult;
import com.qiangjin.springcloud.entity.PaymentDo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@FeignClient("CLOUD-PROVIDER-PAYMENT-SERVICE")
@RequestMapping("/payment")
public interface PaymentFeignService {

    @PostMapping("/create")
    CommonResult create(@RequestBody PaymentDo paymentDo);

    @GetMapping("/get/{id}")
    CommonResult<PaymentDo> getPaymentById(@PathVariable("id") Long id);

    @GetMapping("/discovery")
    Object discovery();

    @GetMapping("/serverPort")
    String getServerPort();

    @GetMapping("/timeout")
    String getTimeoutServerPort();
}
