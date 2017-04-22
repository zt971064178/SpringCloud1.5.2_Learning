package cn.itcast.zt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * 启动该应用，并访问http://localhost:1111/
 * 可以在Eureka Server的信息面板中看到config-server已经被注册了。
 */
@SpringBootApplication
// 新增@EnableDiscoveryClient注解，用来将config-server注册到上面配置的服务注册中心上去。
@EnableDiscoveryClient
@EnableConfigServer
public class SpringcloudConfigServerEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudConfigServerEurekaApplication.class, args);
	}
}
