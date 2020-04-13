package com.swpu.system_manager.interceptor;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Component
public class JwtInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

//        无论如何都放行，具体能不能操作还是在棘突的操作中判断
//        拦截器只是负责把头部请求中包含token的令牌进行解析
        String header = request.getHeader("Authorization");

        if (header != null && !"".equals(header)) {
//            如果有包含有Authorization头信息，就进行截器
            if (header.startsWith("Bearer ")) {
//                得到令牌token
                String token = header.substring(7);
//                对令牌进行验证
                try {
                    Claims claims = jwtUtil.parseJWT(token);
                    String state = (String) claims.get("state");
                    if ("开启".equals(state)||"1".equals(state)){
                        request.setAttribute("state",token);
                    }
                    List<String> roles = (List<String>) claims.get("roles");
//                    String roles = (String) claims.get("roles");
                    //  包含superAdmin角色
                    if (roles != null && roles.contains("superAdmin")) {
                        request.setAttribute("claims_superAdmin", token);
                    }
                    //不包含superAdmin角色
                    if (roles != null && (!roles.contains("superAdmin") && roles.contains("admin") || roles.contains("user"))) {
                        request.setAttribute("claims_admin", token);
                    }
                    //只包含uer角色
                    if (roles != null && (!roles.contains("superAdmin") && !roles.contains("admin") && roles.contains("user"))) {
                        request.setAttribute("claims_user", token);
                    }

                } catch (Exception e) {
                    throw new RuntimeException("令牌有误");
                }
            }
        }
        return true;
    }
}
