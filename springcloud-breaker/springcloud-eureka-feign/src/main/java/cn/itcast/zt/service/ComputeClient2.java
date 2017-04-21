package cn.itcast.zt.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by zhangtian on 2017/4/20.
 */
@FeignClient(value = "compute-service", fallback = ComputeClientHystrix.class)
@Primary
public interface ComputeClient2 {

    /**
     * 调用远程服务
     * @param a
     * @param b
     * @return
     */
    @GetMapping(value = "/add")
    public Integer add(@RequestParam(value = "a") Integer a, @RequestParam(value = "b") Integer b) ;
}
