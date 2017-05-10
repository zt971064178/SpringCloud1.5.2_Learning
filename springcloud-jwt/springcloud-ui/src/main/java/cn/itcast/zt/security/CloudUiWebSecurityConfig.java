package cn.itcast.zt.security;

import cn.itcast.zt.common.security.JsonWebTokenSecurityConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**
 * Created by Administrator on 2017/5/9/009.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
@ComponentScan(value = "cn.itcast.zt")
public class CloudUiWebSecurityConfig extends JsonWebTokenSecurityConfig{
    @Override
    protected void setupAuthorization(HttpSecurity http) throws Exception {
    }
}
