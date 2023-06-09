package com.xxt.token.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.omg.PortableServer.THREAD_POLICY_ID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenUtil {

    private static final Logger logger = LoggerFactory.getLogger(TokenUtil.class);

    /**
     * 加密密钥
     */
    private static final String SECRET = "432d2eb**************20dba3633";
    private static final long EXPIRE_TIME = 2 * 60 * 60 * 1000;
    private static final String JWT_ISSUER = "JWT";

    /**
     * 生成token
     * @param id
     * @return
     */
    public static String getToken(Long id){
        try {
            long currentTimeMillis = System.currentTimeMillis();
            Date expireDate = new Date(currentTimeMillis + EXPIRE_TIME);
            return JWT.create().
                    withIssuer(JWT_ISSUER).
                    withClaim("id",id).
                    withIssuedAt(new Date(currentTimeMillis))
                    .withExpiresAt(expireDate)
                    .sign(Algorithm.HMAC256(SECRET));
        } catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return null;
    }

    public static Map verify(String token){
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET))
                    .withIssuer(JWT_ISSUER)
                    .build();
            DecodedJWT decodedJWT = verifier.verify(token);
            Map resultMap = new HashMap();
            Map map = decodedJWT.getClaims();
            map.forEach((key,value)->{
                resultMap.put(key,value.toString());
            });
            return resultMap;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static boolean isExpire(String token){
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getExpiresAt().compareTo(new Date()) <= 0 ? true : false;
        } catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            return true;
        }
    }

    public static void main(String[] args) {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            Date expireDate = new Date(currentTimeMillis + 30000000);
            String token = JWT.create()
                    .withIssuer(JWT_ISSUER)
                    .withClaim("id", 1)
                    .withIssuedAt(new Date(currentTimeMillis))
                    .withExpiresAt(expireDate)
                    .sign(Algorithm.HMAC256(SECRET));
            System.out.println(token);
        } catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJKV1QiLCJpZCI6MSwiZXhwIjoxNjgzNjczMzM0LCJpYXQiOjE2ODM2NDMzMzR9.lEVEKO4TYPnOCqW0uvR2P78anLVEAMxdhpS96cdhDiI");
    }


}
