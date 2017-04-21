package cn.itcast.zt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
// 添加@EnableConfigServer注解，开启Config Server
@EnableCon
public class SpringcloudConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudConfigServerApplication.class, args);
	}
}
