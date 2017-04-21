package cn.itcast.zt.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * http://127.0.0.1:7002/from
 * 如果配置中心的from的值是一个数组，取得是在配置中心中的最有一个
 * 即：后面的会覆盖前面的，取最后一个
 * spring.cloud.config.server.git.searchPaths=springcloud-config/config-repo,springcloud-config/config-label-test
 * 注意：当配置文件中内容被修改之后，服务端在一定时间之后会自动更新
 * 客户端需要调用：http://127.0.0.1:7002/refresh(注：必须是POST请求)刷新数据之后，配置属性在随之更改
 *
 * 1. config-client的pom.xml中新增spring-boot-starter-actuator监控模块，其中包含了/refresh刷新API。
 * 2. git中配置数据修改后，config-client中执行http://localhost:18443/refresh 即可实现client端配置数据更新
 * Created by zhangtian on 2017/4/21.
 */
@RestController
@RefreshScope
public class TestController {
    @Value("${from}")
    private String from ;

    @GetMapping(value = "from")
    public String from() {
        return this.from ;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}
