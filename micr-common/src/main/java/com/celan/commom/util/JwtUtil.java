package com.celan.commom.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.apache.commons.lang3.time.DateUtils;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class JwtUtil {

    private SecretKey selfKey;

    public JwtUtil(String selfKey){
        byte[] bytes = selfKey.getBytes(StandardCharsets.UTF_8);
        this.selfKey = Keys.hmacShaKeyFor(bytes);
    }

    public String createJwt(Map<String,Object> data, Integer minute) throws Exception{
        Date curDate = new Date();
        String jwt = Jwts.builder().signWith(selfKey, SignatureAlgorithm.HS256)
                .setExpiration(DateUtils.addMinutes(curDate, minute))
                .setIssuedAt(curDate)
                .setId(UUID.randomUUID().toString().replaceAll("-","").toUpperCase())
                .addClaims(data)
                .compact();

        return jwt;
    }

    public Claims readJwt(String jwt) throws Exception{
        Claims body = Jwts.parserBuilder().setSigningKey(selfKey).build().parseClaimsJws(jwt).getBody();
        return body;
    }
}
