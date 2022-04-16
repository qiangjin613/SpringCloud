package com.qiangjin.springcloud.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.qiangjin.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

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

    /**
     * -------------------- 服务熔断 -----------------------------
     */
    @Override
    @HystrixCommand(fallbackMethod = "circuitBreakerFallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"), /* 是否开启断路器 */
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"), /* 请求次数 */
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), /* 时间窗口期 */
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"), /* 失败率，表示在一个时间窗口期里失败率达到多少后跳闸 */
    })
    public String paymentCircuitBreaker(@PathVariable("id") Long id) {
        if (id < 0) {
            throw new RuntimeException("id 不能为负数");
        }
        return "Application Name: " + applicationName +
                ", Server Port: " + serverPort +
                ", thread: " + Thread.currentThread().getName() +
                " 调用 paymentCircuitBreaker, id: " + id;
    }
    public String circuitBreakerFallback(@PathVariable("id") Long id, Throwable throwable) {
        throwable.printStackTrace();
        return "CircuitBreakerFallback，请稍后重试，id=" + id;
    }
}
