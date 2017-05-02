package cn.itcast.zt.consul;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 参考：
 * http://blog.csdn.net/forezp/article/details/70245644
 * http://blog.csdn.net/forezp/article/details/70188595
 * 服务注册到服务注册中心Consul
 *
 * Created by zhangtian on 2017/5/2.
 */
@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class ConsulMiyaApplication {
    @GetMapping(value = "hi")
    public String home() {
        return "hi, i am miya!" ;
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(ConsulMiyaApplication.class).web(true).run(args) ;
    }
}
