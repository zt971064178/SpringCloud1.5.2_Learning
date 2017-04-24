package cn.itcast.zt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringcloudConfigClientEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudConfigClientEurekaApplication.class, args);
	}
}
