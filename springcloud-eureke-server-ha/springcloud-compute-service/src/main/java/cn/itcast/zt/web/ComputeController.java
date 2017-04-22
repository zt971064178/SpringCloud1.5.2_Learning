package cn.itcast.zt.web;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Administrator on 2017/4/22/022.
 */
@RestController
public class ComputeController {
    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private DiscoveryClient discoveryClient ;

    @Autowired
    private Registration registration ;

    @GetMapping(value = "add")
    public String add(@RequestParam Integer a, @RequestParam Integer b) {
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances(registration.getServiceId()) ;
        Integer r = a + b;
        for(ServiceInstance serviceInstance : serviceInstances) {
            logger.info("/add, host:" + serviceInstance.getHost() + ", service_id:" + serviceInstance.getServiceId() + ", result:" + r);

        }
        return "Result is " + r;
    }
}
