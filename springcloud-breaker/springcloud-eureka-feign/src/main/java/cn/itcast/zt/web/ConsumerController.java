package cn.itcast.zt.web;

import cn.itcast.zt.service.ComputeClient;
import cn.itcast.zt.service.ComputeClient2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhangtian on 2017/4/20.
 */
@RestController
public class ConsumerController {
    @Autowired
    private ComputeClient computeClient ;

    @Autowired
    private ComputeClient2 computeClient2 ;

    /**
     * 调用远程服务就像调用用本地服务一样
     * @return
     */
    @GetMapping(value = "/addFeign")
    public Integer add() {
        return computeClient.add(10,90) ;
    }

    /**
     * 调用远程服务就像调用用本地服务一样
     * @return
     */
    @GetMapping(value = "/addFeign2")
    public Integer add2() {
        return computeClient2.add(10,90) ;
    }
}
