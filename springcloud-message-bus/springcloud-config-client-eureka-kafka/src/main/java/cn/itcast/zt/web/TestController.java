package cn.itcast.zt.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhangtian on 2017/4/24.
 */
@RestController
@RefreshScope
public class TestController {
    @Value("${from}")
    private String from ;

    @GetMapping(value = "from")
    public String from (){
        return this.from ;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}
