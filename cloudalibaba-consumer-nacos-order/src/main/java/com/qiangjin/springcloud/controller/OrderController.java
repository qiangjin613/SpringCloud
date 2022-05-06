package com.qiangjin.springcloud.controller;

import com.qiangjin.springcloud.entity.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Value("${server-url.nacos-payment-sercer}")
    private String paymentUrl = "http://provider-payment";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/echo/{str}")
    public String getEcho(@PathVariable("str") String str) {
        return restTemplate.getForObject(paymentUrl + "/echo/" + str, String.class);
    }
}
