package cn.itcast.zt.config.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.BasicErrorController;
import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 初始化 servlet 配置
 * HTTP 401, a.k.a. UNAUTHORIZED
 * Created by Administrator on 2017/5/11/011.
 */
@Controller
@Configuration
public class ServletCustomizer extends BasicErrorController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServletCustomizer.class);

    @Autowired
    public ServletCustomizer(ServerProperties serverProperties) {
        super(new DefaultErrorAttributes(), serverProperties.getError());
    }

    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
        return new MyCustomizer();
    }

    /**
     * 错误页面处理
     */
    private static class MyCustomizer implements EmbeddedServletContainerCustomizer {
        @Override
        public void customize(ConfigurableEmbeddedServletContainer container) {
            container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404.html"));
        }
    }

    /**
     * 覆盖默认的Json响应
     */
    @Override
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        Map<String, Object> body = getErrorAttributes(request,
                isIncludeStackTrace(request, MediaType.ALL));
        HttpStatus status = getStatus(request);

        LOGGER.error(body.toString());

        //输出自定义的Json格式
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", false);
        map.put("msg", body.get("message"));

        return new ResponseEntity<Map<String, Object>>(map, status);
    }

    /**
     * 覆盖默认的HTML响应
     */
    @Override
    public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(getStatus(request).value());
        Map<String, Object> model = getErrorAttributes(request,
                isIncludeStackTrace(request, MediaType.TEXT_HTML));

        //指定自定义的视图
        return new ModelAndView("error", model);
    }
}
