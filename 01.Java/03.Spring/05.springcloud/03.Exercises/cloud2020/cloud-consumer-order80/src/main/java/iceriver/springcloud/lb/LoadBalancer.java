package iceriver.springcloud.lb;

import java.util.List;

import org.springframework.cloud.client.ServiceInstance;

/**
 * @author zhuyuqi
 * @version v2.0
 * @interfaceName LoadBalancer
 * @description https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date 2022/08/23 11:41
 */
public interface LoadBalancer {
    /**
     * 根据算法确定的的实例服务提供者实例
     * 
     * @param serviceInstances 注册中心可用的服务提供者
     * @return
     */
    ServiceInstance instances(List<ServiceInstance> serviceInstances);
}
