package cn.itcast.zt.domain;

import java.io.Serializable;

/**
 * 认证响应  获取令牌token
 * Created by Administrator on 2017/5/11/011.
 */
public class LopAuthenticationResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String token;

    public LopAuthenticationResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }
}
