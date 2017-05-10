package cn.itcast.zt.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

/**
 * 用户Bean
 * Created by Administrator on 2017/5/9/009.
 */
@Entity
public class User {
    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    private String id ;
    private String email ;
    @ManyToMany
    private Set<Role> roles ;
    // For simplicity sake, we keep a simple hash code. In the real world, we
    // would do something better.
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private int passwordHash ;

    public User(){

    }

    public User(String id, String email) {
        this.id = id;
        this.email = email;
    }

    public User(String id, String email, Set<Role> roles) {
        this.id = id;
        this.email = email;
        this.roles = roles;
    }

    public User(String id, String email, String password ,Set<Role> roles) {
        this.id = id;
        this.email = email;
        this.roles = roles;
        setPassword(password);
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public int getPasswordHash() {
        return passwordHash;
    }

    public void setPassword(String password) {
        int hc = password.hashCode();
        setPasswordHash(hc);
    }

    public void setPasswordHash(int passwordHash) {
        this.passwordHash = passwordHash ;
    }

    @Override
    public String toString() {
        String rolesString = "";
        if (roles != null) {
            for (Role role : roles) {
                rolesString += role.toString();
            }
        }
        return String.format("User[id=%d, email=%s, roles=%s]", id, email, rolesString);
    }
}
