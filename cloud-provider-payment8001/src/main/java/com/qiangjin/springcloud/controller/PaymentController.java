package com.qiangjin.springcloud.controller;

import com.qiangjin.springcloud.entity.CommonResult;
import com.qiangjin.springcloud.entity.PaymentDo;
import com.qiangjin.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/create")
    public CommonResult create(@RequestBody PaymentDo paymentDo) {
        int result = paymentService.crate(paymentDo);
        log.info("create result:{}", result);
        if (result > 0) {
            return new CommonResult(200, "成功，serverPort：" + serverPort, result);
        } else {
            return new CommonResult(444, "失败，serverPort：" + serverPort);
        }
    }

    @GetMapping("/get/{id}")
    public CommonResult<PaymentDo> getPaymentById(@PathVariable("id") Long id) {
        PaymentDo payment = paymentService.getPaymentById(id);
        if (payment != null) {
            return new CommonResult<PaymentDo>(200, "成功，serverPort：" + serverPort, payment);
        } else {
            return new CommonResult(444, "无对应记录，id=" + id + "，serverPort：" + serverPort);
        }
    }

    /**
     * 服务发现 Demo
     */
    @GetMapping("/discovery")
    public Object discovery() {
        /* 获取入驻 Eureka 的所有服务 */
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("*** element: " + service + " ***");
            /* 根据具体服务名称获取服务信息 */
            List<ServiceInstance> instances = discoveryClient.getInstances(service);
            for (ServiceInstance instance : instances) {
                log.info(instance.getServiceId() + "\t" + instance.getHost() + ":" + instance.getPort() + "\t" + instance.getUri());
            }
        }
        return discoveryClient;
    }

    @GetMapping("/serverPort")
    public String getServerPort() {
        return serverPort;
    }

    /**
     * 超时控制 Demo
     */
    @GetMapping("/timeout")
    public String getTimeoutServerPort() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }
        return serverPort;
    }
}
