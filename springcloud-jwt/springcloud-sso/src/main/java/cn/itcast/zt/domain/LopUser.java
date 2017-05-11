package cn.itcast.zt.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 用户信息
 * Created by Administrator on 2017/5/11/011.
 */
@Entity
public class LopUser implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    private String id;// 用户id
    @Column(unique = true)
    private String username;// 帐号
    private String password; // 密码;
    private String email;
    private int age;
    @NotNull
    private String sex;
    private String address;
    private String headerImage;

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date lastPasswordResetDate;

    //邮件激活码
    private String activeCode;

    /**
     * 定义中间表
     * <p>
     * 顺便设置级联关系,在保存时,可以级联保存角色表
     */
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(
            name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<LopRole> roles;// 每个用户具有多个角色

    /**
     * 激活状态 false or true
     */
    @NotNull
    private Boolean enabled;

    public LopUser() {

    }

    public LopUser(String username, String password, String email, int age, String sex, String address, String headerImage) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.age = age;
        this.sex = sex;
        this.address = address;
        this.headerImage = headerImage;
        this.enabled = false;   //需要激活
        this.lastPasswordResetDate = new Date();
        this.roles = Collections.singletonList(new LopRole(LopRoleName.ROLE_USER, "普通用户"));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public void setLastPasswordResetDate(Date lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    public String getActiveCode() {
        return activeCode;
    }

    public void setActiveCode(String activeCode) {
        this.activeCode = activeCode;
    }

    public List<LopRole> getRoles() {
        return roles;
    }

    public void setRoles(List<LopRole> roles) {
        this.roles = roles;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
