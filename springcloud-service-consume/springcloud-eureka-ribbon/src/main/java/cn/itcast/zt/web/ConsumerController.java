package cn.itcast.zt.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by zhangtian on 2017/4/19.
 */
@RestController
public class ConsumerController {
    @Autowired
    private RestTemplate restTemplate ;

    @GetMapping(value = "/addRibbon")
    public String add() {
        return restTemplate.getForEntity("http://COMPUTE-SERVICE/add?a=10&b=20", String.class).getBody() ;
    }
}
