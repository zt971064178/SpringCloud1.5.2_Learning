package cn.itcast.zt.web;

import cn.itcast.zt.service.ComputeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhangtian on 2017/4/20.
 */
@RestController
public class ConsumerController {
    @Autowired
    private ComputeClient computeClient ;

    /**
     * 调用远程服务就像吊啊用本地服务一样
     * @return
     */
    @GetMapping(value = "/addFeign")
    public Integer add() {
        return computeClient.add(10,90) ;
    }
}
