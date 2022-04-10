package com.qiangjin.springcloud.controller;

import com.qiangjin.springcloud.entity.CommonResult;
import com.qiangjin.springcloud.entity.PaymentDo;
import com.qiangjin.springcloud.lb.LoadBalancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/consumer/order")
public class OrderController {

    /*
    注意：当使用 Ribbon + RestTemplate 并且使用了负载均衡 @LoadBalanced 时，
    getForObject() 的 String url 参数不能为地址。
    否则，抛出：java.lang.IllegalStateException: No instances available for 192.168.XXX.XXX 异常！
     */
    // public static final String PAYMENT_URL = "http://localhost:8001";
    public static final String PAYMENT_URL = "http://CLOUD-PROVIDER-PAYMENT-SERVICE";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancer loadBalancer;

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/create")
    public CommonResult<PaymentDo> create(PaymentDo payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
    }

    @GetMapping("/get/{id}")
    public CommonResult<PaymentDo> getPayment(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
    }

    @GetMapping("/payment/serverPort")
    public String getLbServerPort() {
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PROVIDER-PAYMENT-SERVICE");
        if (instances == null || instances.size() <= 0) {
            return null;
        }
        ServiceInstance serviceInstance = loadBalancer.instances(instances);
        URI uri = serviceInstance.getUri();

        /*
        注意：当使用 Ribbon + RestTemplate 并且使用了负载均衡 @LoadBalanced 时，
        getForObject() 的 String url 参数不能为地址。
        否则，抛出：java.lang.IllegalStateException: No instances available for 192.168.XXX.XXX 异常！
         */
        return restTemplate.getForObject(uri + "/payment/serverPort", String.class);
    }
}
