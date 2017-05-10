package cn.itcast.zt.controller;

import cn.itcast.zt.common.security.JsonWebTokenAuthentication;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * BaseController
 * Created by Administrator on 2017/5/9/009.
 */
public class BaseController {
    /**
     * 在认证处理AuthenticationController中，生成了token
     * 如果业务级别的Controller需要访问数据，也需要在请求头部加上Authentication  token数据
     * @return
     */
    protected String getAuthorizationToken() {
        String token = null;
        // 在请求头部加上Authentication  token数据之后,下面的代码authentication才会有JsonWebTokenAuthentication的数据
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getClass().isAssignableFrom(JsonWebTokenAuthentication.class)) {
            JsonWebTokenAuthentication jwtAuthentication = (JsonWebTokenAuthentication) authentication;
            token = jwtAuthentication.getJsonWebToken();
        }

        return token ;
    }
}
