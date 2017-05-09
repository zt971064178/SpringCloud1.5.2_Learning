package cn.itcast.zt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * http://www.ctolib.com/spring-boot-jwt.html#articleHeader0
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class SpringcloudUiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudUiApplication.class, args);
	}
}
