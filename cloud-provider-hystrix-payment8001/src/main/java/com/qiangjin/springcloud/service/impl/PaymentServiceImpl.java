package com.qiangjin.springcloud.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.qiangjin.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Value("${spring.application.name}")
    private String applicationName;
    @Value("${server.port}")
    private String serverPort;

    @Override
    public String paymentInfo(Long id) {
        return "thread: " + Thread.currentThread().getName() + " 调用 paymentInfo, id:" + id;
    }

    @Override
    @HystrixCommand(fallbackMethod = "timeoutFallbackMethod", commandProperties = {
            /* 超时的设置（这里是 copy 来的） */
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")
    })
    public String paymentInfoTimeout(Long id) {
        //int i = 8 / 0; /* 对异常的测试 */
        int timeout = 3;
        try {
            TimeUnit.SECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "thread: " + Thread.currentThread().getName() + " 调用 paymentInfoTimeout, id:" + id + ", use time: " + timeout;
    }

    public String timeoutFallbackMethod(Long id) {
        /* 对于请求参数，这里也是可以获取到的 */
        return "系统繁忙或报错，请稍后再试！ Application Name: " + applicationName +
                ", Server Port: " + serverPort +
                ", thread: " + Thread.currentThread().getName() +
                " 调用 timeoutFallbackMethod, id: " + id;
    }
}
