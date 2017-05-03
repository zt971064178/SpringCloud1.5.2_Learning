package cn.itcast.zt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * http://blog.csdn.net/forezp/article/details/70198649
 */
@EnableEurekaClient
@SpringBootApplication
@RestController
public class SpringcloudDockerServiceHiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudDockerServiceHiApplication.class, args);
	}

	@Value("${server.port}")
	private String port ;

	@GetMapping(value = "hi")
	public String home(@RequestParam String name) {
		return "hi "+name+",i am from port:" +port;
	}
}
