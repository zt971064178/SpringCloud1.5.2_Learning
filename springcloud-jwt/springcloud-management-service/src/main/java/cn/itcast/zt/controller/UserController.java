package cn.itcast.zt.controller;

import cn.itcast.zt.dto.RoleDTO;
import cn.itcast.zt.dto.UserDTO;
import cn.itcast.zt.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

/**
 * 用户管理
 * Created by zhangtian on 2017/5/10.
 */
@RestController
@RequestMapping(value = "users")
public class UserController {
    @Autowired
    private UserManagementService userManagementService ;

    /**
     * 创建用户
     * @param userDTO
     * @return
     */
    @PostMapping
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        return userManagementService.createUser(userDTO);
    }

    /**
     * 删除用户
     * @param id
     */
    @DeleteMapping(value = "{id}")
    public void deleteUser(@PathVariable String id) {
        userManagementService.deleteUser(id);
    }

    /**
     * 查询所有用户列表
     * @return
     */
    @GetMapping
    public List<UserDTO> findAllUsers() {
        return userManagementService.findAllUsers();
    }

    /**
     * 查询用户详情
     * @param id
     * @return
     */
    @GetMapping(value = "{id}")
    public UserDTO findUserById(@PathVariable String id) {
        return userManagementService.findUserById(id);
    }

    /**
     * 查询用户所属角色列表
     * @param id
     * @return
     */
    @GetMapping(value = "{id}/roles")
    public Collection<RoleDTO> findUserRoles(@PathVariable String id) {
        return userManagementService.findAllRolesForUser(id);
    }

    /**
     * 跟新用户信息
     * @param id
     * @param userDTO
     */
    @PutMapping(value = "{id}")
    public void updateUser(@PathVariable String id, @RequestBody UserDTO userDTO) {
        userDTO = userManagementService.updateUser(id, userDTO);
    }

}
