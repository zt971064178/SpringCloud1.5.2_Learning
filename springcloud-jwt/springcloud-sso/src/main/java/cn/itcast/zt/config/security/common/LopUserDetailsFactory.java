package cn.itcast.zt.config.security.common;

import cn.itcast.zt.domain.LopRole;
import cn.itcast.zt.domain.LopUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by zhangtian on 2017/5/12.
 */
public final class LopUserDetailsFactory {
    private LopUserDetailsFactory() {

    }

    public static LopUserDetails create(LopUser user) {
        return new LopUserDetails(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                mapToGrantedAuthorities(user.getRoles()),   //根据角色名,设置权限
                user.getEnabled(),
                user.getLastPasswordResetDate()
        );
    }

    /**
     * 根据用户名获取角色Role名(具有什么权限)
     * @param authorities
     * @return
     */
    private static List<GrantedAuthority> mapToGrantedAuthorities(List<LopRole> authorities) {
        return authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getRoleName().name()))
                .collect(Collectors.toList());
    }
}
