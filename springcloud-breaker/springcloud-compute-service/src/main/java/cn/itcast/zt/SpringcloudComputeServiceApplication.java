package cn.itcast.zt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 服务单元，端口2222
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SpringcloudComputeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudComputeServiceApplication.class, args);
	}
}
