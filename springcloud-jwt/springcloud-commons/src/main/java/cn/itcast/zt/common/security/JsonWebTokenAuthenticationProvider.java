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
            }else {
                // It is already a JsonWebTokenAuthentication
                authenticatedUser = authentication;
            }
        }
        return authenticatedUser;
    }

    private UserDetails parseToken(String tokenHeader) {
        UserDetails principal = null;
        AuthTokenDetailsDTO authTokenDetails = jsonWebTokenUtility.parseAndValidate(tokenHeader);

        if (authTokenDetails != null) {
            List<GrantedAuthority> authorities = authTokenDetails.getRolesNames().stream().map(roleName -> new SimpleGrantedAuthority(roleName)).collect(Collectors.toList());
            principal = new User(authTokenDetails.getEmail(), "", authorities);
        }

        return principal;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(PreAuthenticatedAuthenticationToken.class)
                || authentication.isAssignableFrom(JsonWebTokenAuthentication.class);
    }
}
