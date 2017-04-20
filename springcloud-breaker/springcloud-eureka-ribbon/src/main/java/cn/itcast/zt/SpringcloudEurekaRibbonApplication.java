package cn.itcast.zt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 通过ribbon实现的服务单元，依赖compute-service的服务，端口3333
 * 在eureka-ribbon的主类RibbonApplication中使用@EnableCircuitBreaker注解开启断路器功能
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker// 开启断路器功能
public class SpringcloudEurekaRibbonApplication {

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate() ;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudEurekaRibbonApplication.class, args);
	}
}
