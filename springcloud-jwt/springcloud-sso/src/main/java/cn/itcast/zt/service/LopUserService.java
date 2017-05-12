package cn.itcast.zt.service;

import cn.itcast.zt.CommonResult;
import cn.itcast.zt.domain.LopUser;
import cn.itcast.zt.domain.LopUserRequest;

/**
 * Created by zhangtian on 2017/5/12.
 */
public interface LopUserService {
    LopUser findByUsername(String username);

    CommonResult userRegister(LopUserRequest userRequest);

    //发送激活码
    CommonResult sendActiveCode(String activeCode,String username);

    //根据激活码激活用户
    CommonResult verifyAndActiveUser(boolean flag,String activeCode,String username);
}
