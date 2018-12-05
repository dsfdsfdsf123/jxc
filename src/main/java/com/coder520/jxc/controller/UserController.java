package com.coder520.jxc.controller;

import com.coder520.jxc.entity.Role;
import com.coder520.jxc.entity.User;
import com.coder520.jxc.service.RoleService;
import com.coder520.jxc.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
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

    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;

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
            String userName = (String) SecurityUtils.getSubject().getPrincipal();
            User currentUser = userService.findByUserName(userName);
            //此处等重构的时候存入redis，还有验证码，还可以设置过期时间
            session.setAttribute("currentUser",currentUser);
            List<Role> roleList = roleService.findRolesByUserId(currentUser.getId());
            map.put("roleList",roleList);
            map.put("roleSize",roleList.size());
            map.put("success",true);
            return map;
        }catch (Exception e){
            e.printStackTrace();
            map.put("success",false);
            map.put("errorInfo","用户名或者密码错误");
            return map;
        }
    }

    /**
     * 保存角色信息
     * @param roleId 角色id
     * @param session session
     * @return 返回map类型
     */
    @RequestMapping("/saveRole")
    public Map<String,Object> saveRole(Integer roleId,HttpSession session){
        Map<String,Object> map = new HashMap<>();
        Role currentRole = roleService.findById(roleId);
        session.setAttribute("currentRole",currentRole);
        map.put("success",true);
        return map;
    }
}
