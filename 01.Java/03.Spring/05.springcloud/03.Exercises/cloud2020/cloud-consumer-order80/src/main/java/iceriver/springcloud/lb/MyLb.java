package iceriver.springcloud.lb;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

/**
 * @author zhuyuqi
 * @version v0.0.1
 * @className MyLb
 * @description https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date 2022/08/23 11:44
 */
@Component
public class MyLb implements LoadBalancer {
    private AtomicInteger atomicInteger = new AtomicInteger(0);// 原子类

    /**
     * rest接口请求次数
     * 
     * @return
     */
    public final int getAndIncrement() {
        int current;
        int next;
        do {
            current = this.atomicInteger.get();
            next = current >= 2147483647 ? 0 : current + 1;
        } while (!this.atomicInteger.compareAndSet(current, next));// 自旋锁
        System.out.println("*********next:" + next);
        return next;
    }

    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {
        int index = getAndIncrement() % serviceInstances.size();
        return serviceInstances.get(index);
    }
}
