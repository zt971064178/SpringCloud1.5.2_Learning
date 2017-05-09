package cn.itcast.zt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * http://www.ctolib.com/spring-boot-jwt.html#articleHeader0
 */
@SpringBootApplication
@EnableEurekaServer
public class SpringcloudServiceRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudServiceRegistryApplication.class, args);
	}
}
