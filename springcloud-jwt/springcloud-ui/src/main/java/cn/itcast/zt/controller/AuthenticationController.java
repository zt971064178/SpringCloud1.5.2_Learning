package cn.itcast.zt.controller;

import cn.itcast.zt.api.CloudUserManagementServiceAPI;
import cn.itcast.zt.dto.AuthTokenDTO;
import cn.itcast.zt.dto.AuthenticationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 认证Controller
 * Created by Administrator on 2017/5/9/009.
 */
@RestController
public class AuthenticationController {

    @Autowired
    private CloudUserManagementServiceAPI cloudUserManagementServiceAPI ;

    @PostMapping(value = "authenticate")
    public AuthTokenDTO authenticate(@RequestBody AuthenticationDTO authenticationDTO) {
        // Authenticate the user
        AuthTokenDTO authTokenDTO = cloudUserManagementServiceAPI.authenticateUser(authenticationDTO) ;
        // TODO If authentication fails, return an unauthorized error code
        return authTokenDTO ;
    }
}
