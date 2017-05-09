package cn.itcast.zt.security;

import cn.itcast.zt.common.security.JsonWebTokenSecurityConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Component;

/**
 * 权限配置
 * Created by Administrator on 2017/5/9/009.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Component(value = "cn.itcast.zt")
public class CloudUserManagementWebSecurityConfig extends JsonWebTokenSecurityConfig {
    @Override
    protected void setupAuthorization(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // allow anonymous access to /authenticate endpoint
                .antMatchers("/authenticate").permitAll()
                // authenticate all other requests
                .anyRequest().authenticated();
    }
}
