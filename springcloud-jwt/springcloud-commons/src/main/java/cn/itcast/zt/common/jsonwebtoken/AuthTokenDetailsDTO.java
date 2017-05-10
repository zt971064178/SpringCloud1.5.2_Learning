package cn.itcast.zt.common.jsonwebtoken;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

/**
 * Created by zhangtian on 2017/5/9.
 */
public class AuthTokenDetailsDTO {
    private String userId ;
    private String email;
    private List<String> rolesNames ;
    private List<String> roleIds ;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private Date expirationDate ;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRolesNames() {
        return rolesNames;
    }

    public void setRolesNames(List<String> rolesNames) {
        this.rolesNames = rolesNames;
    }

    public List<String> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<String> roleIds) {
        this.roleIds = roleIds;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
}
