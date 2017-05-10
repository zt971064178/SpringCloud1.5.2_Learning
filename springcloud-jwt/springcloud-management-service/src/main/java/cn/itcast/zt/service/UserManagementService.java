package cn.itcast.zt.service;

import cn.itcast.zt.domain.Role;
import cn.itcast.zt.domain.User;
import cn.itcast.zt.dto.RoleDTO;
import cn.itcast.zt.dto.UserDTO;
import cn.itcast.zt.repository.UserRepository;
import cn.itcast.zt.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 业务逻辑控制  操作用户角色
 * Created by Administrator on 2017/5/9/009.
 */
@Service
@Transactional
public class UserManagementService {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * 认证用户,非null则用户存在
     * @param email
     * @param password
     * @return
     */
    public UserDTO authenticateUser(String email, String password) {
        UserDTO userDTO = null;
        User user = userRepository.findByEmail(email);
        if (user != null) {
            if (user.getPasswordHash() == password.hashCode()) {
                userDTO = convertUserToUserDTO(user);
            }
        }
        return userDTO;
    }

    private Collection<RoleDTO> convertRolesToRoleDTOs(Collection<Role> roles) {
        List<RoleDTO> roleDTOs = new ArrayList<RoleDTO>();
        for (Role role : roles) {
            roleDTOs.add(convertRoleToRoleDTO(role));
        }
        return roleDTOs;
    }

    /**
     * 对象转换
     * @param roles
     * @return
     */
    private Collection<String> convertRolesToRoleIds(Collection<Role> roles) {
        List<String> roleIds = new ArrayList<>();
        for (Role userRole : roles) {
            roleIds.add(userRole.getId());
        }
        return roleIds;
    }

    /**
     * 对象转换
     * @param role
     * @return
     */
    private RoleDTO convertRoleToRoleDTO(Role role) {
        return new RoleDTO(role.getId(), role.getName());
    }

    private List<UserDTO> convertUsersToUserDTOs(Collection<User> users) {
        List<UserDTO> userDTOs = new ArrayList<UserDTO>();
        for (User user : users) {
            userDTOs.add(convertUserToUserDTO(user));
        }
        return userDTOs;
    }

    /**
     * 对象转换
     * @param user
     * @return
     */
    private UserDTO convertUserToUserDTO(User user) {
        Collection<String> roleIds = convertRolesToRoleIds(user.getRoles());
        return new UserDTO(user.getId(), user.getEmail(), roleIds);
    }

    /**
     * 创建角色
     * @param roleDTO
     * @return
     */
    public RoleDTO createRole(RoleDTO roleDTO) {
        Role role = new Role(roleDTO.getName());
        role = roleRepository.save(role);
        return convertRoleToRoleDTO(role);
    }

    /**
     * 创建用户
     * @param userDTO
     * @return
     */
    public UserDTO createUser(UserDTO userDTO) {
        Set<Role> roles = new HashSet<>(roleRepository.findAll(userDTO.getRoleIds()));
        User user = new User(null, userDTO.getEmail(), userDTO.getPassword(), roles);
        user = userRepository.save(user);
        userDTO = convertUserToUserDTO(user);
        return userDTO;
    }

    /**
     * 删除角色
     * @param id
     */
    public void deleteRole(String id) {
        Role role = roleRepository.findOne(id);
        if (role != null) {
            roleRepository.delete(role);
        }
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteUser(String id) {
        User user = userRepository.findOne(id);
        if (user != null) {
            userRepository.delete(user);
        }
    }

    /**
     * 查询所有角色列表
     * @return
     */
    public Collection<RoleDTO> findAllRoles() {
        List<Role> roles = roleRepository.findAll();
        Collection<RoleDTO> roleDTOs = convertRolesToRoleDTOs(roles);
        return roleDTOs;
    }

    /**
     * 查询用户所属角色列表
     * @param id
     * @return
     */
    public Collection<RoleDTO> findAllRolesForUser(String id) {
        Collection<Role> roles = new HashSet<>();
        User user = userRepository.findOne(id);
        if (user != null) {
            roles = user.getRoles();
        }
        Collection<RoleDTO> roleDTOs = convertRolesToRoleDTOs(roles);
        return roleDTOs;
    }

    /**
     * 查询所有用户列表
     * @return
     */
    public List<UserDTO> findAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOs = convertUsersToUserDTOs(users);
        return userDTOs;
    }

    /**
     * 根据id查询角色
     * @param id
     * @return
     */
    public RoleDTO findRoleById(String id) {
        return convertRoleToRoleDTO(roleRepository.findOne(id));
    }

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    public UserDTO findUserById(String id) {
        return convertUserToUserDTO(userRepository.findOne(id));
    }

    /**
     * 更新角色
     * @param id
     * @param roleDTO
     * @return
     */
    public RoleDTO updateRole(String id, RoleDTO roleDTO) {
        Role role = roleRepository.findOne(id);
        // TODO: make a converter for RoleDTO to Role
        role.setName(roleDTO.getName());
        return convertRoleToRoleDTO(roleRepository.save(role));
    }

    /**
     * 更新用户
     * @param id
     * @param userDTO
     * @return
     */
    public UserDTO updateUser(String id, UserDTO userDTO) {
        User user = userRepository.findOne(id);
        // TODO: make a converter for UserDTO to User
        Set<Role> roles = new HashSet<>(roleRepository.findAll(userDTO.getRoleIds()));
        user.setEmail(userDTO.getEmail());
        // Only set the password if it was passed in
        if (userDTO.getPassword() != null) {
            user.setPassword(userDTO.getPassword());
        }
        user.setRoles(roles);
        user = userRepository.save(user);
        return convertUserToUserDTO(user);
    }
}
