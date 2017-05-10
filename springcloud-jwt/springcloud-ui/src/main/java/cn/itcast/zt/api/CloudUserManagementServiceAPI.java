package cn.itcast.zt.api;

import cn.itcast.zt.dto.AuthTokenDTO;
import cn.itcast.zt.dto.AuthenticationDTO;
import cn.itcast.zt.dto.RoleDTO;
import cn.itcast.zt.dto.UserDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * 调用服务
 * Created by Administrator on 2017/5/9/009.
 */
@FeignClient(value = "springcloud-user-management-service")
public interface CloudUserManagementServiceAPI {
    // 认证用户，不需要认证
    @PostMapping(value = "authenticate")
    AuthTokenDTO authenticateUser(@RequestBody AuthenticationDTO authenticationDTO);// 传递对象时被调用的方法可以加入@RequestBody

    // 创建角色，需要认证
    @PostMapping(value = "roles")
    RoleDTO createRole(@RequestHeader("Authorization") String authorizationToken, @RequestBody RoleDTO roleDTO);

    // 创建用户,需要认证
    @PostMapping(value = "users")
    UserDTO createUser(@RequestHeader("Authorization") String authorizationToken, @RequestBody UserDTO userDTO);

    // 删除角色，需要认证
    @DeleteMapping(value = "roles/{id}")
    void deleteRole(@RequestHeader("Authorization") String authorizationToken, @PathVariable("id") String id);

    // 删除用户，需要认证
    @DeleteMapping(value = "users/{id}")
    void deleteUser(@RequestHeader("Authorization") String authorizationToken, @PathVariable("id") String id);

    // 查询所有角色列表，需要认证
    @GetMapping(value = "roles")
    Collection<RoleDTO> findAllRoles(@RequestHeader("Authorization") String authorizationToken);

    // 查询所有用户列表，需要认证
    @GetMapping(value = "users")
    Collection<UserDTO> findAllUsers(@RequestHeader("Authorization") String authorizationToken);

    // 通过id查询角色信息,需要认证
    @GetMapping(value = "roles/{id}", produces = "application/json", consumes = "application/json")
    RoleDTO findRoleById(@RequestHeader("Authorization") String authorizationToken, @PathVariable("id") String id);

    // 通过id查询用户信息,需要认证
    @GetMapping(value = "users/{id}", produces = "application/json", consumes = "application/json")
    UserDTO findUserById(@RequestHeader("Authorization") String authorizationToken, @PathVariable("id") String id);

    // 查询用户所属角色,需要认证
    @GetMapping(value = "users/{id}/roles")
    Collection<RoleDTO> findUserRoles(@RequestHeader("Authorization") String authorizationToken,
                                      @PathVariable("id") String id);

    // 根据角色id更新角色，需要认证
    @PutMapping(value = "roles/{id}")
    void updateRole(@RequestHeader("Authorization") String authorizationToken, @PathVariable("id") String id,
                    @RequestBody RoleDTO roleDTO);

    // 根据用户id更新用户信息,需要认证
    @PutMapping(value = "users/{id}")
    void updateUser(@RequestHeader("Authorization") String authorizationToken, @PathVariable("id") String id,
                    @RequestBody UserDTO userDTO);
}
