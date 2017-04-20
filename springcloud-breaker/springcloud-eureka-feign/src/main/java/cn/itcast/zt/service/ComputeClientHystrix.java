package cn.itcast.zt.service;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by zhangtian on 2017/4/20.
 */
@Component
public class ComputeClientHystrix implements ComputeClient2 {
    @Override
    public Integer add(@RequestParam(value = "a") Integer a, @RequestParam(value = "b") Integer b) {
        return -8888;
    }
}
