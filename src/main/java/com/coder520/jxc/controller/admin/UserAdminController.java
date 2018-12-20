package com.coder520.jxc.controller.admin;

import com.coder520.jxc.entity.Role;
import com.coder520.jxc.entity.User;
import com.coder520.jxc.service.RoleService;
import com.coder520.jxc.service.UserService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liugang
 * @create 2018/12/20 22:33
 **/
@Controller
@RequestMapping("/admin/user")
public class UserAdminController {

    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;

    /**
     * 分页查询用户信息
     * @param user
     * @param page
     * @param rows
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/list")
    public Map<String,Object> list(User user, @RequestParam(value = "page",required = false)Integer page,@RequestParam(value = "rows",required = false)Integer rows)throws Exception{
        Map<String,Object> resultMap = new HashMap<>();
        List<User> userList = userService.list(user,page,rows, Sort.Direction.ASC,"id");
        for (User u:userList){
            List<Role> roleList = roleService.findRolesByUserId(u.getId());
            StringBuffer stringBuffer = new StringBuffer();
            for (Role r:roleList){
                stringBuffer.append(","+r.getName());
            }
            u.setRoles(stringBuffer.toString().replaceFirst(",",""));
        }
        Long total = userService.getCount(user);
        resultMap.put("rows",userList);
        resultMap.put("total",total);
        return resultMap;
    }
}
