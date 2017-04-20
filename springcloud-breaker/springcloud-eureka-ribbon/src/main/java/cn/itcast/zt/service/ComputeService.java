package cn.itcast.zt.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by zhangtian on 2017/4/20.
 * 改造原来的服务消费方式，新增ComputeService类，在使用ribbon消费服务的函数上增加@HystrixCommand注解来指定回调方法。
 */
@Service
public class ComputeService {
    @Autowired
    private RestTemplate restTemplate ;

    /**
     * 调用注册的服务
     * @return
     */
    @HystrixCommand(fallbackMethod = "addServiceFallBack")
    public String addService() {
        return restTemplate.getForEntity("http://COMPUTE-SERVICE/add?a=10&b=50", String.class).getBody() ;
    }

    public String addServiceFallBack() {
        return  "error" ;
    }

}
