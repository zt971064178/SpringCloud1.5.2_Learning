package cn.itcast.zt.domain;
import java.io.Serializable;

/**
 * 请求用户基本信息
 * Created by Administrator on 2017/5/11/011.
 */
public class LopUserRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    private String username;// 帐号
    private String password; // 密码;
    private String email;
    private int age;
    private String sex;
    private String address;
    private String headerImage;

    //必须要有默认的构造器（让父类也序列化）
    public LopUserRequest() {
        super();
    }

    public LopUserRequest(String username, String password, String email, int age, String sex, String address, String headerImage) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.age = age;
        this.sex = sex;
        this.address = address;
        this.headerImage = headerImage;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHeaderImage() {
        return headerImage;
    }

    public void setHeaderImage(String headerImage) {
        this.headerImage = headerImage;
    }
}
