package cn.itcast.zt;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
/**
 * 服务注册中心
* 通过@EnableEurekaServer注解启动一个服务注册中心提供给其他应用进行对话。
* 这一步非常的简单，只需要在一个普通的Spring Boot应用中添加这个注解就能开启此功能
 * 启动之后访问：http://localhost:1111/
 */
@SpringBootApplication
// @EnableEurekaServer: 该注解表明应用为eureka服务，有可以联合多个服务作为集群，对外提供服务注册以及发现功能
@EnableEurekaServer
public class SpringcloudEurekaServerApplication {

	public static void main(String[] args) {
		//SpringApplication.run(SpringcloudEurekaServerApplication.class, args);
		new SpringApplicationBuilder(SpringcloudEurekaServerApplication.class).web(true).run(args);
	}
}
