package com.coder520.jxc.controller;

import com.coder520.jxc.entity.Menu;
import com.coder520.jxc.entity.Role;
import com.coder520.jxc.entity.User;
import com.coder520.jxc.service.MenuService;
import com.coder520.jxc.service.RoleService;
import com.coder520.jxc.service.UserService;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

    @Resource
    private MenuService menuService;

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

    /**
     * 加载当前用户信息
     * @param session
     * @return
     */
    @GetMapping("/loadUserInfo")
    public String loadUserInfo(HttpSession session){
        User currentUser = (User) session.getAttribute("currentUser");
        Role currentRole = (Role) session.getAttribute("currentRole");
        return "欢迎您："+currentUser.getTrueName()+"&nbsp;[&nbsp;"+currentRole.getName()+"&nbsp;]&nbsp;";
    }

    /**
     * 加载权限菜单
     * @param session
     * @param parentId
     * @return
     * @throws Exception
     */
    @ResponseBody
    @PostMapping("/loadMenuInfo")
    public String loadMenuInfo(HttpSession session,Integer parentId)throws Exception{
        Role currentRole = (Role) session.getAttribute("currentRole");
        return getAllMenuByParentId(parentId,currentRole.getId()).toString();
    }

    /**
     * 递归
     * 获取所有菜单信息
     * @param parentId
     * @param roleId
     * @return
     */
    public JsonArray getAllMenuByParentId(Integer parentId,Integer roleId){
        JsonArray jsonArray = this.getMenuByParentId(parentId,roleId);
        for (int i=0;i<jsonArray.size();i++){
            JsonObject jsonObject = (JsonObject) jsonArray.get(i);
            if ("open".equals(jsonObject.get("state").getAsString())){
                continue;
            }else{
                jsonObject.add("children",getAllMenuByParentId(jsonObject.get("id").getAsInt(),roleId));
            }
        }
        return jsonArray;
    }

    /**
     * 根据父节点和用户角色id菜单
     * @param parentId
     * @param roleId
     * @return
     */
    public JsonArray getMenuByParentId(Integer parentId,Integer roleId){
        List<Menu> menuList = menuService.findByParentIdAndRoleId(parentId,roleId);
        JsonArray jsonArray = new JsonArray();
        for (Menu menu:menuList){
            JsonObject jsonObject = new JsonObject();
            //节点id
            jsonObject.addProperty("id",menu.getId());
            //节点名称
            jsonObject.addProperty("text",menu.getName());
            //叶子节点
            if (menu.getState()==1){
                jsonObject.addProperty("state","closed");
            }else{
                jsonObject.addProperty("state","open");
            }
            //节点图标
            jsonObject.addProperty("iconCls",menu.getIcon());
            //扩展属性
            JsonObject attributeObject = new JsonObject();
            //菜单请求地址
            attributeObject.addProperty("url",menu.getUrl());
            jsonObject.add("attributes",attributeObject);
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }
}
