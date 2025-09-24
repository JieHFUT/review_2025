package com.jiehfut.headline.util;

import com.alibaba.druid.util.StringUtils;
import io.jsonwebtoken.*;

import java.util.Date;

/**
 * ClassName: JwtHelper
 * Package: com.jiehfut.headline.util
 * Description:
 *
 * @Author jieHFUT
 * @Create 2024/12/22 20:03
 * @Version 1.0
 */
public class JwtHelper {
    // token 的有效性
    private static long tokenExpiration = 60*60*1000;
    // 密文缀余，在密文反向解析的时候需要知道这一段数据
    private static String tokenSignKey = "123456";

    //生成token字符串
    //一个 long 类型的用户ID，返回一个密文，可以在密文里存储多个键值对
    public static String createToken(Long userId) {
        String token = Jwts.builder()

                .setSubject("YYGH-USER")
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))
                .claim("userId", userId)
                .signWith(SignatureAlgorithm.HS512, tokenSignKey)
                .compressWith(CompressionCodecs.GZIP)
                .compact();
        return token;
    }

    //从token字符串获取userid
    public static Long getUserId(String token) {
        if(StringUtils.isEmpty(token)) return null;
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        Integer userId = (Integer)claims.get("userId");
        return userId.longValue();
    }



    //判断token是否有效，过期了返回 true
    public static boolean isExpiration(String token){
        try {
            boolean isExpire = Jwts.parser()
                    .setSigningKey(tokenSignKey)
                    .parseClaimsJws(token)
                    .getBody()
                    .getExpiration().before(new Date());
            //没有过期，有效，返回false
            return isExpire;
        }catch(Exception e) {
            //过期出现异常，返回true
            return true;
        }
    }
}
