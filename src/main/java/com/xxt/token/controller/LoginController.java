package com.xxt.token.controller;

import com.xxt.token.util.TokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
@RequestMapping("/index/")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping("login")
    public String login(){
        return "login";
    }

    @RequestMapping("sign")
    public String sign(String username,String passwd,HttpServletResponse response,Map map){
        if(("test").equals(username)&&("test").equals(passwd)){
            logger.info("验证成功！");
            String token = TokenUtil.getToken(1L);
            response.setHeader("token",token);
            map.put("username",username);
            map.put("token",token);
            return "index";
        }else{
            logger.error("验证失败！");
            return "login";
        }
    }
    @RequestMapping("test")
    @ResponseBody
    public String test(){
        System.out.println(111);
        return "";
    }

}
