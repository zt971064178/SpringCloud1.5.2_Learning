package cn.itcast.zt.config;

import cn.itcast.zt.config.security.jwt.JwtAuthenticationEntryPoint;
import cn.itcast.zt.config.security.jwt.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.client.resource.OAuth2AccessDeniedException;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.resource.UserRedirectRequiredException;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.filter.CompositeFilter;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

/**
 * 权限配置
 * Created by zhangtian on 2017/5/11.
 */
@Configuration
@RestController
@EnableOAuth2Client
@EnableAuthorizationServer
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled=true)
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
    @Autowired
    private UserDetailsService userDetailService;

    @Autowired
    private OAuth2ClientContext oauth2ClientContext;

    @Autowired
    private JwtAuthenticationEntryPoint authenticationEntryPoint;

    /**
     * oauth2 认证 token
     *
     * @return 认证后的 oauthToken
     */
    @RequestMapping(value = "/oauthToken", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<OAuth2AccessToken> githubToken() {
        OAuth2AccessToken accessToken = null;
        try {
            accessToken = oauth2ClientContext.getAccessToken();
        } catch (UserRedirectRequiredException e) {
            return new ResponseEntity<>(HttpStatus.TEMPORARY_REDIRECT);
        } catch (OAuth2AccessDeniedException ex) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return ResponseEntity.ok(accessToken);
    }

    @Bean
    public OAuth2RestTemplate oAuth2RestTemplate(OAuth2ProtectedResourceDetails resource) {
        return new OAuth2RestTemplate(resource, oauth2ClientContext);
    }

    @Override
    @Bean // share AuthenticationManager for web and oauth
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean //redirect for Exception info
    public FilterRegistrationBean oauth2ClientFilterRegistration(
            OAuth2ClientContextFilter filter) {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(filter);
        registration.setDispatcherTypes(EnumSet.allOf(DispatcherType.class));   //设置错误页面分发器
        registration.setOrder(SecurityProperties.DEFAULT_FILTER_ORDER);         //order=-100
        return registration;
    }

    /**
     * 主过滤器
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .headers()
                .cacheControl()     // disable page caching
                .and()
                .frameOptions()
                .sameOrigin()
                .and()
                .csrf().disable()   //no need csrf token
                // 跨域支持
                .cors().and()
                .antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/", "/login", "/clearSession","/webjars/**", "/favicon.ico","/auth/**", "/login/github", "/oauthToken").permitAll()
                .antMatchers("/user/**").authenticated();
        http
//                .exceptionHandling()
//                .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"))
//                .and()
                //需要登录时不重定向，只返回一个 json
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
                .and()
                //session设置无状态,即请求间不能共享
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//                .and()
//                .formLogin().loginPage("/login").loginProcessingUrl("/login.do").defaultSuccessUrl("/success")
//                .failureUrl("/login?err=1")
//                .permitAll();
        ;
        http
                .logout()
                .logoutUrl("/clearSession")       //默认只接受post请求处理,需要携带csrf token
                .logoutSuccessUrl("/").permitAll()
                .invalidateHttpSession(true)    //清空 session，便于oauth 注销
                .clearAuthentication(true)
//                .and().csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()) //csrf can only get by http request
        ;

        http
                .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class)   // Custom JWT based security filter
                .addFilterBefore(ssoFilter(), JwtAuthenticationTokenFilter.class)
        ;
    }


    /**
     * 授权服务器(定义UserDetails类)
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        // Configure spring security's authenticationManager with custom
        // user details service
        auth.userDetailsService(this.userDetailService)
                .passwordEncoder(passwordEncoder());
    }

    /**
     * 密码编码方式,只编码不需要解码
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /**
     * 过滤器(第三方,需要注入到主过滤器)
     */
    private Filter ssoFilter() {
        CompositeFilter filter = new CompositeFilter();
        List<Filter> filters = new ArrayList<>();
        filters.add(ssoFilter(github(), "/login/github"));
        filter.setFilters(filters);
        return filter;
    }

    /**
     * 过滤器,验证 token
     *
     * @return
     * @throws Exception
     */
    @Bean
    public JwtAuthenticationTokenFilter jwtFilter() throws Exception {
        return new JwtAuthenticationTokenFilter();
    }

    /**
     * 资源服务器(第三方)
     */
    @Bean
    @ConfigurationProperties("github")
    public ClientResources github() {
        return new ClientResources();
    }


    /**
     * 本地的资源服务器
     */
    @Configuration
    @EnableResourceServer
    protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
        @Override
        public void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/api/**").authorizeRequests().anyRequest().authenticated();
        }
    }

    private Filter ssoFilter(ClientResources client, String path) {
        OAuth2ClientAuthenticationProcessingFilter filter = new OAuth2ClientAuthenticationProcessingFilter(path);
        OAuth2RestTemplate template = new OAuth2RestTemplate(client.getClient(), oauth2ClientContext);
        filter.setRestTemplate(template);
        UserInfoTokenServices tokenServices = new UserInfoTokenServices(
                client.getResource().getUserInfoUri(), client.getClient().getClientId());
        tokenServices.setRestTemplate(template);
        filter.setTokenServices(tokenServices);
        return filter;
    }

    class ClientResources {

        @NestedConfigurationProperty
        private AuthorizationCodeResourceDetails client = new AuthorizationCodeResourceDetails();

        @NestedConfigurationProperty
        private ResourceServerProperties resource = new ResourceServerProperties();

        public AuthorizationCodeResourceDetails getClient() {
            return client;
        }

        public ResourceServerProperties getResource() {
            return resource;
        }
    }
}
