package cn.itcast.zt.web;

import cn.itcast.zt.service.ComputeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhangtian on 2017/4/19.
 */
@RestController
public class ConsumerController {

    @Autowired
    private ComputeClient computeClient ;

    /**
     * controller层的调用url可以任意命名
     * @return
     */
    @GetMapping(value = "/addFeign")
    public String add() {
        return computeClient.add(20, 40) ;
    }

}
