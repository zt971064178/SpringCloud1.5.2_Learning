package cn.itcast.zt.web;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 实现/add请求处理接口，通过DiscoveryClient对象，在日志中打印出服务实例的相关内容
 * Created by zhangtian on 2017/4/19.
 */
@RestController
public class ComputeController {

    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private Registration registration;

    @Autowired
    private DiscoveryClient discoveryClient ;

    @GetMapping(value = "/add")
    public Integer add(@RequestParam Integer a, @RequestParam Integer b) {
        String serverId = registration.getServiceId() ;// 此处为compute-service
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances(serverId) ;
        Integer r = a + b;
        for(ServiceInstance serviceInstance : serviceInstances) {
            logger.info("/add, host:" + serviceInstance.getHost() + ", service_id:" + serviceInstance.getServiceId() + ", result:" + r);
        }
        return r;
    }

}
