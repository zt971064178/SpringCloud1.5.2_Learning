package cn.itcast.zt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin.server.EnableZipkinServer;

/**
 * http://blog.csdn.net/forezp/article/details/70162074
 */
@SpringBootApplication
@EnableZipkinServer
public class SpringcloudServerZipkinApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudServerZipkinApplication.class, args);
	}
}
