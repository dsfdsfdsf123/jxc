package com.coder520.jxc.service;

import com.coder520.jxc.entity.Role;

import java.util.List;

/**
 * 角色Service接口
 * @author liugang
 * @create 2018/12/5 23:07
 **/
public interface RoleService {

    /**
     * 根据用户id查找角色集合
     * @param id 用户id
     * @return 返回 Rolelist
     */
    public List<Role> findRolesByUserId(Integer id);

    /**
     * 根据id查询实体
     * @param id 角色id
     * @return role实体
     */
    public Role findById(Integer id);
}
