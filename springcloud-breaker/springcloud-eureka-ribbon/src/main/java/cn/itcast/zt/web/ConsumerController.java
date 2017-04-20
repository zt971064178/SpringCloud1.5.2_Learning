package cn.itcast.zt.web;

import cn.itcast.zt.service.ComputeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhangtian on 2017/4/20.
 */
@RestController
public class ConsumerController {
    @Autowired
    private ComputeService computeService ;

    /**
     * 调用远程服务就像调用本地服务
     * @return
     */
    @GetMapping(value = "/addRibbon")
    public String add() {
        return computeService.addService() ;
    }
}
