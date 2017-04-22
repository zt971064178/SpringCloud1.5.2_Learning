package cn.itcast.zt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 增加@EnableDiscoveryClient注解，用来发现config-server服务，利用其来加载应用配置
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SpringcloudConfigClientEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudConfigClientEurekaApplication.class, args);
	}
}
