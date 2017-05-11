package cn.itcast.zt.domain;

import java.io.Serializable;

/**
 * 认证请求
 * Created by Administrator on 2017/5/11/011.
 */
public class LopAuthenticationRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    private String username;
    private String password;

    public LopAuthenticationRequest() {
        super();
    }

    public LopAuthenticationRequest(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
