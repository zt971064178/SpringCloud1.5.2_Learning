package cn.itcast.zt.config.security.common;

import cn.itcast.zt.domain.LopUser;
import cn.itcast.zt.repository.LopUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 实现 UserDetailsService 接口
 * Created by zhangtian on 2017/5/12.
 */
@Service
public class LopUserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private LopUserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LopUser user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return LopUserDetailsFactory.create(user);
        }
    }
}
