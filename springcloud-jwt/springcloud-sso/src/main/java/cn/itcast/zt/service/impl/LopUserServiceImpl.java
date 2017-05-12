package cn.itcast.zt.service.impl;

import cn.itcast.zt.CommonResult;
import cn.itcast.zt.domain.LopUser;
import cn.itcast.zt.domain.LopUserRequest;
import cn.itcast.zt.repository.LopUserRepository;
import cn.itcast.zt.service.LopUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Created by zhangtian on 2017/5/12.
 */
@Service
public class LopUserServiceImpl implements LopUserService {
    @Autowired
    private LopUserRepository lopUserRepository;

    @Override
    public LopUser findByUsername(String username) {
        return lopUserRepository.findByUsername(username);
    }

    @Override
    public CommonResult userRegister(LopUserRequest userRequest) {
        LopUser lopUser = new LopUser(
                userRequest.getUsername(),
                new BCryptPasswordEncoder().encode(userRequest.getPassword()),
                userRequest.getEmail(),
                userRequest.getAge(),
                userRequest.getSex(),
                userRequest.getAddress(),
                userRequest.getHeaderImage()
        );
        try {
            lopUserRepository.save(lopUser);
            return CommonResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.build(500, "注册失败");
        }
    }

    @Override
    public CommonResult sendActiveCode(String activeCode, String username) {
        lopUserRepository.updateActiveCodeByUsername(activeCode, username);
        return CommonResult.ok();
    }

    @Override
    public CommonResult verifyAndActiveUser(boolean flag, String activeCode, String username) {
        LopUser lopUser = findByUsername(username);
        if (Objects.equals(lopUser.getActiveCode(), activeCode)) {

            lopUserRepository.updateUserEnabledByUsername(flag, username);
        }else {
            return CommonResult.build(400, "激活码不正确");
        }
        return CommonResult.ok();
    }
}
