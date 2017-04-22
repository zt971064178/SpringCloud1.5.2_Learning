package cn.itcast.zt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 通过@EnableDiscoveryClient注解来添加发现服务能力。
 * 创建RestTemplate实例，并通过@LoadBalanced注解开启均衡负载能力。
 */
@EnableDiscoveryClient
@SpringBootApplication
public class SpringcloudEurekaRibbonApplication {

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return  new RestTemplate() ;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudEurekaRibbonApplication.class, args);
	}
}
/*
	Ribbon是一个基于HTTP和TCP客户端的负载均衡器。Feign中也使用Ribbon，后续会介绍Feign的使用。
	Ribbon可以在通过客户端中配置的ribbonServerList服务端列表去轮询访问以达到均衡负载的作用。
	当Ribbon与Eureka联合使用时，ribbonServerList会被DiscoveryEnabledNIWSServerList重写，扩展成从Eureka注册中心中获取服务端列表。
	同时它也会用NIWSDiscoveryPing来取代IPing，它将职责委托给Eureka来确定服务端是否已经启动。
	下面我们通过实例看看如何使用Ribbon来调用服务，并实现客户端的均衡负载。
 */
