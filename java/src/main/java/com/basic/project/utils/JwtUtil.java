package com.basic.project.utils;

import com.google.gson.Gson;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.gson.io.GsonSerializer;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class JwtUtil {
    private static final int EXPIRE_TIME = 2;
    private static final Key ENCRYPT_KEY = new SecretKeySpec("a8f90188c4a3499abbc0697e5aa363a9".getBytes(StandardCharsets.UTF_8), "HmacSHA256");

    // 解析JWT
    public static Map<String, Object> parser(String token) throws Exception {
        return Jwts.parserBuilder()
                .setSigningKey(ENCRYPT_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // 创建JWT
    public static String create(Map<String, Object> claims) {
        try {
            Calendar calendar = Calendar.getInstance();
            Date startDate = calendar.getTime();
            Date expireDate;
            calendar.add(Calendar.HOUR, EXPIRE_TIME);
            expireDate = calendar.getTime();
            return Jwts.builder()
                    .setIssuedAt(startDate)
                    .setExpiration(expireDate)
                    .addClaims(claims)
                    .signWith(ENCRYPT_KEY)
                    .serializeToJsonWith(new GsonSerializer<>(new Gson()))
                    .compact();
        } catch (Exception ex) {
            throw new RuntimeException();
        }
    }
}
