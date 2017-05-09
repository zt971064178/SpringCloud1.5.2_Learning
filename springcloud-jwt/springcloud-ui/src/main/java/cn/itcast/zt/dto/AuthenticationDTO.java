package cn.itcast.zt.dto;

/**
 * Created by Administrator on 2017/5/9/009.
 */
public class AuthenticationDTO {
    private String email ;
    private String password ;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
