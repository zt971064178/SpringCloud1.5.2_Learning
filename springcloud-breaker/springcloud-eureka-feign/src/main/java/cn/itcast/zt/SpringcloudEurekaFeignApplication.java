package cn.itcast.zt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * 通过feign实现的服务单元，依赖compute-service的服务，端口3334
 * @FeignClient中的fallback : 指定回调类。
 * @EnableFeignClients : 开启 Fegin 功能（包括 Hystrix ）。
 * @EnableHystrixDashboard : 开启 Hystrix Dashboard 监控视图
 */
@EnableDiscoveryClient
@EnableFeignClients
@EnableHystrixDashboard
@EnableCircuitBreaker
@SpringBootApplication
//@SpringCloudApplication
public class SpringcloudEurekaFeignApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudEurekaFeignApplication.class, args);
	}
}
