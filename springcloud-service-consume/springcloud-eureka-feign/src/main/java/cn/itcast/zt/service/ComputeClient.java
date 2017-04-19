package cn.itcast.zt.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 使用@FeignClient("compute-service")注解来绑定该接口对应compute-service服务
 * 通过Spring MVC的注解来配置compute-service服务下的具体实现。
 * Created by zhangtian on 2017/4/19.
 */
@FeignClient(value = "compute-service")
public interface ComputeClient {
    /**
     * /add不能任意命名，必须是服务提供方的url
     * @param a
     * @param b
     * @return
     */
    @GetMapping(value = "/add")
    public Integer add(@RequestParam(value = "a") Integer a, @RequestParam(value = "b") Integer b) ;
}
