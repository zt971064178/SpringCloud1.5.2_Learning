package cn.itcast.zt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * http://www.ctolib.com/spring-boot-jwt.html#articleHeader0
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SpringcloudManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudManagementServiceApplication.class, args);
	}
}
