package cn.itcast.zt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 启动了两个客户端
 * 需要安装RabbitMQ
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SpringcloudConfigClientEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudConfigClientEurekaApplication.class, args);
	}
}
