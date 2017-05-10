package cn.itcast.zt.controller;

import cn.itcast.zt.dto.RoleDTO;
import cn.itcast.zt.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * 角色管理
 * Created by zhangtian on 2017/5/10.
 */
@RestController
@RequestMapping(value = "roles")
public class RoleController {
    @Autowired
    private UserManagementService userManagementService;

    /**
     * 创建角色
     * @param roleDTO
     * @return
     */
    @PostMapping
    public RoleDTO createRole(@RequestBody RoleDTO roleDTO) {
        return userManagementService.createRole(roleDTO);
    }

    /**
     * 删除角色
     * @param id
     */
    @DeleteMapping(value = "{id}")
    public void deleteRole(@PathVariable String id) {
        userManagementService.deleteRole(id);
    }

    /**
     * 查询所有角色列表
     * @return
     */
    @GetMapping
    public Collection<RoleDTO> findAllRoles() {
        return userManagementService.findAllRoles();
    }

    /**
     * 查询角色详情
     * @param id
     * @return
     */
    @RequestMapping(value = "{id}")
    public RoleDTO findRoleById(@PathVariable String id) {
        return userManagementService.findRoleById(id);
    }

    /**
     * 更新角色
     * @param id
     * @param roleDTO
     */
    @PutMapping(value = "{id}")
    public void updateRole(@PathVariable String id, @RequestBody RoleDTO roleDTO) {
        roleDTO = userManagementService.updateRole(id, roleDTO);
    }
}
