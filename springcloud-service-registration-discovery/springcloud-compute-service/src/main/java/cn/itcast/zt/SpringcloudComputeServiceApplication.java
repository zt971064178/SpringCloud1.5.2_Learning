package cn.itcast.zt;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 服务提供方
 * 在主类中通过加上@EnableDiscoveryClient注解，
 * 该注解能激活Eureka中的DiscoveryClient实现，才能实现Controller中对服务信息的输出。
 */
@SpringBootApplication
// @EnableEurekaClient: 该注解表明应用既作为eureka实例又为eureka client 可以发现注册的服务
@EnableEurekaClient
public class SpringcloudComputeServiceApplication {

	public static void main(String[] args) {
		// SpringApplication.run(SpringcloudComputeServiceApplication.class, args);
		new SpringApplicationBuilder(SpringcloudComputeServiceApplication.class).web(true).run(args);
	}
}
