package cn.itcast.zt.common.security;

import cn.itcast.zt.common.jsonwebtoken.AuthTokenDetailsDTO;
import cn.itcast.zt.common.jsonwebtoken.JsonWebTokenUtility;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 在这里，头部信息将被转换为Spring Authentication对象，名称为PreAuthenticatedAuthenticationToken
 * 我们需要一个授权提供者读取这个记号，然后验证它，然后转换为我们自己的定制授权对象，就是把header里的token转化成我们自己的授权对象。
 * 然后把解析之后的对象返回给Spring Security，这里就相当于完成了token->session的转换
 * Created by zhangtian on 2017/5/9.
 */
@Component
public class JsonWebTokenAuthenticationProvider implements AuthenticationProvider {

    private JsonWebTokenUtility jsonWebTokenUtility = new JsonWebTokenUtility() ;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Authentication authenticatedUser = null;
        // Only process the PreAuthenticatedAuthenticationToken
        if(authentication.getClass().isAssignableFrom(PreAuthenticatedAuthenticationToken.class)
                && authentication.getPrincipal() != null) {
            String tokenHeader = (String) authentication.getPrincipal();
            UserDetails userDetails = parseToken(tokenHeader) ;
            if(userDetails != null) {
                authenticatedUser = new JsonWebTokenAuthentication(userDetails, tokenHeader) ;
                authenticatedUser.setAuthenticated(true);// 解析到token中有数据，设置认证校验通过,拦截器终止
            }else {
                // It is already a JsonWebTokenAuthentication
                // authenticatedUser = authentication;// 校验不通过  进入spring security的拦截器
                authenticatedUser = null ;// 设置为null  进入拦截器验证不通过
            }
        }
        return authenticatedUser;
    }

    private UserDetails parseToken(String tokenHeader) {
        UserDetails principal = null;
        AuthTokenDetailsDTO authTokenDetails = jsonWebTokenUtility.parseAndValidate(tokenHeader);

        if (authTokenDetails != null) {
            List<String> roleIds = authTokenDetails.getRoleIds() ;
            List<String> roleNames = authTokenDetails.getRolesNames() ;
            if (roleIds != null) {
                List<GrantedAuthority> authorities = authorities = authTokenDetails.getRoleIds().stream().map(roleId->new SimpleGrantedAuthority(roleId)).collect(Collectors.toList());
                // 既然在toekn生成以及parseAndValidate解析中加入了roleIds，roleNames，则authorities需要将两者都加入，否则匹配失败
                authorities.addAll(authTokenDetails.getRolesNames().stream().map(roleName -> new SimpleGrantedAuthority(roleName)).collect(Collectors.toList()));
                principal = new User(authTokenDetails.getEmail(), "", authorities);
            }
        }

        return principal;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(PreAuthenticatedAuthenticationToken.class)
                || authentication.isAssignableFrom(JsonWebTokenAuthentication.class);
    }
}
