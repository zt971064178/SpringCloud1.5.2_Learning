package cn.itcast.zt.common.jsonwebtoken;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.List;

/**
 * 产生token的主要类
 * Created by zhangtian on 2017/5/9.
 */
public class JsonWebTokenUtility {
    private SignatureAlgorithm signatureAlgorithm ;
    private Key secretKey ;

    // THIS IS NOT A SECURE PRACTICE!
    // For simplicity, we are storing a static key here.
    // Ideally, in a microservices environment, this key would kept on a
    // config server.
    // 目前默认是写死编解码token
    public JsonWebTokenUtility(){
        signatureAlgorithm = SignatureAlgorithm.HS512 ;
        String encodedKey = "L7A/6zARSkK1j7Vd5SDD9pSSqZlqF7mAhiOgRbgv9Smce6tf4cJnvKOjtKPxNNnWQj+2lQEScm3XIUjhW+YVZg=="; ;
        secretKey = deserializeKey(encodedKey) ;
    }

    /**
     * 反序列化解码
     * @param encodedKey
     * @return
     */
    private Key deserializeKey(String encodedKey) {
        byte[] decodedKey = Base64.getDecoder().decode(encodedKey) ;
        Key key = new SecretKeySpec(decodedKey, getSignatureAlgorithm().getJcaName()) ;
        return key ;
    }

    public SignatureAlgorithm getSignatureAlgorithm() {
        return signatureAlgorithm;
    }

    public Key getSecretKey() {
        return secretKey;
    }

    /**
     * 创建token
     * @param authTokenDetailsDTO
     * @return
     */
    public String createJsonWebToken(AuthTokenDetailsDTO authTokenDetailsDTO) {
        String token = Jwts.builder()
                            .setSubject(authTokenDetailsDTO.getUserId())
                            .claim("email", authTokenDetailsDTO.getEmail())
                            .claim("roleIds", authTokenDetailsDTO.getRoleIds())// 新增roleIds
                            .claim("roleNames", authTokenDetailsDTO.getRolesNames())
                            .setExpiration(authTokenDetailsDTO.getExpirationDate())
                            .signWith(getSignatureAlgorithm(), getSecretKey())
                            .compact() ;

        return token ;
    }

    /**
     * 解析token
     * @param token
     * @return
     */
    public AuthTokenDetailsDTO parseAndValidate(String token){
        AuthTokenDetailsDTO authTokenDetailsDTO = null ;
        try {
            Claims claims = Jwts.parser().setSigningKey(getSecretKey()).parseClaimsJws(token).getBody() ;
            String userId = claims.getSubject() ;
            String email = (String) claims.get("email");
            List<String> roleNames = (List<String>) claims.get("roleNames");
            List<String> roleIds = (List<String>) claims.get("roleIds");// 新增roleIds
            Date expirationDate = claims.getExpiration() ;

            authTokenDetailsDTO = new AuthTokenDetailsDTO() ;
            authTokenDetailsDTO.setUserId(userId);
            authTokenDetailsDTO.setEmail(email);
            authTokenDetailsDTO.setRoleIds(roleIds);
            authTokenDetailsDTO.setRolesNames(roleNames);
            authTokenDetailsDTO.setExpirationDate(expirationDate);
        }catch (JwtException ex) {
            ex.printStackTrace();
        }
        return authTokenDetailsDTO ;
    }

    /**
     * 序列化编码
     * @param key
     * @return
     */
    private String serializeKey(Key key) {
        String encodedKey = Base64.getEncoder().encodeToString(key.getEncoded()) ;
        return encodedKey ;
    }
}
