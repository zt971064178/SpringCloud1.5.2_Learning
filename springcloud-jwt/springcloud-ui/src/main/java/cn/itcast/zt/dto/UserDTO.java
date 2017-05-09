package cn.itcast.zt.dto;

import java.util.List;

/**
 * Created by Administrator on 2017/5/9/009.
 */
public class UserDTO {
    private String id ;
    private String email ;
    private List<String> roleIds ;

    public UserDTO(){

    }

    public UserDTO(String id, String email, List<String> roleIds) {
        this.id = id;
        this.email = email;
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

    public List<String> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<String> roleIds) {
        this.roleIds = roleIds;
    }
}
