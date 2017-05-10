package cn.itcast.zt.controller;

import cn.itcast.zt.api.CloudUserManagementServiceAPI;
import cn.itcast.zt.dto.RoleDTO;
import cn.itcast.zt.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户管理
 * Created by Administrator on 2017/5/9/009.
 */
@RestController
@RequestMapping(value = "api/users/")
public class UserController extends BaseController {

    @Autowired
    private CloudUserManagementServiceAPI userManagementAPI ;

    /**
     * 创建用户
     * @param user
     * @return
     */
    @PostMapping
    public UserDTO createUser(@RequestBody UserDTO user) {
        return userManagementAPI.createUser(getAuthorizationToken(), user);
    }

    /**
     * 删除用户
     * @param id
     */
    @DeleteMapping(value = "{id}")
    public void deleteUser(@PathVariable String id) {
        userManagementAPI.deleteUser(getAuthorizationToken(), id);
    }

    /**
     * 获取用户列表
     * @return
     */
    @GetMapping
    public Iterable<UserDTO> findAllUsers() {
        return userManagementAPI.findAllUsers(getAuthorizationToken());
    }

    /**
     * 查询用户详情
     * @param id
     * @return
     */
    @GetMapping(value = "{id}")
    public UserDTO findUserById(@PathVariable String id) {
        return userManagementAPI.findUserById(getAuthorizationToken(), id);
    }

    /**
     * 查询用户所属角色列表
     * @param id
     * @return
     */
    @GetMapping(value = "{id}/roles")
    public Iterable<RoleDTO> findUserRoles(@PathVariable String id) {
        return userManagementAPI.findUserRoles(getAuthorizationToken(), id);
    }

    /**
     * 更新用户信息
     * @param id
     * @param user
     */
    @PutMapping(value = "{id}")
    public void updateUser(@PathVariable String id, @RequestBody UserDTO user) {
        userManagementAPI.updateUser(getAuthorizationToken(), id, user);
    }
}
