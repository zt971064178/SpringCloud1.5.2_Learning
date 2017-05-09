package cn.itcast.zt.dto;

/**
 * Created by Administrator on 2017/5/9/009.
 */
public class RoleDTO {
    private String id;
    private String name;

    public RoleDTO() {

    }

    public RoleDTO(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public RoleDTO(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
