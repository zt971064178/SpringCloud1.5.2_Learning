package cn.itcast.zt.controller;

import cn.itcast.zt.common.jsonwebtoken.AuthTokenDetailsDTO;
import cn.itcast.zt.common.jsonwebtoken.JsonWebTokenUtility;
import cn.itcast.zt.dto.AuthTokenDTO;
import cn.itcast.zt.dto.AuthenticationDTO;
import cn.itcast.zt.dto.RoleDTO;
import cn.itcast.zt.dto.UserDTO;
import cn.itcast.zt.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 认证管理
 * Created by zhangtian on 2017/5/10.
 */
@RestController
public class AuthenticationController {
    @Autowired
    private UserManagementService userManagementService;
    private JsonWebTokenUtility tokenService = new JsonWebTokenUtility();

    // 作为被调用方法传递对象时被调用的方法可以加入@RequestBody
    @PostMapping(value = "authenticate")
    public AuthTokenDTO authenticate(@RequestBody AuthenticationDTO authenticationDTO) {
        AuthTokenDTO authToken = null;

        // Authenticate the user
        UserDTO userDTO = userManagementService.authenticateUser(authenticationDTO.getEmail(), authenticationDTO.getPassword());
        // TODO If authentication fails, return an unauthorized error code

        if (userDTO != null) {

            Collection<RoleDTO> roles = userManagementService.findAllRolesForUser(userDTO.getId());
            List<String> roleNames = null ;
            List<String> roleIds = null ;// 新增roleIds
            if(roles != null) {
                roleIds = roles.stream().map(r->r.getId()).collect(Collectors.toList());
                roleNames = roles.stream().map(r -> r.getName()).collect(Collectors.toList());
            }

            // Build the AuthTokenDetailsDTO
            AuthTokenDetailsDTO authTokenDetailsDTO = new AuthTokenDetailsDTO();

            authTokenDetailsDTO.setUserId(userDTO.getId());
            authTokenDetailsDTO.setEmail(userDTO.getEmail());
            authTokenDetailsDTO.setRoleIds(roleIds);
            authTokenDetailsDTO.setRolesNames(roleNames);
            authTokenDetailsDTO.setExpirationDate(buildExpirationDate());

            // Create auth token
            String jwt = tokenService.createJsonWebToken(authTokenDetailsDTO);
            if (jwt != null) {
                authToken = new AuthTokenDTO();
                authToken.setToken(jwt);
            }
        }

        return authToken;
    }

    private Date buildExpirationDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, 1);
        Date expirationDate = calendar.getTime();
        return expirationDate;
    }
}
