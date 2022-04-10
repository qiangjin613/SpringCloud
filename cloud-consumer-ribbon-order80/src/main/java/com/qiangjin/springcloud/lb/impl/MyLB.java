package com.qiangjin.springcloud.lb.impl;

import com.qiangjin.springcloud.lb.LoadBalancer;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自己仿写一个负载均衡的算法
 */
@Component
public class MyLB implements LoadBalancer {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {
        int index = getAndIncrement(serviceInstances.size());
        return serviceInstances.get(index);
    }

    public final int getAndIncrement(int size) {
        int current;
        int next;
        do {
            current = this.atomicInteger.get();
            next = (current + 1) % size;
        } while (!this.atomicInteger.compareAndSet(current, next));
        return next;
    }
}
