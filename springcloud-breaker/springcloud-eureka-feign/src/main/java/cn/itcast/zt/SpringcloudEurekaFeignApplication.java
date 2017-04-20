package cn.itcast.zt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * 通过feign实现的服务单元，依赖compute-service的服务，端口3334
 */
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
//@SpringCloudApplication
public class SpringcloudEurekaFeignApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudEurekaFeignApplication.class, args);
	}
}
