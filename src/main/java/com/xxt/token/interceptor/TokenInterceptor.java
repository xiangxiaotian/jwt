package com.xxt.token.interceptor;

import com.xxt.token.util.TokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class TokenInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(TokenInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        if(handler instanceof HandlerMethod){
            String token = request.getHeader("Authorization");
            if(!StringUtils.isEmpty(token)){
                Map map = TokenUtil.verify(token);
                map.get("user");
            } else{
              response.sendRedirect("/index/login");
            }
        }
        return true;
    }
}
