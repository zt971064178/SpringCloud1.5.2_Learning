package cn.itcast.zt.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 系统角色实体类;
 * Created by Administrator on 2017/5/11/011.
 */
@Entity
public class LopRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    private String id;// 用户id

    @NotNull
    @Enumerated(EnumType.STRING)
    private LopRoleName roleName; // 角色.如"admin",这个是唯一的:

    private String description; // 角色描述,UI界面显示使用
    private Boolean available = Boolean.FALSE; // 是否可用,如果不可用将不会添加给用户

    //多个用户-多个角色
    //mappedBy 父表关联用户,从表关联角色。mappedBy指从表中关联的角色。
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private List<LopUser> lopUsers;

    public LopRole() {

    }

    public LopRole(LopRoleName roleName) {
        this.roleName = roleName;
        this.available = Boolean.TRUE;
    }

    public LopRole(LopRoleName roleName, String description) {
        this.roleName = roleName;
        this.description = description;
        this.available = Boolean.TRUE;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LopRoleName getRoleName() {
        return roleName;
    }

    public void setRoleName(LopRoleName roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public List<LopUser> getLopUsers() {
        return lopUsers;
    }

    public void setLopUsers(List<LopUser> lopUsers) {
        this.lopUsers = lopUsers;
    }

    @Override
    public String toString() {
        return "LopRole{" +
                "id=" + id +
                ", roleName=" + roleName +
                ", description='" + description + '\'' +
                ", available=" + available +
                ", lopUsers=" + lopUsers +
                '}';
    }
}
