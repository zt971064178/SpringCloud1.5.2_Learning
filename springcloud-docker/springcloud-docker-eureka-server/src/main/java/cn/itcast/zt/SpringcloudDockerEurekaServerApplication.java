package cn.itcast.zt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * http://blog.csdn.net/forezp/article/details/70198649
 * https://github.com/forezp/SpringCloudLearning/tree/master/chapter11
 */
@SpringBootApplication
@EnableEurekaServer
public class SpringcloudDockerEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudDockerEurekaServerApplication.class, args);
	}
}
