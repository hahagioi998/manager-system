package com.swpu.jwt;

import io.jsonwebtoken.Claims;

import io.jsonwebtoken.Jwts;


import java.text.SimpleDateFormat;

public class ParseJwtTest {
    public static void main(String[] args) {
        Claims claims = Jwts.parser().setSigningKey("itcast").parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI2NjYiLCJzdWIiOiLlsI_pqawiLCJpYXQiOjE1ODM3NjI2NTAsImV4cCI6MTU4Mzc2MjcxMCwicm9sZSI6ImFkbWluIn0.xsSsoLULBgjP929umNyLxRNjv5EErS1UNprGMaBNooM")
                .getBody();
        System.out.println("用户Id："+claims.getId());
        System.out.println("用户名："+claims.getSubject());
        System.out.println("登录时间："+ new SimpleDateFormat("yy-MM-dd HH:mm:ss").format(claims.getIssuedAt()));
        System.out.println("过期时间："+ new SimpleDateFormat("yy-MM-dd HH:mm:ss").format(claims.getExpiration()));
        System.out.println("角色名称："+ claims.get("role"));
    }
}
