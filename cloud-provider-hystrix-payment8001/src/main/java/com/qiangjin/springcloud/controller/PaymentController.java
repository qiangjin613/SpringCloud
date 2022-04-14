package com.qiangjin.springcloud.controller;

import com.qiangjin.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/hystrix/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/info/{id}")
    public String paymentInfo(@PathVariable("id") Long id) {
        String info = paymentService.paymentInfo(id);
        log.debug("serverPort:{}, " + info, serverPort);
        return info;
    }

    @GetMapping("/info/timeout/{id}")
    public String paymentInfoTimeout(@PathVariable("id") Long id) {
        //int i = 10 / 0;
        String infoTimeout = paymentService.paymentInfoTimeout(id);
        log.debug("serverPort:{}, " + infoTimeout, serverPort);
        return infoTimeout;
    }
}
