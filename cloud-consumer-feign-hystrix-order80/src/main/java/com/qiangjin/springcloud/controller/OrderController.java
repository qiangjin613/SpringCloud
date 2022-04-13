package com.qiangjin.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.qiangjin.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/consumer/order")
public class OrderController {

    @Autowired
    private PaymentFeignService paymentFeignService;

    @Value("${spring.application.name}")
    private String applicationName;
    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/info/{id}")
    public String paymentInfo(@PathVariable("id") Long id) {
        String info = paymentFeignService.paymentInfo(id);
        log.debug("serverPort:{}, " + info);
        return info;
    }

    @GetMapping("/info/timeout/{id}")
    @HystrixCommand(fallbackMethod = "timeoutFallbackMethod", commandProperties = {
            /* 超时的设置 */
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000")
    })
    public String paymentInfoTimeout(@PathVariable("id") Long id) {
        String infoTimeout = paymentFeignService.paymentInfoTimeout(id);
        log.debug("serverPort:{}, " + infoTimeout);
        return infoTimeout;
    }

    /**
     * 在这里也会同 SpringMVC 中的 ResponseEntity 等一样，会将调用 fallbackMethod 的方发原因封装到 Throwable 中
     */
    public String timeoutFallbackMethod(Long id, Throwable throwable) {
        throwable.printStackTrace();
        /* 对于请求参数，这里也是可以获取到的 */
        return "系统繁忙或报错，请稍后再试！ Application Name: " + applicationName +
                ", Server Port: " + serverPort +
                ", thread: " + Thread.currentThread().getName() +
                " 调用 timeoutFallbackMethod, id: " + id;
    }
}
