package cn.itcast.zt.controller;

import cn.itcast.zt.api.CloudUserManagementServiceAPI;
import cn.itcast.zt.common.security.JsonWebTokenAuthentication;
import cn.itcast.zt.dto.RoleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

/**
 * 角色管理
 * Created by Administrator on 2017/5/9/009.
 */
@RestController
@RequestMapping(value = "api/roles")
public class RoleController extends BaseController {

    @Autowired
    private CloudUserManagementServiceAPI roleManagementAPI ;

    /**
     * 创建角色
     * @param role
     * @return
     */
    @PostMapping
    public RoleDTO createRole(@RequestBody RoleDTO role) {
        return roleManagementAPI.createRole(getAuthorizationToken(), role);
    }

    /**
     * 删除角色
     * @param id
     */
    @DeleteMapping(value = "{id}")
    public void deleteRole(@PathVariable String id) {
        roleManagementAPI.deleteRole(getAuthorizationToken(), id);
    }

    /**
     * 查询所有角色
     * @param jwt
     * @return
     */
    @GetMapping
    public Iterable<RoleDTO> findAllRoles(@AuthenticationPrincipal JsonWebTokenAuthentication jwt) {
        return roleManagementAPI.findAllRoles(getAuthorizationToken());
    }

    /**
     * 查询角色详情
     * @param id
     * @return
     */
    @GetMapping(value = "{id}")
    public RoleDTO findRoleById(@PathVariable String id) {
        return roleManagementAPI.findRoleById(getAuthorizationToken(), id);
    }

    /**
     * 修改角色
     * @param id
     * @param role
     */
    @PutMapping(value = "{id}")
    public void updateRole(@PathVariable String id, @RequestBody RoleDTO role) {
        roleManagementAPI.updateRole(getAuthorizationToken(), id, role);
    }
}
