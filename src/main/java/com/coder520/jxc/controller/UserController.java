package com.coder520.jxc.controller;

import com.coder520.jxc.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 用户controller
 * @author liugang
 * @create 2018/12/5 22:24
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    /**
     * 用户登录
     * @param imageCode 验证码
     * @param user 用户信息
     * @param bindingResult jpa默认绑定信息
     * @param session 从session里面取验证码
     * @return
     */
    @RequestMapping("/login")
    public Map<String,Object> login(String imageCode, @Valid User user, BindingResult bindingResult, HttpSession session){
        Map<String,Object> map = new HashMap<>();
        if(StringUtils.isEmpty(imageCode)){
            map.put("success",false);
            map.put("errorInfo","请输入验证码！");
            return map;
        }
        if (!session.getAttribute("checkcode").equals(imageCode)){
            map.put("success",false);
            map.put("errorInfo","验证码输入错误！");
            return map;
        }
        if (bindingResult.hasErrors()){
            map.put("success",false);
            map.put("errorInfo", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
            return map;
        }
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(),user.getPassword());
        try{
            subject.login(token);
            map.put("success",true);
            return map;
        }catch (Exception e){
            e.printStackTrace();
            map.put("success",false);
            map.put("errorInfo","用户名或者密码错误");
            return map;
        }
    }
}
