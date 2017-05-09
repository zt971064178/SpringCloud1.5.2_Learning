package cn.itcast.zt.controller;

import cn.itcast.zt.common.security.JsonWebTokenAuthentication;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * BaseController
 * Created by Administrator on 2017/5/9/009.
 */
public class BaseController {
    protected String getAuthorizationToken() {
        String token = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getClass().isAssignableFrom(JsonWebTokenAuthentication.class)) {
            JsonWebTokenAuthentication jwtAuthentication = (JsonWebTokenAuthentication) authentication;
            token = jwtAuthentication.getJsonWebToken();
        }

        return token ;
    }
}
