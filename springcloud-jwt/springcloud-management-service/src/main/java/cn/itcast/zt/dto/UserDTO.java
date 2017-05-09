package cn.itcast.zt.dto;

import java.util.Collection;

/**
 * Created by Administrator on 2017/5/9/009.
 */
public class UserDTO {
    private String id ;
    private String email ;
    private String password ;
    private Collection<String> roleIds ;

    public UserDTO() {

    }

    public UserDTO(String id, String email, Collection<String> roleIds) {
        this.id = id;
        this.email = email;
        this.roleIds = roleIds;
    }

    public UserDTO(String id, String email, String password, Collection<String> roleIds) {
        this.email = email;
        this.password = password;
        this.roleIds = roleIds;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public Collection<String> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(Collection<String> roleIds) {
        this.roleIds = roleIds;
    }
}
