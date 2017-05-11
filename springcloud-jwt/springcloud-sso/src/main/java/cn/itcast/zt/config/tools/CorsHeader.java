package cn.itcast.zt.config.tools;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 定义垮域请求访问头
 * Created by Administrator on 2017/5/11/011.
 */
@Configuration
public class CorsHeader {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/success")
                        .allowedOrigins("http://127.0.0.1:8080")
                        /*.allowCredentials(true)
                        .allowedMethods("PUT", "DELETE")
                        .allowedHeaders("header1", "header2", "header3")
                        .exposedHeaders("header1", "header2")*/
                        .allowedHeaders("*")
                        .allowedMethods("*")
                        .allowCredentials(false).maxAge(3600);
            }
        } ;
    }
}
